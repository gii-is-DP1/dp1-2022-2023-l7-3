package org.springframework.monopoly.game;

import org.springframework.data.repository.CrudRepository;


public interface GameRepository extends CrudRepository<Game, Integer> {
}
