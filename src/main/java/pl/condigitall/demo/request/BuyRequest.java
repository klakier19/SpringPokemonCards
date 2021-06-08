package pl.condigitall.demo.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class BuyRequest {
        @Min(value = 1, message = "Musisz kupić conajmniej jedną kartę")
        private int cardAmount;
        private int auctionID;
}
