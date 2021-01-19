package pl.condigitall.demo.client;

import com.sun.xml.bind.v2.runtime.output.NamespaceContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.condigitall.demo.model.Card;
import pl.condigitall.demo.repository.CardRepo;

import javax.annotation.PostConstruct;
@Component
public class PokemonTcgClient {
    private static final String API_URL = "https://api.pokemontcg.io/v1/cards";

    private RestTemplate restTemplate;
    private CardRepo cardRepo;

    public PokemonTcgClient(RestTemplate restTemplate, CardRepo cardRepo) {
        this.restTemplate = restTemplate;
        this.cardRepo = cardRepo;
    }

    @PostConstruct
    public void fillCardDB() {
        System.out.println("return jeśli Card");
        if(cardRepo.count()!=0) {
            return;
         }
        RestResponse restResponse = restTemplate.getForObject(API_URL, RestResponse.class);
        cardRepo.saveAll(restResponse.getCards());
    }
}
