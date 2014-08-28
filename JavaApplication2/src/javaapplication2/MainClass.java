/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.IOException;
import java.net.MalformedURLException;
import org.json.JSONException;

/**
 *
 * @author David
 */
public class MainClass {

    public static void main(String[] args) throws MalformedURLException, IOException, JSONException {
        
        ExtractingAndStoringData object = new ExtractingAndStoringData();
        object.goodreadsBooks();

    }

}
