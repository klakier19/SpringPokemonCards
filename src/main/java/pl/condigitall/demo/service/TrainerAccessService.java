package pl.condigitall.demo.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.condigitall.demo.model.Trainer;
import pl.condigitall.demo.repository.TrainerRepo;

@Service
public class TrainerAccessService {
    private LoginService loginService;
    private TrainerRepo trainerRepo;

    public TrainerAccessService(LoginService loginService, TrainerRepo trainerRepo) {
        this.loginService = loginService;
        this.trainerRepo = trainerRepo;
    }

    public Trainer getTrainer() {
        return loginService.getLoggedUser().get().getTrainer();

        //todo upewnic sie ze konsola dalej dziala
    }

    public void saveTrainer(Trainer trainer) {
        trainerRepo.save(trainer);
    }
}
