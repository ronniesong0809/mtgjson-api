package com.ronsong.mtgjsonapi.service;

import com.mongodb.bulk.BulkWriteResult;
import com.ronsong.mtgjsonapi.model.CardSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.BulkOperationException;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MtgJsonEtlService {
    private final MongoTemplate mongoTemplate;

    public BulkWriteResult save() {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, CardSet.class);
        bulkOperations.remove(new Query());
        bulkOperations.insert(Utils.getCardSet());

        try {
            return bulkOperations.execute();
        } catch (BulkOperationException e) {
            log.error(e.getMessage());
            return e.getResult();
        }
    }
}
