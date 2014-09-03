/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.books.controller;

import com.books.service.BookService;

/**
 *
 * @author David
 */
public class Main {
    public static void main(String[] args) {
        BookService bs = new BookService();

        bs.getJSONArray("Python Network Programming Book");
    }
}
