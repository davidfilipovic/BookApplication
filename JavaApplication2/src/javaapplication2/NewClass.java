/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication2;

import com.mongodb.BasicDBObject;

                //      String descriptionITEbooks = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "description");

 //                                for (int i = 0; i < reviews.length(); i++) {
//                            JSONObject jsonObject = reviews.getJSONObject(i);
//                            listOfReviewsURLs.add(jsonObject.getString("url"));
//                        }

 //           BasicDBObject document = new BasicDBObject();

//                                for (int i = 0; i < reviews.length(); i++) {
//                            JSONObject jsonObject = reviews.getJSONObject(i);
//                            listOfReviewsURLs.add(jsonObject.getString("url"));
//                        }

//                                  .add("reviews", Json.createObjectBuilder()
//                                .add("@type", "Review")
//                                .add("author", reviewAuthor)
//                                .add("datePublished", reviewDate)
//                                .add("reviewBody", reviewBody)
//                                .add("reviewRating", reviewRating))
//                        .build();


//  .add("@type", type)
//                                .add("ratingValue", ratingValue)
//                                .add("reviewCount", reviewCount))


// for (Element link : ebooksElements) {
//            String linkHref = link.attr("href");
//            ebooksList.add(linkHref);
//        }


//  for (Element link : goodreadsElements) {
//            String linkHref = link.attr("href");
//            //     System.out.println(linkHref);
//            goodreadsList.add(linkHref.substring(11));
////            String linkText = link.text();
////            System.out.println(linkText);
//        }


 // System.out.println(u);
        //   WfYzScNz9X2wUukkJC1OzQ
        //        VmdctSvPmlceMlTryohPlS7oYjoGcvPyLRq3MRCgYs

        //Set<String> collections = db.getCollectionNames();

        // Elements u = doc.getElementsByTag("a");



// for (JSONObject result : jsonArrayGoodreads) {
//                    System.out.print(result.getJsonObject("from").getString("name"));
//                }
                //   DBObject dbo = (DBObject) JSON.parse(h);

    //table.insert(dbo);
            // document.put("about", "");
            // document.put("name", "mkyong");
            // document.put("age", 30);
            // document.put("createdDate", new Date());
            //  table.insert(document);
            //  table.insert(document);

 //    DBCursor cursorDoc = table.find();
//            ObjectMapper m = new ObjectMapper();
//
//            //while (cursorDoc.hasNext()) {
//            //System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(cursorDoc.next()));
//            //  System.out.println(cursorDoc.next() + "\n");
//            //}
////            for (String collectionName : collections) {
////                System.out.println(collectionName);
////            }

                //URL good = new URL("https://www.goodreads.com/search.xml?key=WfYzScNz9X2wUukkJC1OzQ&q=Ender%27s+Game");


public abstract class NewClass {
    
    public void asd(){
        System.out.println("A");
    }
    
    public abstract void ao();
    
     public void asd1(){
        System.out.println("B");
    }
     
