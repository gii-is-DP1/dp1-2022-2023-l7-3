package org.springframework.monopoly.card;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends CrudRepository<Card, Integer> {
	
	List<Card> findAll();

    @Query("SELECT c FROM Card c WHERE c.cardType = :cardType")
    List<Card> findAllByCardType(@Param("cardType") CardType cardType);
		
}
