package org.shop.Shop.repos;

import org.shop.Shop.models.GameType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCatagoryRepository extends CrudRepository<GameType, Integer> {
}
