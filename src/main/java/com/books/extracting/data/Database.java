/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.books.extracting.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author David
 */
public final class Database {

    public static Database instance;
    MongoClient mongo;
    DB database;
    DBCollection bookTable;
    DBCursor cursor;

    private Database() {
        initializeDatabase();
        bookTable = database.getCollection("books");
        cursor = bookTable.find();
    }

    public static Database getDatabaseObject() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void initializeDatabase() {
        try {
            mongo = new MongoClient("localhost", 27017);
            database = mongo.getDB("booksdb");
        } catch (UnknownHostException u) {
        } catch (MongoException e) {
        }
    }

    public void printNames() {
        while (cursor.hasNext()) {
            BasicDBObject nameObject = (BasicDBObject) cursor.next();
            String name = nameObject.getString("name");
            System.out.println(name);
        }
    }

    public void prettyPrintJSONObjectFromDB(DBCursor cursor) {
        DBObject row;
        while (cursor.hasNext()) {
            row = cursor.next();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(row);
            System.out.println(json);
        }
    }

    public void prettyPrintAllJSONObjectFromDB() {
        DBObject row;
        while (cursor.hasNext()) {
            row = cursor.next();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(row);
            System.out.println(json);
        }
        cursor.close();
    }

    public void saveBook(JSONObject data) {
        DBObject dbo = (DBObject) JSON.parse(data.toString());
        bookTable.insert(dbo);
    }

    public boolean bookExists(String bookName) {
        HashMap mapValue = new HashMap();
        HashMap searchMap = new HashMap();
        mapValue.put("$options", "i");  // for ignoring case
        mapValue.put("$regex", bookName);
        searchMap.put("name", mapValue);
        BasicDBObject searchQuery = new BasicDBObject("name", bookName);
        DBObject checkObject = bookTable.findOne(searchQuery);
        return checkObject != null;
    }

    public void updateBook(ArrayList<String> updateList, String bookName, JSONArray reviewArray) {

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("name", bookName);

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("inLanguage", updateList.get(0));
        updateQuery.append("numberOfPages", updateList.get(1));
        updateQuery.append("aggregateRating.ratingValue", updateList.get(2));
        updateQuery.append("aggregateRating.reviewCount", updateList.get(3));
        updateQuery.append("aggregateRating.@type", updateList.get(4));
        updateQuery.append("bookFormat", updateList.get(5));
        updateQuery.append("edition", updateList.get(6));
        updateQuery.append("awards", updateList.get(7));
        updateQuery.append("description", updateList.get(8));

        BasicDBList parsedReviewArray = new BasicDBList();
        for (int i = 0; i < reviewArray.length(); i++) {
            JSONObject reviewObject = reviewArray.getJSONObject(i);
            parsedReviewArray.add(i, (DBObject) JSON.parse(reviewObject.toString()));
        }

        updateQuery.append("reviews", parsedReviewArray);

        BasicDBObject updateObject = new BasicDBObject("$set", updateQuery);
        bookTable.update(searchQuery, updateObject);
    }

    public void searchBook(String book) {
        if (bookExists(book)) {
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("name", book);
        } else {
            System.out.println("Sorry, there is no book with given attribute in database.");
        }
    }

    public DBObject getBook(String book) {

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", book);
        DBObject bookObject = bookTable.findOne(searchQuery);
        return bookObject;
    }
}
