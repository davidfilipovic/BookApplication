///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.books.extracting.data;
//
//import java.net.MalformedURLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletContextEvent;
//
///**
// *
// * @author David
// */
//public class ExtractionListener implements javax.servlet.ServletContextListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        try {
//            ExtractingAndStoringData exstdObject = new ExtractingAndStoringData();
//            exstdObject.itEBooks();
//            exstdObject.goodreadsBooks();
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(ExtractionListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//    }
//
//}
