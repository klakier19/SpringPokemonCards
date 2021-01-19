package pl.condigitall.demo.service;

import org.springframework.stereotype.Service;
import pl.condigitall.demo.model.Booster;
import pl.condigitall.demo.model.Card;
import pl.condigitall.demo.model.Trainer;
import pl.condigitall.demo.repository.CardRepo;
import pl.condigitall.demo.viewData.BoosterViewData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BoosterService {
    private CardRepo cardRepo;
    private TrainerAccessService trainers;

    public BoosterService(CardRepo cardRepo, TrainerAccessService trainers) {
        this.cardRepo = cardRepo;
        this.trainers = trainers;
    }

    public BoosterViewData getViewData() {
        return BoosterViewData.builder()
                .cardList(new ArrayList<>())
                .boosterCost(Booster.DEFAULT_PRICE)
                .trainerCash(trainers.getTrainer().getCash())
                .build();
    }

    public List<Card> openBooster() {
        Booster booster = boosterGenerator();
        Trainer trainer = trainers.getTrainer();
        if(trainer.getCash() < booster.getPrice()) {
            throw new BoosterServiceExcepiton("Użytkownik ma za mało pieniędzy");
        }
        trainer.buyBooster(booster);
        trainers.saveTrainer(trainer);
        return booster.getCardList();
    }

    private Booster boosterGenerator() {
        Random random = new Random();
        List<Card> cardList = new ArrayList<>();
        List<Card> allCardList = cardRepo.findAll();
        for (int i = 0; i < 10; i++) {
            cardList.add(allCardList.get(random.nextInt(allCardList.size())));
        }
        return new Booster(cardList);
    }
}
//TODO: Walidacja posiadanych pieniędzy po stronie frontend i backend;