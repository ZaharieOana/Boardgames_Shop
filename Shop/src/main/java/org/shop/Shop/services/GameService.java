package org.shop.Shop.services;

import org.shop.Shop.models.Game;
import org.shop.Shop.repos.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Iterable<Game> findAll() {
        return gameRepository.findAll();
    }

    public void save(Game newGame) {
        gameRepository.save(newGame);
    }

    public Optional<Game> findById(Integer id) {
        return gameRepository.findById(id);
    }
}
