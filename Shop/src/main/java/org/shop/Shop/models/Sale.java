package org.shop.Shop.models;

import jakarta.persistence.Entity;

@Entity
public class Sale extends AbstractEntity{

    private String client;
    private String game;

    public Sale() {}

    public Sale(String client, String game) {
        this.client = client;
        this.game = game;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
