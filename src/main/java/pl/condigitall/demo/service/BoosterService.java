package pl.condigitall.demo.service;

import org.springframework.stereotype.Service;
import pl.condigitall.demo.model.Card;
import pl.condigitall.demo.repository.CardRepo;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BoosterService {
    private CardRepo cardRepo;

    public BoosterService(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }
    @PostConstruct
    public void fillCardDB() {
        cardRepo.save(new Card("Caro"));
        cardRepo.save(new Card("trefl"));
        cardRepo.save(new Card("Pik"));
        cardRepo.save(new Card("hearts"));
    }

    public List<Card> cardGenerator() {
        Random random = new Random();
        List<Card> cardList = new ArrayList<>();
        cardList.add(cardRepo.getOne(random.nextInt(4)));
        cardList.add(cardRepo.getOne(random.nextInt(4)));
        return cardList;
    }
}
