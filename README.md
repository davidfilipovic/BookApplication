Book Application created using Spring MVC Framework and MongoDB in Java

## 1. Details about the project

The main idea of this project is to acquire data from the different sites about books into one document, which will be then presented as integrated information about one specific book. Besides that, application provides a set of search options, for quering database with suplied attributes. Books are crawled from the [Goodreads](https://www.goodreads.com) and from [IT E Books](http://it-ebooks.info) site, and then stored in the local repository (MongoDB). Microdata from the book pages is gathered using [Microdata to RDF Distiller](http://www.w3.org/2012/pyMicrodata/), and the output format is set to JSON LD. Schema used for storing books is schema:Book. 

The following phases are recognized through application workflow: 

* A web crawler is created, which then parsers data from the books sites by extracting microdata, and by direct crawling of the page, using specific selectors
* Extracted data is then transformed into JSON based on schema:Book 
* Data is persisted into MongoDB
* A web project enables data quering and their presentation

## 2. Domain model

According to schema:Book, the following domain model has been created: 
![Picture 1 - Domain model](DomainModel.jpg)

Class Book contains the most of the information about specified book. Among others, there is information about book name, author, date published, publisher, number of pages, book format, etc. 

Class AggregateRating contains information about rating value and review count, and in array review there are informations about reviews for the book, like author name, review body, date published and review rating.  

## 3. Solution

The final product is application which crawles data from sites [Goodreads](https://www.goodreads.com) and [IT E Books](http://it-ebooks.info), and stores them into local repository in JSON format. By implementing Spring Framework, data can be accessed and queried in web browser. 

When application starts, the fist page, **/index**, shows all books that exist in database. User can choose number of books to be shown in page, and in search box user can search books by title, author or publisher. When user clicks on the book link, page **/book** presents all information about that book. Depending on whether that book has been updated by another site or not, it may show more or less information. For example, if the book has been crawled from the site IT E Books, but didn't get updated from the site Goodreads, user can see basic information about book, like book author, name, date published, link for reading book online (if book contains such link), etc. On the other hand, if the book has been updated by site Goodreads, user can notice book reviews, book rating, rating count, number of pages, ets. 

On **/book** page, user can search for specific book or author, which will be then shown on the same page. From **/index** page, user can navigate to **/search** page which conatins list of all publishers, as well as list of all years available. When user chooses publisher, table shows all books by that publisher, and when he chooses year, table shows all book by that publisher which are published in that year. Also, user can choose option to show only books which contains link for reading book online. 

The following picture presents **/index** page: 
![Picture 2 - Index page](index-page.png)

Here is **/book** page: 
![Picture 3 - Book page](book-page.png)
 
## 4. Used technologies and instaling instructions

 This project has been created as a Maven project by using Spring MVC Framework in Java.

 For this application, I've used **mongoDB**, which is NoSQL database, and presents cross-platform document-oriented database.
 
  To start it, get mongo database from this [link](http://www.mongodb.org), install it, then navigate to *../bin/mongodb* and wait until it makes connection to the localhost. To start a command line, run *../bin/mongo* file. Simple commands would be **use [database name]** for choosing a database to work with, and **db.[collection.name].find()** for showing all documents for selected collection. 
  
  To crawl data from the web page, there is ExtractionListener class which implements ServletContextListener. ExtractionListener enables automatic gathering of the data, and starts crawling when the application starts. To disable ExtractionListener, simply comment listener tag in web.xml file (this is set by default). Notice that crawling of data may take a while, depending on internet connection speed. 
  
  Other way to insert data into application is to copy database from the root of application to MongoDB default data folder (c:/data), before mongodb.exe is started. This will automaticaly insert all books into MongoBD, and application is ready for use. 
  
 Dependencies: 
 
 [JSON for Java](http://www.json.org/java/). JSON is a light-weight, language independent, data interchange format. The files in this package implement JSON encoders/decoders in Java. It also includes the capability to convert between JSON and XML, HTTP headers, Cookies, and CDL. [Gson](https://code.google.com/p/google-gson/) is also used whithin the project which presents open source Java library for serializing and deserializing Java objects to (and from) JSON.
 
 [Jsoup library](http://jsoup.org). Jsoup is a Java library for working with real-world HTML. It provides a very convenient API for extracting and manipulating data, using the best of DOM, CSS, and jquery-like methods. It is excelent and easy to implement jsoup library, and contains rich documentation. 
 
 [Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html). The Spring web MVC framework provides model-view-controller architecture and ready components that can be used to develop flexible and loosely coupled web applications. The MVC pattern results in separating the different aspects of the application (input logic, business logic, and UI logic), while providing a loose coupling between these elements.

* The **Model** encapsulates the application data and in general they will consist of POJO.

* The **View** is responsible for rendering the model data and in general it generates HTML output that the client's browser can interpret.

* The **Controller** is responsible for processing user requests and building appropriate model and passes it to the view for rendering.
 
## 5. Licence

This software is licensed under the MIT License.

The MIT License (MIT)

Copyright (c) 2014 David Filipovic - david.1990@ymail.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
