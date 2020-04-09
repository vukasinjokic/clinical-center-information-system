package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.LoginService;
import useful_beans.UserToLogin;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
@RestController
public class LoginController {

    private final LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserToLogin userToLogin){
        return loginService.login(userToLogin);
    }
}
