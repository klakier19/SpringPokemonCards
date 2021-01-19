package pl.condigitall.demo.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.condigitall.demo.model.Card;

import java.util.List;

public class CardsServerResponse {
        //@JsonProperty("cards")
        private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }
}
