package pl.condigitall.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.condigitall.demo.model.Trainer;

@Repository
public interface TrainerRepo extends JpaRepository<Trainer, Long> {

}
