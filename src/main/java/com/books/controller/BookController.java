package com.books.controller;

import com.books.model.Book;
import com.books.model.BookJSONObject;
import com.books.service.BookService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

//    @RequestMapping(value = "/book", method = RequestMethod.GET)
//    public String getBookList(ModelMap model) {
    @RequestMapping(value = "/output", method = RequestMethod.GET)
    public String printWelcome(@ModelAttribute("book") Book book, BindingResult result, ModelMap model) {
        model.addAttribute("bookList", bookService.listBook());
        model.addAttribute("pubList", bookService.publishers());
        return "output";
    }

    @RequestMapping("*")
    public String hello(HttpServletRequest request) {
        System.out.println(request.getServletPath());
        return "index";
    }

    @RequestMapping(value = "/springPaginationDataTables", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String springPaginationDataTables(ModelMap model)
            throws IOException {

        List<Book> bookList = bookService.listBook();

        BookJSONObject bookJsonObject = new BookJSONObject();
        bookJsonObject.setiTotalDisplayRecords(bookList.size());
        bookJsonObject.setiTotalRecords(bookList.size());
        bookJsonObject.setAaData(bookList);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(bookJsonObject);

        return json;
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String getBookList(@ModelAttribute Book book, ModelMap model) {
        return "book";
    }

//    @RequestMapping(value = "/book/find", method = RequestMethod.GET)
//    public String getBookOrBooks(@ModelAttribute Book book, ModelMap model) {
//        try {
//            List<Book> searchBook = bookService.findBookOrBooksByAuthorOrByTitle(book.getName());
//            model.addAttribute("books", searchBook);
//        } catch (Exception e) {
//
//        }
//        return "book";
//    }
    @RequestMapping(value = "/book/pubBooks{pub}", method = RequestMethod.GET)
    public String getBookOrBooksByPublisher(@ModelAttribute Book book, ModelMap model) {
        try {
            List<Book> searchBooks = bookService.getAllBooksByPublisher(book.getPublisher());
            model.addAttribute("pubBooks", searchBooks);
        } catch (Exception e) {

        }
        return "output";
    }

    @RequestMapping(value = "/book/find{book}", method = RequestMethod.GET)
    public String getBook(@ModelAttribute Book book, ModelMap model) {
        try {
            List<Book> searchBooks = bookService.findBookOrBooksByAuthorOrByTitle(book.getName());
            model.addAttribute("books", searchBooks);
        } catch (Exception e) {
        }
        return "book";
    }
}
