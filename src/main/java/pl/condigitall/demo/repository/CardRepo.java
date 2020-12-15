package pl.condigitall.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.condigitall.demo.model.Card;

@Repository
public interface CardRepo extends JpaRepository<Card, Integer> {
}
