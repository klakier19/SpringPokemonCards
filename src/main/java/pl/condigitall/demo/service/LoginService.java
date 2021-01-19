package pl.condigitall.demo.service;

import org.springframework.stereotype.Service;
import pl.condigitall.demo.model.Trainer;
import pl.condigitall.demo.model.User;
import pl.condigitall.demo.repository.UserRepo;
import pl.condigitall.demo.request.UserRequest;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class LoginService {
    private UserRepo userRepo;
    private User loginUser;

    public LoginService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void login(UserRequest userRequest) {
        var e = new LoginServiceException("Nieprawidłowy login lub hasło");
        User user = userRepo.findByEmail(userRequest.getEmail())
                .orElseThrow(()->e);

        if(!user.getPassword().equals(userRequest.getPassword())) {
            throw e;
        }
        loginUser = user;
        System.out.println(loginUser);
    }

    public User getLoginUser() {
        return loginUser;
    }
}
