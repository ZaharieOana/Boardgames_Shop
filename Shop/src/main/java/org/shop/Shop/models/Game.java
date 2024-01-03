package org.shop.Shop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Game extends AbstractEntity{

    @NotBlank(message = "Name is required!")
    private String name;

//    @NotBlank(message = "Type is required!")
//    private String type;

    @ManyToOne
    private GameType type;

    @Positive(message = "Price must be a positive number!")
    private Integer price;

    public Game() {}

    public Game(String name, GameType type, Integer price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
