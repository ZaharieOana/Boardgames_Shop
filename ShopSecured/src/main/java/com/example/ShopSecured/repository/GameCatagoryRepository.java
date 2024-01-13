package com.example.ShopSecured.repository;

import com.example.ShopSecured.model.GameType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCatagoryRepository extends CrudRepository<GameType, Integer> {
}
