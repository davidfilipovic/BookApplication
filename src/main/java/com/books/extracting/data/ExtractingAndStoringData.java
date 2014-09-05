/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.books.extracting.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author David
 */
public class ExtractingAndStoringData {

    String awards = "";
    String ratingValue = "";
    String ratingCount = "";
    String type = "";
    String isbn = "";
    String bookName = "";
    String numberOfPages = "";
    String inLanguage = "";
    String bookFormatType = "";
    String bookEdition = "";
    String author = "";
    String image = "";
    String description = "";
    String datePublished = "";
    String publisher = "";
    String bookSubTitle = "";
    String readOnlineLink = "";
    JSONArray reviewArray;

    public static Document retreiveDocumentPage(String requestURL) {

        Document document = null;
        try {
            document = Jsoup.connect(requestURL).timeout(15 * 1000).get();
        } catch (IOException ex) {
            Logger.getLogger(ExtractingAndStoringData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return document;
    }

    // prepare string for json converting based on given URL
    public static String prepareStringForJSONTransformation(URL url) {

        String inputLine;
        String jsonString = "";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((inputLine = bufferedReader.readLine()) != null) {
                jsonString += inputLine;
            }
            bufferedReader.close();
        } catch (IOException ex) {
            Logger.getLogger(ExtractingAndStoringData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonString;
    }

    // retreive review informations: name, date, review, rating
    public static ArrayList<String> reviewData(JSONObject reviewObject, String reviewURL) {

        ArrayList<String> reviewsInformation = new ArrayList();

        String reviewAuthor;
        String reviewDate;
        String reviewAuthorURL;
        String reviewBody;
        String reviewRating;

        try {
            reviewBody = reviewObject.getString("reviewBody");
        } catch (JSONException e) {
            reviewBody = "Marked it as to-read || Added it";
        }

        reviewAuthorURL = reviewObject.getString("author");
        reviewDate = reviewObject.getString("publishDate");
        reviewAuthor = "";
        String[] splitS = reviewAuthorURL.split("-");

        try {
            for (int i = 1; i < splitS.length; i++) {
                reviewAuthor += splitS[i].substring(0, 1).toUpperCase() + splitS[i].substring(1) + " ";
            }
        } catch (Exception e) {

        }

        Document reviewPageGoodreads = retreiveDocumentPage(reviewURL);
        try {
            Elements reviewElement = reviewPageGoodreads.getElementsByClass("staticStars");
            reviewRating = reviewElement.get(0).text();
        } catch (Exception e) {
            reviewRating = "No rating.";
        }

        reviewsInformation.add(reviewAuthor);
        reviewsInformation.add(reviewDate);
        reviewsInformation.add(reviewBody);
        reviewsInformation.add(reviewRating);

        return reviewsInformation;

    }

    public static ArrayList<String> retreiveReviewsInformation(JSONArray jsonArray, URL urlGoodreadsReviews, String reviewURL) throws JSONException {

        ArrayList<String> reviewsInformation = new ArrayList();

        String jsonStringReview = prepareStringForJSONTransformation(urlGoodreadsReviews);

        //json is generated differently every time
        JSONObject reviewObject;
        JSONObject objectR = new JSONObject(jsonStringReview);
        try {
            JSONObject reviewObjectMD = objectR.getJSONObject("md:item");
            reviewObject = reviewObjectMD.getJSONArray("@list").getJSONObject(0).getJSONObject("reviews");
            reviewsInformation = reviewData(reviewObject, reviewURL);
        } catch (JSONException e) {
            try {
                reviewObject = objectR.getJSONArray("@graph").getJSONObject(0);
                if (reviewObject.isNull("author")) {
                    reviewObject = objectR.getJSONArray("@graph").getJSONObject(1);
                }
                reviewsInformation = reviewData(reviewObject, reviewURL);
            } catch (JSONException ex) {
                System.out.println("No reviews found");
            }
        }

        return reviewsInformation;
    }

    private static JSONArray prepareReviewArray(JSONArray jsonArrayGoodreads) {

        String reviewAuthor;
        String reviewDate;
        String reviewBody;
        String reviewRating;

        JSONArray reviewArray = new JSONArray();

        ArrayList<String> listOfReviewsURLs = new ArrayList();

        try {
            JSONArray reviews = jsonArrayGoodreads.getJSONObject(1).getJSONArray("reviews");
            int counter = 0;
            JSONObject jsonObject;
            while (counter < 5) {
                try {
                    jsonObject = reviews.getJSONObject(counter);
                } catch (JSONException e) {
                    break;
                }
                listOfReviewsURLs.add(jsonObject.getString("url"));
                counter++;
            }
        } catch (JSONException e) {
            JSONObject reviews = jsonArrayGoodreads.getJSONObject(1).getJSONObject("reviews");
            listOfReviewsURLs.add(reviews.getString("url"));
        }

        for (String reviewURL : listOfReviewsURLs) {

            URL urlGoodreadsReviews = null;
            try {
                urlGoodreadsReviews = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri="
                        + reviewURL
                        + "&format=json&vocab_expansion=false&vocab_cache=true");
            } catch (MalformedURLException ex) {
                Logger.getLogger(ExtractingAndStoringData.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ArrayList<String> reviewInformations = retreiveReviewsInformation(jsonArrayGoodreads, urlGoodreadsReviews, reviewURL);
                if (!reviewInformations.isEmpty()) {
                    reviewAuthor = reviewInformations.get(0);
                    reviewDate = reviewInformations.get(1);
                    reviewBody = reviewInformations.get(2);
                    reviewRating = reviewInformations.get(3);
                    JSONObject reviewObject = new JSONObject();

                    reviewObject.put("@type", "Review");
                    reviewObject.put("author", reviewAuthor);
                    reviewObject.put("datePublished", reviewDate);
                    reviewObject.put("reviewBody", reviewBody);
                    reviewObject.put("reviewRating", reviewRating);

                    reviewArray.put(reviewObject);
                }
            } catch (JSONException e) {
            }
        }
        return reviewArray;
    }

    public static void prettyPrintJsonArray(JSONArray jsonArray) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            Object json = mapper.readValue(jsonArray.toString(), Object.class);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        } catch (IOException ex) {
            Logger.getLogger(ExtractingAndStoringData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void prettyPrintJsonObject(JSONObject jsonObject) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            Object json = mapper.readValue(jsonObject.toString(), Object.class);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        } catch (IOException ex) {
            Logger.getLogger(ExtractingAndStoringData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // return all links for every book on page
    public static ArrayList<String> retreiveLinksFromPage(Elements elements) {  //links for single book page

        ArrayList<String> listOfBooksURLs = new ArrayList();
        for (Element link : elements) {
            String linkHref = link.attr("href");
            listOfBooksURLs.add(linkHref);
        }
        return listOfBooksURLs;
    }

    // prepareJSONArray for elements md:item and @list
    public static JSONArray prepareJSONArray(String jsonString) throws JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject mdItem = jsonObject.getJSONObject("md:item");
        JSONArray jsonArray = mdItem.getJSONArray("@list");
        return jsonArray;
    }

    public URL returnPageInJSON(String site, String bookURL) {

        URL bookInHTML = null;
        try {
            bookInHTML = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri="
                    + site
                    + bookURL
                    + "&format=json&vocab_expansion=false&vocab_cache=true");
        } catch (MalformedURLException ex) {
            Logger.getLogger(ExtractingAndStoringData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookInHTML;
    }

    public void itEBooks() throws MalformedURLException {

        String itEBooksSite = "http://it-ebooks.info";
        ArrayList<String> tagList = new ArrayList(
                Arrays.asList("programming", "php", "java", "python", "javaScript", "ruby"));

        for (String tag : tagList) {
            for (int i = 1; i < 60; i++) {
                Document eBooks = retreiveDocumentPage("http://it-ebooks.info/search/?q=" + tag + "&type=title&page=" + i);
                Elements ebooksElements = eBooks.getElementsByAttribute("title");
                List<String> eBooksList = retreiveLinksFromPage(ebooksElements);
                try {
                    eBooksList = eBooksList.subList(4, 24);
                } catch (Exception e) {
                    break;
                }

                ArrayList<String> finalEBooksList = new ArrayList();
                for (int j = 0; j < eBooksList.size(); j += 2) {
                    finalEBooksList.add(eBooksList.get(j));
                }

                for (String eBookURL : finalEBooksList) {

                    URL ebooksBook = returnPageInJSON(itEBooksSite, eBookURL);
                    String jsonStringEBooks = prepareStringForJSONTransformation(ebooksBook);
                    JSONArray jsonArrayITEbooks = prepareJSONArray(jsonStringEBooks);

                    bookName = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "name");
                    bookName = prepareName(bookName);

                    datePublished = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "datePublished");
                    image = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "image");
                    author = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "author");
                    description = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "description");
                    inLanguage = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "inLanguage");

                    Document bookDocument = retreiveDocumentPage(itEBooksSite + eBookURL);
                    try {
                        Elements elementsSub = bookDocument.getElementsByTag("h3");
                        bookSubTitle = elementsSub.text();
                    } catch (Exception e) {
                        bookSubTitle = "";
                    }

                    Elements publisherElements = bookDocument.getElementsByAttributeValue("itemprop", "publisher");

                    publisher = publisherElements.text();
                    isbn = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "isbn");
                    readOnlineLink = "http://it-ebooks.info/read/" + eBookURL.substring(6);

                    if (!Database.getDatabaseObject().bookExists(bookName)) {
                        try {
                            createAndStoreBook();
                            System.out.println("IT E Books " + bookName);
                        } catch (Exception e) {
                            System.out.println("Can't store book");
                        }
                    } else {
                        System.out.println("Book exists");
                    }
                }
            }
        }
    }

    public void goodreadsBooks() throws MalformedURLException {

        String goodreadsSite = "https://www.goodreads.com";

        for (int i = 1; i < 25; i++) {
            Document goodreads = retreiveDocumentPage("https://www.goodreads.com/shelf/show/programming?page=" + i);
            Elements goodreadsElements = goodreads.getElementsByClass("bookTitle");
            ArrayList<String> goodreadsList = retreiveLinksFromPage(goodreadsElements);

            for (String bookURL : goodreadsList) {

                URL goodreadsBook = returnPageInJSON(goodreadsSite, bookURL);

                String jsonStringGoodreads = prepareStringForJSONTransformation(goodreadsBook);

                try {
                    JSONArray jsonArrayGoodreads = prepareJSONArray(jsonStringGoodreads);

                    reviewArray = new JSONArray();
                    if (jsonArrayGoodreads.getJSONObject(1).has("reviews")) {
                        try {
                            reviewArray = prepareReviewArray(jsonArrayGoodreads);
                        } catch (JSONException e) {
                        }
                    }


                    author = getAuthorNameOrNames(jsonArrayGoodreads);
                    ratingValue = getSpecificAttributeFromJSON(jsonArrayGoodreads, 0, "ratingValue");
                    ratingCount = getSpecificAttributeFromJSON(jsonArrayGoodreads, 0, "ratingCount");
                    type = getSpecificAttributeFromJSON(jsonArrayGoodreads, 0, "@type");
                    numberOfPages = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "numberOfPages");
                    inLanguage = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "inLanguage");
                    bookFormatType = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "bookFormatType");
                    bookEdition = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "bookEdition");
                    isbn = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "isbn");
                    image = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "image");

                    try {
                        Document descriptionPage = retreiveDocumentPage(goodreadsSite + bookURL);
                        Element descriptionElement = descriptionPage.getElementById("description");
                        description = descriptionElement.text();
                    } catch (Exception e) {
                    }

                    try {
                        description = description.substring(0, description.length() - 6);
                    } catch (Exception e) {
                    }

                    try {
                        awards = jsonArrayGoodreads.getJSONObject(1).getString("awards");
                    } catch (JSONException e) {
                        awards = "Without awards";
                    }

                    bookName = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "name");
                    bookName = bookName.substring(7, bookName.length() - 1);
                    bookName = prepareName(bookName);

                    if (Database.getDatabaseObject().bookExists(bookName)) {
                        System.out.println("Update " + bookName);
                        ArrayList<String> updateAttributeList = new ArrayList(
                                Arrays.asList(inLanguage, numberOfPages, ratingValue, ratingCount, type, bookFormatType, bookEdition, awards, description));
                        Database.getDatabaseObject().updateBook(updateAttributeList, bookName, reviewArray);
                    } else {
                        System.out.println("Goodreads " + bookName);
                        createAndStoreBook();
                    }
                } catch (JSONException e) {
                    System.out.println("Can't retreive book");
                }
            }
        }
    }

    public void createAndStoreBook() {

        JSONObject ratingObject = new JSONObject();
        ratingObject.put("@type", type);
        ratingObject.put("ratingValue", ratingValue);
        ratingObject.put("reviewCount", ratingCount);

        JSONObject bookForStoring = new JSONObject();
        bookForStoring.put("@context", "http://schema.org");
        bookForStoring.put("@type", "WebPage");
        bookForStoring.put("aggregateRating", ratingObject);
        bookForStoring.put("author", author);
        bookForStoring.put("bookFormat", bookFormatType);
        bookForStoring.put("datePublished", datePublished);
        bookForStoring.put("image", image);
        bookForStoring.put("description", description);
        bookForStoring.put("inLanguage", inLanguage);
        bookForStoring.put("isbn", isbn);
        bookForStoring.put("name", bookName);
        bookForStoring.put("subTitle", bookSubTitle);
        bookForStoring.put("numberOfPages", numberOfPages);
        bookForStoring.put("edition", bookEdition);
        bookForStoring.put("awards", awards);
        bookForStoring.put("publisher", publisher);
        bookForStoring.put("readOnlineLink", readOnlineLink);
        bookForStoring.put("reviews", reviewArray);

        Database.getDatabaseObject().saveBook(bookForStoring);
    }

    public String prepareName(String name) {

        String prepareName = name;

        if (name.contains("#")) {                         //browser can not execute uri with # and ++ properly
            prepareName = name.replaceAll("#", " sharp");
        }

        if (name.contains("++")) {
            prepareName = prepareName.replace("+", " plus");
        }

        if (name.contains("(")) {
            prepareName = prepareName.replaceAll("\\(.*?\\)", ""); // the same goes for the parentheses, they are just inner mark for the publisher, 
        }                                                          // therefore not relevant to the book name 

        return prepareName;

    }

    public static String getAuthorNameOrNames(JSONArray jsonArray) throws JSONException {

        String author;
        try {
            author = jsonArray.getJSONObject(1).getJSONObject("author").getString("name");
        } catch (JSONException e) {
            JSONArray authorArray = jsonArray.getJSONObject(1).getJSONObject("author").getJSONArray("name");
            ArrayList<String> authorArrayList = new ArrayList();
            for (int i = 0; i < authorArray.length(); i++) {
                Object jsonObject = authorArray.get(i);
                authorArrayList.add(jsonObject.toString());
            }
            author = authorArrayList.toString();
            author = author.substring(1, author.length() - 1);
        }
        return author;
    }

    public static String getSpecificAttributeFromJSON(JSONArray jsonArray, int objectPosition, String objectName) {

        String value;
        try {
            value = jsonArray.getJSONObject(objectPosition).getString(objectName);
        } catch (JSONException e) {
            value = "";
        }
        return value;
    }

}
