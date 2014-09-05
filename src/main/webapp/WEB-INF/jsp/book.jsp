<%-- 
    Document   : book
    Created on : Sep 3, 2014, 5:29:18 PM
    Author     : David
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Book page</title>
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
        
       

</body>
</html>
