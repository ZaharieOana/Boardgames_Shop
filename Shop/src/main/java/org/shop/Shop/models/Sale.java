package org.shop.Shop.models;

import jakarta.persistence.Entity;

@Entity
public class Sale extends AbstractEntity{

    private int clientId;

    private int gameId;

    public Sale() {}

    public Sale(int clientId, int gameId) {
        this.clientId = clientId;
        this.gameId = gameId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

}
