package org.shop.Shop.data;

import org.shop.Shop.models.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserData {

    private static final Map<Integer, User> users = new HashMap<>();

    public static Collection<User> findAll() {
        return users.values();
    }

    public static User getById(int id) {
        return users.get(id);
    }

    public static void add(User user) {
        users.put(user.getId(), user);
    }

    public static int verifyExists(String email, String password) {
        for(User u : users.values()) {
            if(u.getEmail().equals(email)) {
                if(u.getPassword().equals(password))
                    return u.getId();
                else
                    return -1;
            }
        }
        return -1;
    }

}
