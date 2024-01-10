package com.ronsong.mtgjsonapi.service;

import com.ronsong.mtgjsonapi.model.CardSet;
import com.ronsong.mtgjsonapi.repository.MtgJsonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MtgJsonApiService {
    private final MongoTemplate mongoTemplate;
    private final MtgJsonRepository mtgJsonRepository;

    public Page<CardSet> getCards(int page) {
        Pageable pageable = PageRequest.of(page, 25);
        return mtgJsonRepository.findAll(pageable);
    }

    public List<CardSet> getCard(String name, Integer mana, String power, String toughness, String text, String setCode, String types, String subtypes) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        if (!ObjectUtils.isEmpty(mana)){
            query.addCriteria(Criteria.where("convertedManaCost").is(mana));
        }
        if (!ObjectUtils.isEmpty(power)) {
            query.addCriteria(Criteria.where("power").is(power));
        }
        if (!ObjectUtils.isEmpty(toughness)) {
            query.addCriteria(Criteria.where("toughness").is(toughness));
        }
        if (!ObjectUtils.isEmpty(text)) {
            query.addCriteria(Criteria.where("text").is(text));
        }
        if (!ObjectUtils.isEmpty(setCode)) {
            query.addCriteria(Criteria.where("setCode").is(setCode));
        }
        if (!ObjectUtils.isEmpty(types)) {
            query.addCriteria(Criteria.where("types").in(types));
        }
        if (!ObjectUtils.isEmpty(subtypes)) {
            query.addCriteria(Criteria.where("subtypes").in(subtypes));
        }
        log.info("query: {}", query);
        return mongoTemplate.find(query, CardSet.class);
    }
}
