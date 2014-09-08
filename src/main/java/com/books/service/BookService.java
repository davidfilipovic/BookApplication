package com.books.service;

import com.books.database.MongoConfiguration;
import com.books.model.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BookService {

    ApplicationContext context
            = new AnnotationConfigApplicationContext(MongoConfiguration.class);

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

        List<Book> listOfBooksByCriteria = new ArrayList<Book>();

        Query titleQuery = new Query();
        titleQuery.addCriteria(Criteria.where("name").is(value.trim()));
        Book book = mongoTemplate.findOne(titleQuery, Book.class);

        listOfBooksByCriteria.add(book);

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

    public List<String> years() {
        List<Book> bookList;
        List<String> yearsList = new LinkedList();
        bookList = mongoTemplate.findAll(Book.class, COLLECTION_NAME);

        for (Book book : bookList) {
            yearsList.add(book.getDatePublished());
        }
        HashSet set = new HashSet();
        set.addAll(yearsList);
        yearsList.clear();
        yearsList.addAll(set);
        Collections.sort(yearsList);

        return yearsList.subList(0, yearsList.size() - 5);
    }

    public List<Book> getAllBooksByPublisher(String publisher) {

        Query query = new Query();
        query.addCriteria(Criteria.where("publisher").is(publisher));
        List<Book> listOfBooksByCriteria = mongoTemplate.find(query, Book.class);

        return listOfBooksByCriteria;
    }

    public List<Book> getAllBooksByYear(String year) {

        Query query = new Query();
        query.addCriteria(Criteria.where("datePublished").is(year));
        List<Book> listOfBooksByCriteria = mongoTemplate.find(query, Book.class);

        return listOfBooksByCriteria;
    }

    public List<Book> getAllBooksByPublisherForYear(String year, String publisher) {

        Query query = new Query();
        query.addCriteria(Criteria.where("publisher").is(publisher).and("datePublished").is(year));
        List<Book> listOfBooksByCriteria = mongoTemplate.find(query, Book.class);

        return listOfBooksByCriteria;
    }

    public List<Book> getAllReadOnlineBooksYear(String year) {
        Query query = new Query();
        query.addCriteria(Criteria.where("readOnlineLink").regex(".*read.*").and("datePublished").is(year));
        List<Book> listOfBooksByCriteria = mongoTemplate.find(query, Book.class);

        return listOfBooksByCriteria;
    }

    public List<Book> getAllReadOnlineBooksPublisher(String publisher) {
        Query query = new Query();
        query.addCriteria(Criteria.where("readOnlineLink").regex(".*read.*").and("publisher").is(publisher));
        List<Book> listOfBooksByCriteria = mongoTemplate.find(query, Book.class);

        return listOfBooksByCriteria;
    }
}
