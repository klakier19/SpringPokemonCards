package pl.condigitall.demo.model;

import pl.condigitall.demo.service.AuctionServiceException;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public Map<Card, Integer> getCards() {
        return cards;
    }

    public int getCount(Card card) {
        return cards.get(card);
    }

    public long getCash() {
        return cash;
    }

    public void decreaseCash (long decreaseAmount) {
      if(cash < decreaseAmount) {
          throw new IllegalArgumentException("Trener nie ma pieniędzy");
      }
        cash = cash - decreaseAmount;
    }

    public void increaseCash (long increaseAmount) {
        if(increaseAmount < 0) {
            throw new IllegalArgumentException("Kwota nie może być ujemna");
        }
        cash = cash + increaseAmount;
    }

    public void delCard(Card card, int cardAmount) {
        cards.put(card, cards.get(card)-cardAmount);
        if(cards.get(card) <= 0) {
            cards.remove(card);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return id == trainer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
