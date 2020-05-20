package com.example.demo.api;

import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.ClinicCenterAdmin;
import com.example.demo.model.UserRegisterRequest;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserRegisterService;
import com.example.demo.useful_beans.UserToLogin;
import com.example.demo.dto.UserTokenState;
import com.example.demo.model.User;
import com.example.demo.security.TokenUtils;
import com.example.demo.security.auth.JwtAuthenticationRequest;
import com.example.demo.useful_beans.ChangePassword;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody UserToLogin userToLogin,
                                                                    HttpServletResponse response) {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userToLogin.username,
                        userToLogin.password));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        List<String> strAuthorities = new ArrayList<>();
        for (GrantedAuthority authority : user.getAuthorities())
            strAuthorities.add(authority.getAuthority());

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, strAuthorities, user.getEmail()));
    }

    @GetMapping("/userDetails")
    public UserDTO getUserDetails() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = new UserDTO(user);
        System.out.println(userDTO);
        return userDTO;
    }

    @PostMapping("/updateProfile")
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
    public ResponseEntity<Void> changePassword(@RequestBody ChangePassword changePassword){
        Optional<User> check = userRepository.findById(Integer.parseInt(changePassword.getId()));

        if(check.isPresent()){
            User user = check.get();
            if(!user.getPassword().equals(changePassword.getOld()))
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            user.setPassword(changePassword.getNew_pass());
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

        boolean success = userRegisterService.saveUserRegisterRequest(patientToRegister);
        if (success) {
            List<ClinicCenterAdmin> clinicCenterAdmins = userService.findAllClinicCenterAdmins();
            emailService.alertClinicCenterAdminsForUserRegister(clinicCenterAdmins, patientToRegister);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Request not saved to database", HttpStatus.INTERNAL_SERVER_ERROR);
    }





//
//    @PostMapping(value = "/refresh")
//    public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request) {
//
//        String token = tokenUtils.getToken(request);
//        String username = this.tokenUtils.getUsernameFromToken(token);
//        User user = (User) this.userDetailsService.loadUserByUsername(username);
//
//        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
//            String refreshedToken = tokenUtils.refreshToken(token);
//            int expiresIn = tokenUtils.getExpiredIn();
//
//            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
//        } else {
//            UserTokenState userTokenState = new UserTokenState();
//            return ResponseEntity.badRequest().body(userTokenState);
//        }
//    }
//
//    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
//        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
//
//        Map<String, String> result = new HashMap<>();
//        result.put("result", "success");
//        return ResponseEntity.accepted().body(result);
//    }
//
//    static class PasswordChanger {
//        public String oldPassword;
//        public String newPassword;
//    }
}
