/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
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
public class JavaApplication2 {

    public static Document retreiveDocumentPage(String url) {

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException ex) {
            Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonString;
    }

    // retreive review informations: name, date, review, rating
    public static ArrayList<String> retreiveReviewsInformation(JSONArray jsonArray, URL urlGoodreadsReviews, String reviewURL) throws JSONException {

        String reviewAuthor;
        String reviewDate;
        String reviewAuthorURL;
        String reviewBody;
        String reviewRating;

        ArrayList<String> reviewsInformation = new ArrayList<>();

        String jsonStringReview = prepareStringForJSONTransformation(urlGoodreadsReviews);

        //json se generise razlicito svaki put
        JSONObject reviewObject;
        JSONObject objectR = new JSONObject(jsonStringReview);
        try {
            JSONObject reviewObjectMD = objectR.getJSONObject("md:item");
            reviewObject = reviewObjectMD.getJSONArray("@list").getJSONObject(0).getJSONObject("reviews");
        } catch (JSONException e) {
            reviewObject = objectR.getJSONArray("@graph").getJSONObject(0);
            if (reviewObject.isNull("author")) {
                reviewObject = objectR.getJSONArray("@graph").getJSONObject(1);
            }
        }

        reviewBody = reviewObject.getString("reviewBody");
        reviewAuthorURL = reviewObject.getString("author");
        reviewDate = reviewObject.getString("publishDate");
        reviewAuthor = "";
        String[] splitS = reviewAuthorURL.split("-");
        for (int i = 1; i < splitS.length; i++) {
            reviewAuthor += splitS[i].substring(0, 1).toUpperCase() + splitS[i].substring(1) + " ";
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

    public static void prettyPrintJsonArray(JSONArray jsonArray) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            Object json = mapper.readValue(jsonArray.toString(), Object.class);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        } catch (IOException ex) {
            Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void prettyPrintJsonObject(JSONObject jsonObject) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            Object json = mapper.readValue(jsonObject.toString(), Object.class);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        } catch (IOException ex) {
            Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // return all links for every book on page
    public static ArrayList<String> retreiveLinksFromPage(Elements elements) {  //links for single book page

        ArrayList<String> listOfBooksURLs = new ArrayList<>();
        for (Element link : elements) {
            String linkHref = link.attr("href");
            listOfBooksURLs.add(linkHref.substring(11));
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

    public static void main(String[] args) throws MalformedURLException, IOException, JSONException {

        Document goodreads = retreiveDocumentPage("https://www.goodreads.com/shelf/show/it?page=2");
        Elements goodreadsElements = goodreads.getElementsByClass("bookTitle");

        Document ebooks = retreiveDocumentPage("http://www.ebooks.com/s/ref=lp_3839_pg_2?rh=n%3A283155%2Cn%3A%211000%2Cn%3A5%2Cn%3A3839&page=2&ie=UTF8&qid=1408207294");
        Elements ebooksElements = ebooks.getElementsByClass("title");

        //  ArrayList<String> ebooksList = retreiveLinksFromPage(ebooksElements);
        ArrayList<String> goodreadsList = retreiveLinksFromPage(goodreadsElements);

        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("testdb");
            DBCollection table = db.getCollection("user");
            BasicDBObject document = new BasicDBObject();

            for (String s : goodreadsList.subList(0, 1)) {

                URL goodreadsBook = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri=https%3A%2F%2Fwww.goodreads.com%2Fbook%2Fshow%2F"
                        + s
                        + "&format=json&vocab_expansion=false&vocab_cache=true");
                //  URL goodreadsBook = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri=https%3A%2F%2Fwww.goodreads.com%2Fbook%2Fshow%2F31173.Villette%3Fref%3Dru_lihp_up_rs_0_mclk%26uid%3D1848568670&format=json&vocab_expansion=false&vocab_cache=true");
                String jsonStringGoodreads = prepareStringForJSONTransformation(goodreadsBook);

                // URL ebooksBook = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri=http%3A%2F%2Fit-ebooks.info%2Fbook%2F2537%2F&format=json&vocab_expansion=false&vocab_cache=true");
                URL ebooksBook = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri=http%3A%2F%2Fit-ebooks.info%2Fbook%2F3658%2F&format=json&vocab_expansion=false&vocab_cache=true");
                String jsonStringEBooks = prepareStringForJSONTransformation(ebooksBook);

                JSONArray jsonArrayITEbooks = prepareJSONArray(jsonStringEBooks);
                JSONArray jsonArrayGoodreads = prepareJSONArray(jsonStringGoodreads);

                String reviewAuthor = "";
                String reviewDate = "";
                String reviewBody = "";
                String reviewRating = "";
                JSONArray reviewArray = new JSONArray();
                if (jsonArrayGoodreads.getJSONObject(1).has("reviews")) {

                    ArrayList<String> listOfReviewsURLs = new ArrayList<>();

                    try {
                        JSONArray reviews = jsonArrayGoodreads.getJSONObject(1).getJSONArray("reviews");
                        for (int i = 0; i < reviews.length(); i++) {
                            JSONObject jsonObject = reviews.getJSONObject(i);
                            listOfReviewsURLs.add(jsonObject.getString("url"));
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
                            Logger.getLogger(JavaApplication2.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        ArrayList<String> reviewInformations = retreiveReviewsInformation(jsonArrayGoodreads, urlGoodreadsReviews, reviewURL);
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
                }

                String awards;
                String ratingValue;
                String reviewCount;
                String type;
                String isbn;
                String bookName;
                String numberOfPages;
                String inLanguage;
                String bookFormat;
                String edition;
                String author = "";
                String image;
                String descriptionGoodreads;

                ratingValue = getSpecificAttributeFromJSON(jsonArrayGoodreads, 0, "ratingValue");
                reviewCount = getSpecificAttributeFromJSON(jsonArrayGoodreads, 0, "ratingCount");
                type = getSpecificAttributeFromJSON(jsonArrayGoodreads, 0, "@type");
                isbn = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "isbn");
                bookName = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "name");
                numberOfPages = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "numberOfPages");
                inLanguage = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "inLanguage");
                bookFormat = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "bookFormatType");
                edition = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "bookEdition");
                image = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "image");

                author = getAuthorNameOrNames(jsonArrayGoodreads);

                Document descriptionPage = retreiveDocumentPage("https://www.goodreads.com/book/show/7094569-feed");
                Element descriptionElement = descriptionPage.getElementById("description");
                descriptionGoodreads = descriptionElement.text();
                descriptionGoodreads = descriptionGoodreads.substring(0, descriptionGoodreads.length() - 6);

                try {
                    awards = jsonArrayGoodreads.getJSONObject(1).getString("awards");
                } catch (JSONException e) {
                    awards = "Without awards";
                }

                String publisherLink = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "publisher");
                Document publisherDocument = retreiveDocumentPage(publisherLink);
                Elements publisherElements = publisherDocument.getElementsByAttributeValue("style", "margin-bottom:20px;");
                String publisher = "";
                for (Element element : publisherElements) {
                    publisher = element.text();
                }
                //      String descriptionITEbooks = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "description");
                String datePublished = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "datePublished");

                String subtitleITEbooks;
                Document subtitle = retreiveDocumentPage("http://it-ebooks.info/book/3169/");
                try {
                    Elements elementsSub = subtitle.getElementsByTag("h3");
                    subtitleITEbooks = elementsSub.text();
                } catch (Exception e) {
                    subtitleITEbooks = "";
                }
                JSONObject ratingObject = new JSONObject();
                ratingObject.put("@type", type);
                ratingObject.put("ratingValue", ratingValue);
                ratingObject.put("reviewCount", reviewCount);
                JSONObject bookForStoring = new JSONObject();
                bookForStoring.put("@context", "http://schema.org");
                bookForStoring.put("@type", "WebPage");
                bookForStoring.put("aggregateRating", ratingObject);
                bookForStoring.put("author", author);
                bookForStoring.put("bookFormat", bookFormat);
                //     bookForStoring.put("datePublished", datePublished)
                bookForStoring.put("image", image);
                bookForStoring.put("description", descriptionGoodreads);
                bookForStoring.put("inLanguage", inLanguage);
                bookForStoring.put("isbn", isbn);
                bookForStoring.put("name", bookName);
                bookForStoring.put("numberOfPages", numberOfPages);
                bookForStoring.put("edition", edition);
                bookForStoring.put("awards", awards);
                //     bookForStoring.put("publisher", publisher)
                bookForStoring.put("reviews", reviewArray);
                     

                   saveBook(bookForStoring, table);
                //  prettyPrintJSONObjectFromDB(table);
            }
        } catch (UnknownHostException | MongoException e) {
        }
    }

    public static String getAuthorNameOrNames(JSONArray jsonArray) throws JSONException {

        String author;
        try {
            author = jsonArray.getJSONObject(1).getJSONObject("author").getString("name");
        } catch (JSONException e) {
            JSONArray authorArray = jsonArray.getJSONObject(1).getJSONObject("author").getJSONArray("name");
            ArrayList<String> authorArrayList = new ArrayList<>();
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

    public static void prettyPrintJSONObjectFromDB(DBCollection collection) {

        DBCursor cursor = collection.find();
        DBObject row = null;
        while (cursor.hasNext()) {
            row = cursor.next();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(row);
        System.out.println(json);
    }

    public static void saveBook(JSONObject data, DBCollection collection) {

        DBObject dbo = (DBObject) JSON.parse(data.toString());
        collection.insert(dbo);
    }
}
