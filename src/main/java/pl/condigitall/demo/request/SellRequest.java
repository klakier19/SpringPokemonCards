package pl.condigitall.demo.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class SellRequest {
    @Min(value = 0, message = "Cena nie może być ujemna")
    private int cardPrice;
    @Min(value = 1, message = "Musisz sprzedawać conajmniej jedną kartę")
    private int cardAmount;
    private String cardID;
}
