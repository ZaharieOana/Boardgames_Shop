package org.shop.Shop.models;

public class CurrentUser {

    private static int id = -1;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        CurrentUser.id = id;
    }
}
