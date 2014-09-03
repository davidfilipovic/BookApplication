package com.books.database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 *
 * @author David
 */
public class MongoConfiguration {

    public @Bean
    MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClient(), "testdb");
    }

    public @Bean
    MongoTemplate mongoTemplate() throws UnknownHostException {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

}
