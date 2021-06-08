package pl.condigitall.demo.model;

import pl.condigitall.demo.dto.AuctionDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "auctions")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Card card;
    @ManyToOne
    private Trainer trainer;
    private int price;
    private int amount;

    public Auction(Card card, Trainer trainer, int price, int amount) {
        this.card = card;
        this.trainer = trainer;
        this.price = price;
        this.amount = amount;
    }

    public Auction() {
    }

    public AuctionDto toDto() {
      return new AuctionDto(amount, price, card.getImageUrl(), id);
    }

    public int getPrice() {
        return price;
    }

    public Card getCard() {
        return card;
    }

    public int getAmount() {
        return amount;
    }

    public void decreaseAmount(int decreaseAmount) {
        this.amount -= decreaseAmount;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return id == auction.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
