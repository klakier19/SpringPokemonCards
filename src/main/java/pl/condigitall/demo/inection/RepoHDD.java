package pl.condigitall.demo.inection;

import org.springframework.stereotype.Component;

@Component("hdd")
public class RepoHDD implements Repo{
    public RepoHDD() {
        System.out.println("repoHDD construktor");
    }

    public void load() {
        System.out.println("repoHDD");
    }
}
