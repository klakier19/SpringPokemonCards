package pl.condigitall.demo.inection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Aplication {
    private Repo repo;

    public Aplication(@Qualifier("hdd") Repo repo, User user) {
        System.out.println("aplication");
        repo.load();
        System.out.println(user);
    }
}
