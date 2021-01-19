package pl.condigitall.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Card {
    @Id
    private String id;
    private String name;
    private String imageUrl;

    public Card(String name) {
        this.name = name;
    }
}