      static String t
            = "{\n"
            + "    \"@context\": {\n"
            + "        \"rdfa\": \"http://www.w3.org/ns/rdfa#\", \n"
            + "        \"md\": \"http://www.w3.org/ns/md#\", \n"
            + "        \"numberOfPages\": \"http://schema.org/numberOfPages\", \n"
            + "        \"isbn\": \"http://schema.org/isbn\", \n"
            + "        \"name\": \"http://schema.org/name\", \n"
            + "        \"inLanguage\": \"http://schema.org/inLanguage\", \n"
            + "        \"url\": {\n"
            + "            \"@id\": \"http://schema.org/url\", \n"
            + "            \"@type\": \"@id\"\n"
            + "        }, \n"
            + "        \"Review\": \"http://schema.org/Review\", \n"
            + "        \"author\": {\n"
            + "            \"@id\": \"http://schema.org/author\", \n"
            + "            \"@type\": \"@id\"\n"
            + "        }, \n"
            + "        \"publishDate\": {\n"
            + "            \"@id\": \"http://schema.org/publishDate\", \n"
            + "            \"@type\": \"@id\"\n"
            + "        }, \n"
            + "        \"bookEdition\": \"http://schema.org/bookEdition\", \n"
            + "        \"reviews\": {\n"
            + "            \"@id\": \"http://schema.org/reviews\", \n"
            + "            \"@type\": \"@id\"\n"
            + "        }, \n"
            + "        \"Person\": \"http://schema.org/Person\", \n"
            + "        \"Book\": \"http://schema.org/Book\", \n"
            + "        \"awards\": \"http://schema.org/awards\", \n"
            + "        \"ratingValue\": \"http://schema.org/ratingValue\", \n"
            + "        \"ratingCount\": \"http://schema.org/ratingCount\", \n"
            + "        \"bookFormatType\": \"http://schema.org/bookFormatType\", \n"
            + "        \"AggregateRating\": \"http://schema.org/AggregateRating\", \n"
            + "        \"image\": {\n"
            + "            \"@id\": \"http://schema.org/image\", \n"
            + "            \"@type\": \"@id\"\n"
            + "        }, \n"
            + "        \"md:item\": {\n"
            + "            \"@type\": \"@id\"\n"
            + "        }, \n"
            + "        \"rdfa:usesVocabulary\": {\n"
            + "            \"@type\": \"@id\"\n"
            + "        }\n"
            + "    }, \n"
            + "    \"@id\": \"https://www.goodreads.com/book/show/7157310-rot-and-ruin\", \n"
            + "    \"rdfa:usesVocabulary\": \"http://schema.org/\", \n"
            + "    \"md:item\": {\n"
            + "        \"@list\": [\n"
            + "            {\n"
            + "                \"@type\": \"AggregateRating\", \n"
            + "                \"ratingValue\": \"4.13\", \n"
            + "                \"ratingCount\": \"17,570 ratings\"\n"
            + "            }, \n"
            + "            {\n"
            + "                \"@type\": \"Book\", \n"
            + "                \"numberOfPages\": \"458 pages\", \n"
            + "                \"bookFormatType\": \"Hardcover\", \n"
            + "                \"isbn\": \"9781442402324\", \n"
            + "                \"inLanguage\": \"English\", \n"
            + "                \"awards\": \"\\n                  Bram Stoker Award Nominee for Best Novel (2010), Cybils Awards for Young Adult Fantasy & Science Fiction (2010)\\n                \", \n"
            + "                \"name\": \"\\n      Rot and Ruin\\n      \\n          (Benny Imura #1)\\n\", \n"
            + "                \"author\": {\n"
            + "                    \"@type\": \"Person\", \n"
            + "                    \"name\": \"Jonathan Maberry\", \n"
            + "                    \"url\": \"https://www.goodreads.com/author/show/72451.Jonathan_Maberry\"\n"
            + "                }, \n"
            + "                \"reviews\": [\n"
            + "                    {\n"
            + "                        \"@type\": \"Review\", \n"
            + "                        \"publishDate\": \"https://www.goodreads.com/review/show/108448902%3Fbook_show_action%3Dtrue\", \n"
            + "                        \"url\": \"https://www.goodreads.com/review/show/108448902\"\n"
            + "                    }, \n"
            + "                    \n"
            + "                ], \n"
            + "                \"bookEdition\": \"1st Edition\", \n"
            + "                \"image\": \"https://www.goodreads.com/book/photo/7157310-rot-and-ruin\"\n"
            + "            }\n"
            + "        ]\n"
            + "    }\n"
            + "}";

      //           
//           
//            for (int j = 0; j < eBooksList.size(); j += 2) {
//                System.out.println(eBooksList.get(j));
//            }
      
 //                    String publisherLink = getSpecificAttributeFromJSON(jsonArrayITEbooks, 0, "publisher");
//                Document publisherDocument = retreiveDocumentPage(publisherLink);
//                Elements publisherElements = publisherDocument.getElementsByAttributeValue("style", "margin-bottom:20px;");
//                publisher = "";
//                for (Element element : publisherElements) {
//                    publisher = element.text();
//                }

 //   { "_id" : { "$oid" : "53ef7999e4749e5ca91c6d87"} , 
//"@context" : "http://schema.org" ,
//"@type" : "WebPage" ,
//"aggregateRating" : {
// "@type" : "AggregateRating" ,
//"ratingValue" : "3.95" ,
//"reviewCount" : "874 ratings"
//},
// "author" : "Andrew S. Tanenbaum" ,
//"bookFormat" : "Hardcover" ,
//"image" : "https://www.goodreads.com/book/photo/166190.Computer_Networks" ,
//"inLanguage" : "English" ,
//"isbn" : "0076092022473" ,
//"name" : "\n      Computer Networks\n"
//}
//            + "    \"@context\": {\n"
//            + "        \"rdfa\": \"http://www.w3.org/ns/rdfa#\", \n"
//            + "        \"md\": \"http://www.w3.org/ns/md#\", \n"
//            + "        \"inLanguage\": \"http://schema.org/inLanguage\", \n"
//            + "        \"isbn\": \"http://schema.org/isbn\", \n"
//            + "        \"name\": \"http://schema.org/name\", \n"
//            + "        \"Person\": \"http://schema.org/Person\", \n"
//            + "        \"url\": {\n"
//            + "            \"@id\": \"http://schema.org/url\", \n"
//            + "            \"@type\": \"@id\"\n"
//            + "        }, \n"
//            + "        \"Review\": \"http://schema.org/Review\", \n"
//            + "        \"numberOfPages\": \"http://schema.org/numberOfPages\", \n"
//            + "        \"reviews\": {\n"
//            + "            \"@id\": \"http://schema.org/reviews\", \n"
//            + "            \"@type\": \"@id\"\n"
//            + "        }, \n"
//            + "        \"publishDate\": {\n"
//            + "            \"@id\": \"http://schema.org/publishDate\", \n"
//            + "            \"@type\": \"@id\"\n"
//            + "        }, \n"
//            + "        \"bookEdition\": \"http://schema.org/bookEdition\", \n"
//            + "        \"AggregateRating\": \"http://schema.org/AggregateRating\", \n"
//            + "        \"Book\": \"http://schema.org/Book\", \n"
//            + "        \"ratingValue\": \"http://schema.org/ratingValue\", \n"
//            + "        \"ratingCou nt\": \"http://schema.org/ratingCount\", \n"
//            + "        \"bookFormatType\": \"http://schema.org/bookFormatType\", \n"
//            + "        \"author\": {\n"
//            + "            \"@id\": \"http://schema.org/author\", \n"
//            + "            \"@type\": \"@id\"\n"
//            + "        }, \n"
//            + "        \"image\": {\n"
//            + "            \"@id\": \"http://schema.org/image\", \n"
//            + "            \"@type\": \"@id\"\n"
//            + "        }, \n"
//            + "        \"md:item\": {\n"
//            + "            \"@type\": \"@id\"\n"
//            + "        }, \n"
//            + "        \"rdfa:usesVocabulary\": {\n"
//            + "            \"@type\": \"@id\"\n"
//            + "        }\n"
//            + "    }, \n"
//            + "    \"@id\": \"https://www.goodreads.com/book/show/166190.Computer_Networks\", \n"
//            + "    \"rdfa:usesVocabulary\": \"http://schema.org/\", \n"
}

                //  AIzaSyBXg93iv3fINpIUDqL23jVYcfeRwVdmhgM


