package com.example.demo;

import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class CupboardRepositoryCustomImpl implements CupboardRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public CupboardRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void deleteCupByObject(String cupboardId, String cupId) {
        Query query = new Query(where("id").is(cupboardId));

        Update update = new Update().pull("cups", new Cup(cupId, null));

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Cupboard.class);

        if (updateResult.getMatchedCount() == 0) {
            throw new RuntimeException("Not found");
        }
    }

    @Override
    public void deleteCupByCriteria(String cupboardId, String cupId) {
        Query query = new Query(where("id").is(cupboardId));

        Update update = new Update().pull("cups", query(where("id").is(cupId)));

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Cupboard.class);

        if (updateResult.getMatchedCount() == 0) {
            throw new RuntimeException("Not found");
        }
    }

    @Override
    public void addCup(String cupboardId, Cup cup) {
        UpdateResult updateResult = mongoTemplate.updateFirst(query(where("id").is(cupboardId)),
                new Update().push("cups", cup), Cupboard.class);

        if (updateResult.getMatchedCount() == 0) {
            throw new RuntimeException("Cupboard not found");
        }
    }
}
