package pl.condigitall.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.condigitall.demo.model.User;
import pl.condigitall.demo.repository.TrainerRepo;
import pl.condigitall.demo.repository.UserRepo;
import pl.condigitall.demo.request.UserRequest;

@Service
public class RegisterService {
    private UserRepo userRepo;
    private TrainerRepo trainerRepo;
    private PasswordEncoder passwordEncoder;

    public RegisterService(UserRepo userRepo, TrainerRepo trainerRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.trainerRepo = trainerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRequest userRequest) {
        if(userRepo.existsByEmail(userRequest.getEmail())) {
            throw new RegisterServiceException("Taki email już istnieje");
        }
        if(!userRequest.getEmail().contains("@") || userRequest.getPassword().length() < 3) {
            throw new RegisterServiceException("Błędny adres email lub hasło");
        }
        String hashPassword = passwordEncoder.encode(userRequest.getPassword());
        User user = new User(userRequest.getEmail(), hashPassword);
        trainerRepo.save(user.getTrainer());
        userRepo.save(user);
        System.out.println(userRepo.findAll());
    }
}
