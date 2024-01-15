package org.shop.Shop.services;

import org.shop.Shop.models.GameType;
import org.shop.Shop.repos.GameCatagoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameCategoryService {
    @Autowired
    private GameCatagoryRepository typeRepository;

    public Iterable<GameType> findAll() {
        return typeRepository.findAll();
    }

    public void save(GameType newType) {
        typeRepository.save(newType);
    }
}
