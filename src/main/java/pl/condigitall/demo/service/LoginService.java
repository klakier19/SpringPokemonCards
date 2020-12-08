package pl.condigitall.demo.service;

import org.springframework.stereotype.Service;
import pl.condigitall.demo.model.User;
import pl.condigitall.demo.repository.UserRepo;
import pl.condigitall.demo.request.UserRequest;

import java.util.Optional;

@Service
public class LoginService {
    private UserRepo userRepo;
    private User loginUser;

    public LoginService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void login(UserRequest userRequest) {
        Optional<User> user = userRepo.findByEmail(userRequest.getEmail());
        if(!user.isPresent()) {
            throw new LoginServiceException("Nieprawidłowy login lub hasło");
        }
        if(!user.get().getPassword().equals(userRequest.getPassword())) {
            throw new LoginServiceException("Nieprawidłowy login lub hasło");
        }
        loginUser = user.get();
        System.out.println(loginUser);
    }
}
