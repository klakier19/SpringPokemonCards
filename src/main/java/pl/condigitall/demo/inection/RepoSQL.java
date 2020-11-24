package pl.condigitall.demo.inection;

import org.springframework.stereotype.Component;

@Component
public class RepoSQL implements Repo {

    @Override
    public void load() {
        System.out.println("SQL Repo");
    }
}
