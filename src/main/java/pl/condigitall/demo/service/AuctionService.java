package pl.condigitall.demo.service;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import pl.condigitall.demo.dto.AuctionDto;
import pl.condigitall.demo.model.Auction;
import pl.condigitall.demo.model.Card;
import pl.condigitall.demo.model.Trainer;
import pl.condigitall.demo.repository.AuctionRepo;
import pl.condigitall.demo.repository.CardRepo;
import pl.condigitall.demo.request.BuyRequest;
import pl.condigitall.demo.request.SellRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuctionService {
   private TrainerAccessService trainerAccessService;
   private CardRepo cardRepo;
   private AuctionRepo auctionRepo;

    public AuctionService(TrainerAccessService trainerAccessService, CardRepo cardRepo, AuctionRepo auctionRepo) {
        this.trainerAccessService = trainerAccessService;
        this.cardRepo = cardRepo;
        this.auctionRepo = auctionRepo;
    }

    public Map<Card, Integer> getSellViewData(){
        return trainerAccessService.getTrainer().getCards();
    }

    public List<AuctionDto> getBuyViewData(){
        return auctionRepo.findAll().stream()
                .map(auction -> auction.toDto())
                .collect(Collectors.toList());
    }

    public void sellCard(SellRequest sellRequest) {
        Card card = cardRepo.findById(sellRequest.getCardID()).orElseThrow();
        Trainer trainer = trainerAccessService.getTrainer();
        if (trainer.getCount(card) < sellRequest.getCardAmount() ) {
            throw new AuctionServiceException("Nie masz wystarczającej ilości kart");
        }
        Auction auction = new Auction(card
                , trainer
                , sellRequest.getCardPrice()
                , sellRequest.getCardAmount());
        auctionRepo.save(auction);
        trainer.delCard(card, sellRequest.getCardAmount());
        trainerAccessService.saveTrainer(trainer);
    }

    public void buyCard(BuyRequest buyRequest) {
        Auction auction = auctionRepo.findById(buyRequest.getAuctionID()).orElseThrow(() -> new AuctionServiceException("Nie ma takiej aukcji"));
        int totalCardPrice = auction.getPrice() * buyRequest.getCardAmount();
        Trainer buyingTrainer = trainerAccessService.getTrainer();
        Trainer sellTrainer = auction.getTrainer();

        if(buyingTrainer.getCash() < totalCardPrice) {
            throw new AuctionServiceException("Trener nie ma pieniędzy");
        }
        if (auction.getAmount() < buyRequest.getCardAmount()) {
            throw new AuctionServiceException("Aukcja nie zaweira wystarczającej ilości kart");
        }
        buyingTrainer.addCard(auction.getCard());
        if (!buyingTrainer.equals(sellTrainer)) {
            buyingTrainer.decreaseCash(totalCardPrice);
            sellTrainer.increaseCash(totalCardPrice);
        }
        if (auction.getAmount() == buyRequest.getCardAmount()) {
            auctionRepo.delete(auction);
        } else {
            auction.decreaseAmount(buyRequest.getCardAmount());
            auctionRepo.save(auction);
        }
        trainerAccessService.saveTrainer(buyingTrainer);
        trainerAccessService.saveTrainer(sellTrainer);
    }
}
