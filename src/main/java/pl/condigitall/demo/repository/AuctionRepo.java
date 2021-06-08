package pl.condigitall.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.condigitall.demo.model.Auction;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Integer> {
}
