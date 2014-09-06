package com.books.service;

import com.books.extracting.data.Database;
import com.books.database.MongoConfiguration;
import com.books.model.Book;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BookService {

//    @Autowired
//    private MongoTemplate mongoTemplate;
    ApplicationContext context
            = new AnnotationConfigApplicationContext(MongoConfiguration.class);
    //  MongoTemplate mongoTemplate = (MongoTemplate) ctx.getBean("mongoTemplate");

    MongoOperations mongoTemplate = (MongoOperations) context.getBean("mongoTemplate");

    public static final String COLLECTION_NAME = "books";

    public List<Book> listBook() {
        return mongoTemplate.findAll(Book.class, COLLECTION_NAME);
    }

    public List<Book> findBookOrBooksByAuthorOrByTitle(String value) {
        List<Book> listOfBooksByCriteria;

        Query authorQuery = new Query();
        authorQuery.addCriteria(Criteria.where("author").is(value));

        int countBooksByAuthor = (int) mongoTemplate.count(authorQuery, COLLECTION_NAME);

        if (countBooksByAuthor != 0) {
            listOfBooksByCriteria = mongoTemplate.find(authorQuery, Book.class);
        } else {
            Query titleQuery = new Query();
            titleQuery.addCriteria(Criteria.where("name").is(value));
            listOfBooksByCriteria = mongoTemplate.find(titleQuery, Book.class);
        }
        return listOfBooksByCriteria;
    }

    public List<Book> findBook(String value) {

        List<Book> listOfBooksByCriteria;

        Query titleQuery = new Query();
        titleQuery.addCriteria(Criteria.where("name").is(value));
        listOfBooksByCriteria = mongoTemplate.find(titleQuery, Book.class);

        return listOfBooksByCriteria;

    }

    public List<String> publishers() {
        List<Book> bookList;
        List<String> publishersList = new ArrayList();
        bookList = mongoTemplate.findAll(Book.class, COLLECTION_NAME);

        for (Book book : bookList) {
            publishersList.add(book.getPublisher());
        }
        HashSet set = new HashSet();
        set.addAll(publishersList);
        publishersList.clear();
        publishersList.addAll(set);

        return publishersList;
    }

    public List<Book> getAllBooksByPublisher(String publisher) {

        Query query = new Query();
        query.addCriteria(Criteria.where("publisher").is(publisher));
        List<Book> listOfBooksByCriteria = mongoTemplate.find(query, Book.class);

        return listOfBooksByCriteria;
    }

    public void getJSONArray(String name) {

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        DBObject bookDB = Database.getDatabaseObject().getBook(name);
        JSONArray reviews = (JSONArray) bookDB.get("reviews");
        Book book = mongoTemplate.findOne(query, Book.class);

        // BasicDBObject book =  //mongoTemplate.findOne(query, Book.class);
        //System.out.println(book);
    }
}
