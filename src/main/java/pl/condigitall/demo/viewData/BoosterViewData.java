package pl.condigitall.demo.viewData;

import lombok.Builder;
import lombok.Data;
import pl.condigitall.demo.model.Card;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
public class BoosterViewData {
    private List<Card> cardList;
    private long trainerCash;
    private long boosterCost;
}
