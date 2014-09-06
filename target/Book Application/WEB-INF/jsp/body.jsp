<%-- 
    Document   : body
    Created on : Sep 5, 2014, 2:13:29 AM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>1</h1>
        <h1>2</h1>
        <br>
        <c:if test="${book.name != null}">
            <c:forEach var="book" items="${books}">
                <table border="0">
                    <tr>
                        <td>${book.name}</td>
                    </tr>
                    <tr>
                        <td>${book.author}</td>
                    </tr>
                </table>
                      <div class="pub-name">
                <div class="book-dev">
                    <b>Publisher</b> <br>
                    ${book.publisher}
                </div>
                <div class="book-edition">
                    <b>Edition</b> <br>
                   ${book.edition}
                </div>
                  <div class="book-rel-date">
                    <b>Date published</b> <br>
                  ${book.datePublished}
                </div>
            </div>
            </c:forEach>
        </c:if>

        <c:forEach var="review" items="${book.reviews}">
        <td>${review.author}</td>
        <td>${review.reviewRating}</td>
        <td>${review.datePublished}</td>
        <td>${review.reviewBody}</td>
    </c:forEach>
        
</html>
