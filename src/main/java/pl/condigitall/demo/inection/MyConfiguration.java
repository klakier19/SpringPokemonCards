package pl.condigitall.demo.inection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean
    public User buildDefaultUser() {
        return new User("Maciej");
    }
}
