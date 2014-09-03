/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.books.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.json.JSONArray;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author David
 */
@Document(collection = "user")
public class Book {

    @Id
    private String id;
    private String awards = "";
    private String ratingValue = "";
    private String ratingCount = "";
    private String type = "";
    private String isbn = "";
    private String name = "";
    private String numberOfPages = "";
    private String inLanguage = "";
    private String bookFormatType = "";
    private String bookEdition = "";
    private String author = "";
    private String image = "";
    private String description = "";
    private String datePublished = "";
    private String publisher = "";
    private String bookSubTitle = "";
    private String readOnlineLink = "";
    private ArrayList reviews;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList reviews) {
        this.reviews = reviews;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String bookName) {
        this.name = bookName;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getInLanguage() {
        return inLanguage;
    }

    public void setInLanguage(String inLanguage) {
        this.inLanguage = inLanguage;
    }

    public String getBookFormatType() {
        return bookFormatType;
    }

    public void setBookFormatType(String bookFormatType) {
        this.bookFormatType = bookFormatType;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBookSubTitle() {
        return bookSubTitle;
    }

    public void setBookSubTitle(String bookSubTitle) {
        this.bookSubTitle = bookSubTitle;
    }

    public String getReadOnlineLink() {
        return readOnlineLink;
    }

    public void setReadOnlineLink(String readOnlineLink) {
        this.readOnlineLink = readOnlineLink;
    }

}
