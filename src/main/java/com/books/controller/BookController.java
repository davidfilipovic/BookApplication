package com.books.controller;

import com.books.model.Book;
import com.books.service.BookService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
//@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String getBookList(ModelMap model) {
        model.addAttribute("bookList", bookService.listBook());
        model.addAttribute("pubList", bookService.publishers());
        return "output";
    }

    @RequestMapping(value = "/book/find", method = RequestMethod.GET)
    public String getBookOrBooks(@ModelAttribute Book book, ModelMap model) {
        try {
            List<Book> searchBook = bookService.findBookOrBooksByAuthorOrByTitle(book.getName());
            model.addAttribute("books", searchBook);
        } catch (Exception e) {

        }
        return "output";
        //    return new RedirectView("/HelloSpringWithMongoDB-master/person");
    }

    @RequestMapping(value = "/book/pubBooks{pub}", method = RequestMethod.GET)
    public String getBookOrBooksByPublisher(@ModelAttribute Book book, ModelMap model) {
        try {
            List<Book> searchBooks = bookService.getAllBooksByPublisher(book.getPublisher());
            model.addAttribute("pubBooks", searchBooks);
        } catch (Exception e) {

        }
        return "output";
        //    return new RedirectView("/HelloSpringWithMongoDB-master/person");
    }
}
