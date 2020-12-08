package pl.condigitall.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.condigitall.demo.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    public Boolean existsByEmail(String email);
}
