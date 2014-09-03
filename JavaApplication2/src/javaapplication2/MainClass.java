/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author David
 */
public class MainClass {

    public static void main(String[] args) throws MalformedURLException, IOException, JSONException {

        ExtractingAndStoringData object = new ExtractingAndStoringData();
//        object.goodreadsBooks();
//       object.itEBooks();
        //   Database.getDatabaseObject().prettyPrintAllJSONObjectFromDB(); 

        BasicDBObject bookDB =  Database.getDatabaseObject().getBook("Python Network Programming Book");
     //   JSONArray reviews = (JSONArray) bookDB.getJ
      //  System.out.println(reviews);

    }

}
