<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <form action="book/find" method="get">
            <input type="text" id="name" name="name" size="40"/>
            <input type="submit" value="Find"/>
        </form>

        <c:if test="${book.name != null}">
            <c:forEach var="book" items="${books}">
                <table border="1">
                    <tr>
                        <td>${book.name}</td>
                        <c:forEach var="review" items="${book.reviews}">
                        <td>${review.author}</td>
                        <td>${review.reviewRating}</td>
                        <td>${review.datePublished}</td>
                        <td>${review.reviewBody}</td>
                        </c:forEach>
                    </tr>
                </table>
            </c:forEach>
            
        </c:if>
        <c:if test="${book.name == null}">
            <c:out value="There is no book for specified attribute"></c:out>
        </c:if>

        <br>
        <br>

        <table border="1">
            <c:forEach var="book" items="${bookList}">
                <tr>
                    <td>${book.name}</td>
                </tr>
            </c:forEach>
        </table>

       
            <table border="1">
                <c:forEach var="pub" items="${pubList}">
                    <tr>
                        <td><a href="book/pubBooks?publisher=${pub}">${pub}</a></td>
                    </tr>
                </c:forEach>
            </table>
        
        <br>
        <br>

        <table border="1">
            <c:forEach var="book" items="${pubBooks}">
                <tr>
                    <td>${book.name}</a></td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>