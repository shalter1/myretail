package com.myretail.restservice;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PriceRepository extends MongoRepository<Price, Integer> {
}
