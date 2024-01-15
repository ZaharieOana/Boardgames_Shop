package org.shop.Shop.services;

import org.shop.Shop.models.User;
import org.shop.Shop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User newUser) {
        userRepository.save(newUser);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> findByEmail(String email){
        return userRepository.findByEmailIs(email);
    }
}
