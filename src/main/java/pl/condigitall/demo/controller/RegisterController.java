package pl.condigitall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.condigitall.demo.request.UserRequest;
import pl.condigitall.demo.service.RegisterService;
import pl.condigitall.demo.service.RegisterServiceException;

@Controller
public class RegisterController {
    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }


    @PostMapping("/register")
    public String createUser(String email, String password, Model model) {
        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .password(password)
                .build();
        try {
            registerService.register(userRequest);
        } catch (RegisterServiceException e) {
            model.addAttribute("errorMassage",  e.getMessage());
            return "error";
        }
        return "index";
    }
}
