package pl.condigitall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.condigitall.demo.request.UserRequest;
import pl.condigitall.demo.service.LoginService;
import pl.condigitall.demo.service.LoginServiceException;
import pl.condigitall.demo.service.RegisterServiceException;

@Controller
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(String email, String password, Model model) {
        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .password(password)
                .build();
        try {
            loginService.login(userRequest);
        } catch (LoginServiceException e) {
            model.addAttribute("errorMassage",  e.getMessage());
            return "error";
        }
        return "index";
    }
}
