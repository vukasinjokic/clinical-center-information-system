package com.example.demo.api;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserToLogin;
import com.example.demo.dto.UserTokenState;
import com.example.demo.model.User;
import com.example.demo.security.TokenUtils;
import com.example.demo.security.auth.JwtAuthenticationRequest;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

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


        System.out.println(jwt);
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @GetMapping("/userDetails")
    public UserDTO getUserDetails() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = new UserDTO(user);
        System.out.println(userDTO);
        return userDTO;
    }

    @PostMapping("/register")
    public ResponseEntity<User> addPatient(@RequestBody UserDTO userRequest, UriComponentsBuilder ucBuilder) {

        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            throw new RuntimeException("Username already exists");
        }

        User user = this.userService.savePatient(userRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
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
