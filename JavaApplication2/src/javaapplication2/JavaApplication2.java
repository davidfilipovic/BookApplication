/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
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
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject));
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
    public static JSONArray prepareJSONArray(String jsonString) {

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

//                URL goodreadsBook = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri=https%3A%2F%2Fwww.goodreads.com%2Fbook%2Fshow%2F"
//                        + s
//                        + "&format=json&vocab_expansion=false&vocab_cache=true");

                URL goodreadsBook = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri=https%3A%2F%2Fwww.goodreads.com%2Fbook%2Fshow%2F22578475-beginning-java-8-fundamentals%3Ffrom_search%3Dtrue&format=json&vocab_expansion=false&vocab_cache=true");
                String jsonStringGoodreads = prepareStringForJSONTransformation(goodreadsBook);

               // URL ebooksBook = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri=http%3A%2F%2Fit-ebooks.info%2Fbook%2F2537%2F&format=json&vocab_expansion=false&vocab_cache=true");
                URL ebooksBook = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri=http%3A%2F%2Fit-ebooks.info%2Fbook%2F3658%2F&format=json&vocab_expansion=false&vocab_cache=true");
                String jsonStringEBooks = prepareStringForJSONTransformation(ebooksBook);

                JSONArray jsonArrayITEbooks = prepareJSONArray(jsonStringEBooks);
                JSONArray jsonArrayGoodreads = prepareJSONArray(jsonStringGoodreads);

                ArrayList<String> listOfReviewsURLs = new ArrayList<>();
                if(jsonArrayGoodreads.getJSONObject(1).has("reviews")){
                    System.out.println("je");
                }
//                JSONArray reviews = jsonArrayGoodreads.getJSONObject(1).getJSONArray("reviews");
//                for (int i = 0; i<reviews.length(); i++) {
//                    JSONObject jsonObject = reviews.getJSONObject(i);
//                    listOfReviewsURLs.add(jsonObject.getString("url"));
//                }

                URL urlGoodreadsReviews = new URL("http://www.w3.org/2012/pyMicrodata/extract?uri=https%3A%2F%2Fwww.goodreads.com%2Freview%2Fshow%2F947835070&format=json&vocab_expansion=false&vocab_cache=true");
              
                String jsonStringReview = prepareStringForJSONTransformation(urlGoodreadsReviews);
                String reviewBody;                 //json se generise razlicito svaki put
                String reviewAuthorURL;
                String reviewDate;
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
                String reviewAuthor = "";
                String[] splitS = reviewAuthorURL.split("-");
                for (int i = 1; i < splitS.length; i++) {
                    reviewAuthor += splitS[i].substring(0, 1).toUpperCase() + splitS[i].substring(1) + " ";
                }

                Document reviewPageGoodreads = retreiveDocumentPage("https://www.goodreads.com/review/show/437438644");
                Elements reviewElement = reviewPageGoodreads.getElementsByClass("staticStars");

                String reviewRating = reviewElement.get(0).text();

                for (String reviewUrl : listOfReviewsURLs) {
                }

                String awards;
                String ratingValue = null;
                String reviewCount = null;
                String type = null;
                String isbn = null;
                String name = null;
                String numberOfPages = null;
                String inLanguage = null;
                String bookFormat = null;
                String edition = null;
                String author = null;
                String image = null; 
                try{
                ratingValue = jsonArrayGoodreads.getJSONObject(0).getString("ratingValue");
               reviewCount = jsonArrayGoodreads.getJSONObject(0).getString("ratingCount");
                type = jsonArrayGoodreads.getJSONObject(0).getString("@type");
                isbn = jsonArrayGoodreads.getJSONObject(1).getString("isbn");
                 name = jsonArrayGoodreads.getJSONObject(1).getString("name");
                 numberOfPages = jsonArrayGoodreads.getJSONObject(1).getString("numberOfPages");
                inLanguage = jsonArrayGoodreads.getJSONObject(1).getString("inLanguage");
                bookFormat = jsonArrayGoodreads.getJSONObject(1).getString("bookFormatType");
             edition = jsonArrayGoodreads.getJSONObject(1).getString("bookEdition");
                author = jsonArrayGoodreads.getJSONObject(1).getJSONObject("author").getString("name");
                image = jsonArrayGoodreads.getJSONObject(1).getString("image");
                } catch (JSONException e){
                    System.out.println("asd");
                }
                try {
                    awards = jsonArrayGoodreads.getJSONObject(1).getString("awards");
                } catch (JSONException e) {
                    awards = "Without awards";
                }

                String publisherLink = jsonArrayITEbooks.getJSONObject(0).getString("publisher");
                Document publisherDocument = retreiveDocumentPage(publisherLink);
                Elements publisherElements = publisherDocument.getElementsByAttributeValue("style", "margin-bottom:20px;");
                String publisher = "";
                for (Element element : publisherElements) {
                    publisher = element.text();
                }

                String description = jsonArrayITEbooks.getJSONObject(0).getString("description");

                JsonObject data = Json.createObjectBuilder()
                        .add("@context", "http://schema.org")
                        .add("@type", "WebPage")
                        .add("aggregateRating", Json.createObjectBuilder()
                                .add("@type", type)
                                .add("ratingValue", ratingValue)
                                .add("reviewCount", reviewCount))
                        .add("description", description)
                        .add("author", author)
                        .add("bookFormat", bookFormat)
                        .add("image", image)
                        .add("inLanguage", inLanguage)
                        .add("isbn", isbn)
                        .add("name", name)
                        .add("numberOfPages", numberOfPages)
                        .add("edition", edition)
                        .add("awards", awards)
                        .add("publisher", publisher)
                        .add("reviews", Json.createObjectBuilder()
                                .add("@type", "Review")
                                .add("author", reviewAuthor)
                                .add("datePublished", reviewDate)
                                .add("reviewBody", reviewBody)
                                .add("reviewRating", reviewRating))
                        .build();

                DBObject dbo = (DBObject) JSON.parse(data.toString());
                table.insert(dbo);
            }
        } catch (UnknownHostException | MongoException e) {
        }

    }
}
