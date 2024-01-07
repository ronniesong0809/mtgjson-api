package com.ronsong.mtgjsonapi.repository;

import com.ronsong.mtgjsonapi.model.CardSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MtgJsonRepository extends MongoRepository<CardSet, String> {
}
