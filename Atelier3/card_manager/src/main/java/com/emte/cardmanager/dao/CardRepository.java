package com.emte.cardmanager.dao;

import com.emte.model.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
    @Query(value = "SELECT c FROM Card c WHERE c.userId IS NULL")
    List<Card> findSoldCards();

    List<Card> findCardsByUserId(@Param("userId") Integer userId);
}
