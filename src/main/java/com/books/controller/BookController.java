package com.books.controller;

import com.books.model.Book;
import com.books.model.BookJSONObject;
import com.books.model.CheckBoxClass;
import com.books.service.BookService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(@ModelAttribute("book") Book book, BindingResult result, ModelMap model) {
        model.addAttribute("bookList", bookService.listBook());

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

    public ModelMap returnModel(ModelMap model) {
        ModelMap addedModel = null;
        model.addAttribute("pubList", bookService.publishers());
        model.addAttribute("years", bookService.years());
        return addedModel;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getElements(@ModelAttribute Book book, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        try {
            ModelMap addedModel = returnModel(model);
            model.addAttribute("check", new CheckBoxClass());
        } catch (Exception e) {
        }
        return "search";
    }

    @RequestMapping(value = "/search/resp{publisher}", method = RequestMethod.GET)
    public String getBookOrBooksByPublisher(@ModelAttribute("publisher") String publisher,
            HttpServletRequest request, ModelMap model, @ModelAttribute CheckBoxClass cbc) {
        try {
            List<Book> searchBooksPub = bookService.getAllBooksByPublisher(publisher);
            ModelMap addedModel = returnModel(model);
            model.addAttribute("pubBooks", searchBooksPub);
            model.addAttribute("publisher", publisher);
            HttpSession session = request.getSession();
            session.setAttribute("publisher", publisher);
            String year = (String) session.getAttribute("year");
            model.addAttribute("yearSession", year);
            model.addAttribute("check", cbc);

            List<Book> findBooksForYearAndPublisher = bookService.getAllBooksByPublisherForYear(year, publisher);
            model.addAttribute("booksForYearAndPublisher", findBooksForYearAndPublisher);

            List<Book> readOnlineBooks = bookService.getAllReadOnlineBooksYear(year);
            model.addAttribute("booksReadOnlineYear", readOnlineBooks);
        } catch (Exception e) {
        }
        return "search";
    }
    
    @RequestMapping(value = "/search/resp{publisher}", method = RequestMethod.POST)
    public String getBookOrBooksByPublisherP(@ModelAttribute("publisher") String publisher,
            HttpServletRequest request, ModelMap model, @ModelAttribute CheckBoxClass cbc) {
        try {
            List<Book> searchBooksPub = bookService.getAllBooksByPublisher(publisher);
            ModelMap addedModel = returnModel(model);
            model.addAttribute("pubBooks", searchBooksPub);
            model.addAttribute("publisher", publisher);
            HttpSession session = request.getSession();
            session.setAttribute("publisher", publisher);
            String year = (String) session.getAttribute("year");
            model.addAttribute("yearSession", year);
            model.addAttribute("check", cbc);

            List<Book> findBooksForYearAndPublisher = bookService.getAllBooksByPublisherForYear(year, publisher);
            model.addAttribute("booksForYearAndPublisher", findBooksForYearAndPublisher);

            List<Book> readOnlineBooks = bookService.getAllReadOnlineBooksPublisher(publisher);
            model.addAttribute("booksReadOnlinePublisher", readOnlineBooks);
        } catch (Exception e) {
        }
        return "search";
    }

    @RequestMapping(value = "/search/resy{year}", method = RequestMethod.GET)
    public String getBookOrBooksByYear(@ModelAttribute("year") String year, @ModelAttribute Book book,
            ModelMap model, HttpServletRequest request, @ModelAttribute CheckBoxClass cbc) {
        try {
            List<Book> searchBooksYear = bookService.getAllBooksByYear(year);
            ModelMap addedModel = returnModel(model);
            model.addAttribute("yearBooks", searchBooksYear);
            model.addAttribute("year", year);
            HttpSession session = request.getSession();
            session.setAttribute("year", year);
            String publisher = (String) session.getAttribute("publisher");
            model.addAttribute("publisherSession", publisher);
            model.addAttribute("check", cbc);

            List<Book> findBooksForYearAndPublisher = bookService.getAllBooksByPublisherForYear(year, publisher);
            model.addAttribute("booksForYearAndPublisher", findBooksForYearAndPublisher);

            List<Book> readOnlineBooks = bookService.getAllReadOnlineBooksYear(year);
            model.addAttribute("booksReadOnlineYear", readOnlineBooks);

        } catch (Exception e) {
        }
        return "search";
    }

    @RequestMapping(value = "/search/resy{year}", method = RequestMethod.POST)
    public String getBookOrBooksByYearP(@ModelAttribute("year") String year, @ModelAttribute Book book,
            ModelMap model, HttpServletRequest request, @ModelAttribute CheckBoxClass cbc) {
        try {
            List<Book> searchBooksYear = bookService.getAllBooksByYear(year);
            ModelMap addedModel = returnModel(model);
            model.addAttribute("yearBooks", searchBooksYear);
            model.addAttribute("year", year);
            HttpSession session = request.getSession();
            session.setAttribute("year", year);
            String publisher = (String) session.getAttribute("publisher");
            model.addAttribute("publisherSession", publisher);
            model.addAttribute("check", cbc);

            List<Book> findBooksForYearAndPublisher = bookService.getAllBooksByPublisherForYear(year, publisher);
            model.addAttribute("booksForYearAndPublisher", findBooksForYearAndPublisher);

            List<Book> readOnlineBooks = bookService.getAllReadOnlineBooksYear(year);
            model.addAttribute("booksReadOnlineYear", readOnlineBooks);

        } catch (Exception e) {
        }
        return "search";
    }

    @RequestMapping(value = "book/find{name}", method = RequestMethod.GET)
    public String getBookByLink(@ModelAttribute Book book, @ModelAttribute("name") String name, ModelMap model) {
        try {
            List<Book> searchBooks = bookService.findBookOrBooksByAuthorOrByTitle(book.getName());
            if (!searchBooks.contains(null)) {
                model.addAttribute("books", searchBooks);
            }
        } catch (Exception e) {
        }
        return "book";
    }
}
