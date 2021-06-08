package pl.condigitall.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuctionDto {
    private int cardAmount;
    private double cardPrice;
    //private String trainerName; //TODO
    private String cardURL;
    private int auctionID;
    // TODO flaga czy to karta kupującego

}
