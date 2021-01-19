package pl.condigitall.demo.model;

import java.util.List;

public class Booster {
    public static final long DEFAULT_PRICE = 50;
    private long price = DEFAULT_PRICE;
    private List<Card> cardList;

    public Booster(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public long getPrice() {
        return price;
    }
}
