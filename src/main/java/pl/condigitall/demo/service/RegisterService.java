package pl.condigitall.demo.service;

import org.springframework.stereotype.Service;
import pl.condigitall.demo.model.User;
import pl.condigitall.demo.repository.TrainerRepo;
import pl.condigitall.demo.repository.UserRepo;
import pl.condigitall.demo.request.UserRequest;

@Service
public class RegisterService {
    private UserRepo userRepo;
    private TrainerRepo trainerRepo;

    public RegisterService(UserRepo userRepo, TrainerRepo trainerRepo) {
        this.userRepo = userRepo;
        this.trainerRepo = trainerRepo;
    }

    public void register(UserRequest userRequest) {
        if(userRepo.existsByEmail(userRequest.getEmail())) {
            throw new RegisterServiceException("Taki email już istnieje");
        }
        if(!userRequest.getEmail().contains("@") || userRequest.getPassword().length() < 3) {
            throw new RegisterServiceException("Błędny adres email lub hasło");
        }
        User user = new User(userRequest.getEmail(), userRequest.getPassword());
        trainerRepo.save(user.getTrainer());
        userRepo.save(user);
        System.out.println(userRepo.findAll());
    }
}
