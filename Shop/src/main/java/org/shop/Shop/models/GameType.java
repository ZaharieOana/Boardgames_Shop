package org.shop.Shop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class GameType extends AbstractEntity{

    @NotBlank(message = "Name is required")
    private String name;

    @OneToMany(mappedBy = "type")
    private final List<Game> games = new ArrayList<>();

    public GameType() {}

    public GameType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public String toString() {
        return name ;
    }
}