//                BufferedReader input2 = new BufferedReader(
//                        new InputStreamReader(urlReviews.openStream()));
//
//                String y = "";
//
//                while ((inputLine = input2.readLine()) != null) {
//                    System.out.println(inputLine);
//                    y += inputLine;
//                }


    //  System.out.println(ebooksBook);
//                BufferedReader input1 = new BufferedReader(
//                        new InputStreamReader(ebooksBook.openStream())); //metoda
//
//                while ((inputLine = input1.readLine()) != null) {
//                    //System.out.println(inputLine);
//                    a += inputLine;
////                    if (inputLine.contains("name")) {
////                        String name = inputLine;
////
////                        System.out.println(name);
//                }



//
//   BufferedReader input = new BufferedReader(
//                        new InputStreamReader(goodreadsBook.openStream()));
//                String h = "";
//                while ((inputLine = input.readLine()) != null) {
//                    //    System.out.println(inputLine);
//                    h += inputLine;
////                    if (inputLine.contains("name")) {
////                        String name = inputLine;
////                        System.out.println(name);
////                    }
//                }


     //http://www.ebooks.com/Adobe-Photoshop-CS6-Classroom-Book/dp/0321827333
                // System.out.println(y);

//          image = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "image");
//        author = getAuthorNameOrNames(jsonArrayGoodreads);
//        bookName = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "name");
//        isbn = getSpecificAttributeFromJSON(jsonArrayGoodreads, 1, "isbn");

//                  System.out.println(readOnlineLink);
//                System.out.println(publisher);
//                System.out.println(datePublished);
//                System.out.println(subtitleITEbooks);
//                System.out.println(isbn);
//                System.out.println(bookName);
//                System.out.println(image);

//        new URL("http://www.w3.org/2012/pyMicrodata/extract?uri=http%3A%2F%2Fit-ebooks.info"
//                        + eBookURL
//                        + "&format=json&vocab_expansion=false&vocab_cache=true");

//   updateQuery.append("$set",
//                new BasicDBObject().append("numberOfPages", updateList.get(1)));
//        updateQuery.append("$set",
//                new BasicDBObject().append("aggregateRating.ratingValue", updateList.get(2)));
//        updateQuery.append("$set",
//                new BasicDBObject().append("aggregateRating.reviewCount", updateList.get(3)));
//        updateQuery.append("$set",
//                new BasicDBObject().append("aggregateRating.@type", updateList.get(4)));
//        updateQuery.append("bookFormat", updateList.get(5));
//        updateQuery.append("$set",
//                new BasicDBObject().append("bookFormat", updateList.get(5)));
//        updateQuery.append("$set",
//                new BasicDBObject().append("edition", updateList.get(6)));
//        updateQuery.append("$set",
//                new BasicDBObject().append("awards", updateList.get(7)));
//        updateQuery.append("$set",
//                new BasicDBObject().append("description", updateList.get(8)));


//  BasicDBList parsedReviewArray = new BasicDBList();
//        for (int i = 0; i<reviewArray.length(); i++){
//           JSONObject reviewObject = reviewArray.getJSONObject(i);
//           parsedReviewArray.add(i, (DBObject) JSON.parse(reviewObject.toString()));
//        }
//        BasicDBObject updateObject = new BasicDBObject("$set", updateQuery);
//        BasicDBObject update = new BasicDBObject();
//        update.append("lista", parsedReviewArray);
//                BasicDBObject update1 = new BasicDBObject("$set", update);
//     //   DBObject reviewObject = new BasicDBObject("$pushAll", new BasicDBObject("reviews", reviewArray));
//        bookTable.update(searchQuery, updateObject);
//        bookTable.update(searchQuery, update1);
//    //    bookTable.update(searchQuery, reviewObject);
//      //  bookTable.update(searchQuery, BasicDBObjectBuilder.start().push("$pushAll").add("indexes", reviewArray).pop().get());