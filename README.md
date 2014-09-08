Book Application created using Spring MVC Framework and MongoDB in Java

## Details about the project

Main idea of this project is to acquire data from the different sites about books into one document, which will be then presented as integrated informations about one specific book. Besides that, application provides a set of search options, for quering database with suplied attributes. Books are crawled from the [Goodreads](https://www.goodreads.com) and from [IT E Books](http://it-ebooks.info) site, and then stored in the local repository (MongoDB). Microdata from the book pages is gathered using [Microdata to RDF Distiller](http://www.w3.org/2012/pyMicrodata/), and the output format is set to JSON LD. Schema used for storing books is schema:Book. 

The following phases are recognized through application workflow: 

* A web crawler is created, which then parsers data from the books sites by extracting microdata, and by direct crawling of the page, using specific selectors
* Extracted data is then transformed into JSON based on schema:Book 
* Data is persisted into MongoDB
* A web project enables data quering and their presentation

##Used technologies and instaling instructions

 This project has been created as a Maven project by using Spring MVC Framework in Java.

 For this application, I've used **mongoDB**, which is NoSQL database, and presents cross-platform document-oriented database.
 
  To start it, get mongo database from this [link](http://www.mongodb.org), install it, then navigate to *../bin/mongodb* and wait until it has made connection to the localhost. To start a command line, run *../bin/mongo* file. Simple commands would be *use [database name]* for choosing a database to work with, and *db.[collection.name].find()* for showing all documents for selected collection. 
  
  To crawl data from the web page, there is ExtractionListener class which implements ServletContextListener. ExtractionListener enables automatic gathering of the data, and starts crawling when the application starts. To disable ExtractionListener, simply comment listener tag in web.xml file. Notice that crawling of data may take a while, depending on internet connection speed. 
 
