package com.example.demo.api;

import com.example.demo.Repository.ClinicRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.*;
import com.example.demo.service.AuthorityService;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserRegisterService;
import com.example.demo.useful_beans.UserToLogin;
import com.example.demo.dto.UserTokenState;
import com.example.demo.security.TokenUtils;
import com.example.demo.useful_beans.ChangePassword;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClinicRepository clinicRepository;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody UserToLogin userToLogin,
                                                                    HttpServletResponse response) {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userToLogin.email,
                        userToLogin.password));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail());
        int expiresIn = tokenUtils.getExpiredIn();

        List<String> strAuthorities = new ArrayList<>();
        for (GrantedAuthority authority : user.getAuthorities())
            strAuthorities.add(authority.getAuthority());

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, strAuthorities, user.getEmail(),user.isPasswordChanged()));
    }

    @GetMapping("/userDetails")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE', 'PATIENT')")
    public UserDTO getUserDetails() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = new UserDTO(user);
        System.out.println(userDTO);
        return userDTO;
    }

    @PostMapping("/updateProfile")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE', 'PATIENT')")
    public ResponseEntity<UserDTO> updateProfile(@RequestBody UserDTO userDTO){
        Optional<User> check = userRepository.findById(Integer.parseInt(userDTO.getId()));

        if(check.isPresent()){
            User user = check.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setAddress(userDTO.getAddress());
            user.setCity(userDTO.getCity());
            user.setCountry(userDTO.getCountry());
            user.setPhoneNumber(userDTO.getPhoneNumber());
//            user.setSocialSecurityNumber(userDTO.getSocialSecurityNumber());

            userRepository.save(user);
            return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/changePassword")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN', 'DOCTOR', 'NURSE', 'PATIENT')")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePassword changePassword){
        Optional<User> check = userRepository.findById(Integer.parseInt(changePassword.getId()));

        if(check.isPresent()){
            User user = check.get();
            if(!passwordEncoder.matches(changePassword.getOld(), user.getPassword()))
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            user.setPasswordChanged(true);
            user.setPassword(passwordEncoder.encode(changePassword.getNew_pass()));
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/registerPatient")
    public ResponseEntity<String> registerPatient(@RequestBody UserRegisterRequest patientToRegister, UriComponentsBuilder ucBuilder) {
        List<User> existUsers = userService.findByEmailOrSocialSecurityNumber(
                patientToRegister.getEmail(),
                patientToRegister.getSocialSecurityNumber());

        if (existUsers.size() > 2)
            return new ResponseEntity<>("Unknown error. This should not happen.", HttpStatus.BAD_REQUEST);

        else if (existUsers.size() == 2)
            return new ResponseEntity<>("Both email and social security number are already taken.", HttpStatus.BAD_REQUEST);

        else if (existUsers.size() == 1) {
            if (existUsers.get(0).getEmail().equals(patientToRegister.getEmail()))
                return new ResponseEntity<>("Chosen email is already taken.", HttpStatus.BAD_REQUEST);

            if (existUsers.get(0).getSocialSecurityNumber().equals(patientToRegister.getSocialSecurityNumber()))
                return new ResponseEntity<>("Chosen social security number is already taken.", HttpStatus.BAD_REQUEST);

        }

        patientToRegister.setPassword(passwordEncoder.encode(patientToRegister.getPassword()));
        boolean success = userRegisterService.saveUserRegisterRequest(patientToRegister);
        if (success) {
            List<ClinicCenterAdmin> clinicCenterAdmins = userService.findAllClinicCenterAdmins();
            emailService.alertClinicCenterAdminsForUserRegister(clinicCenterAdmins, patientToRegister);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Request not saved to database", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @PostMapping("/registerAdmins")
    @PreAuthorize("hasAnyRole('CLINIC_CENTER_ADMIN', 'CLINIC_ADMIN')")
    public ResponseEntity registerAdmins(@RequestBody UserRegisterRequest adminToRegister) {
        List<User> existUsers = userService.findByEmailOrSocialSecurityNumber(
                adminToRegister.getEmail(),
                adminToRegister.getSocialSecurityNumber());
        if (existUsers.size() > 2)
            return new ResponseEntity<>("Unknown error. This should not happen.", HttpStatus.BAD_REQUEST);

        else if (existUsers.size() == 2)
            return new ResponseEntity<>("Both email and social security number are already taken.", HttpStatus.BAD_REQUEST);

        else if (existUsers.size() == 1) {
            if (existUsers.get(0).getEmail().equals(adminToRegister.getEmail()))
                return new ResponseEntity<>("Chosen email is already taken.", HttpStatus.BAD_REQUEST);

            if (existUsers.get(0).getSocialSecurityNumber().equals(adminToRegister.getSocialSecurityNumber()))
                return new ResponseEntity<>("Chosen social security number is already taken.", HttpStatus.BAD_REQUEST);

        }
        if(adminToRegister.getClinicId() != null){
            //clinic admin
            Optional<Clinic> try_find_clinic = clinicRepository.findById(adminToRegister.getClinicId());
            if(!try_find_clinic.isPresent())
                return new ResponseEntity<>("Clinic doesnt exists", HttpStatus.NOT_FOUND);
            Clinic clinic = try_find_clinic.get();
            List<Authority> auth = authorityService.findByName("ROLE_CLINIC_ADMIN");
            ClinicAdmin clinicAdmin = new ClinicAdmin(adminToRegister.getEmail(), passwordEncoder.encode(adminToRegister.getPassword()), adminToRegister.getFirstName(), adminToRegister.getLastName(), adminToRegister.getAddress(), adminToRegister.getCity(), adminToRegister.getCountry(), adminToRegister.getPhoneNumber(), adminToRegister.getSocialSecurityNumber(), clinic, auth, false);
            userRepository.save(clinicAdmin);
            return ResponseEntity.ok("Successfully registered clinic admin");
        }
        else{
            List<Authority> auth = authorityService.findByName("ROLE_CLINIC_CENTER_ADMIN");
            ClinicCenterAdmin clinicCenterAdmin = new ClinicCenterAdmin(adminToRegister.getEmail(), passwordEncoder.encode(adminToRegister.getPassword()), adminToRegister.getFirstName(), adminToRegister.getLastName(), adminToRegister.getAddress(), adminToRegister.getCity(), adminToRegister.getCountry(), adminToRegister.getPhoneNumber(), adminToRegister.getSocialSecurityNumber(), auth, false);
            userRepository.save(clinicCenterAdmin);
            return ResponseEntity.ok("Successfully registered clinic center admin");
        }


    }
}
