package pl.condigitall.demo.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "trainers")
public class Trainer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @ElementCollection(fetch=FetchType.EAGER)
    private Map<Card, Integer> cards = new HashMap<>();
    private long cash = 200;

    public void addCards(List<Card> cards) {
        for (Card card : cards) {
            addCard(card);
        }
    }

    public void addCard(Card card) {
        //cards.keySet().stream()
        //      .forEach(c -> cards.containsKey(card) ?  cards.compute(card, (k, v) -> v+1 ):  cards.put(card, 1) );

        if (cards.containsKey(card)) {
            cards.put(card, cards.get(card) + 1);
        } else {
            cards.put(card, 1);
        }
    }

    public void buyBooster(Booster booster) {
        cash = cash - booster.getPrice();
        addCards(booster.getCardList());
    }

    public long getCash() {
        return cash;
    }
}
