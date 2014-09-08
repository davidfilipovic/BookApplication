<%-- 
    Document   : body
    Created on : Sep 5, 2014, 2:13:29 AM
    Author     : David
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <body>
        <div id="main">
            <div class="wrapper">
                <div class="home-content">

                    <div id="main-image">
                    </div>
                    <p class="clear"></p>
                    <div class="shadow-divider"></div>

                    <c:choose>
                        <c:when test="${! empty books}">
                            <c:forEach var="book" items="${books}">

                                <div class="headline">${book.name}</div>
                                <div class="shadow-divider"></div>
                                <table width="1000" align="center" border-top="0">
                                    <tr>
                                        <td width="25" align="left"></td>
                                        <td width="250">
                                            <img src="${book.image}" height="300">
                                            <div class="rating">
                                                <table class="rating-table">
                                                    <tr> 
                                                        <td><b>Book rating:</b></td>
                                                        <td>${book.aggregateRating.ratingValue}</td>
                                                    </tr>
                                                    <tr> 
                                                        <td><b>Rating count:</b></td>
                                                        <td>${book.aggregateRating.reviewCount}</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </td>
                                        <td width="800">
                                            <div class="text-main">
                                                <div class="bullet-title">
                                                    <div class="big">Book description</div>
                                                </div>
                                                ${book.description}
                                            </div>
                                            <div class="bullet-title">
                                                <div class="big">Book details</div>
                                            </div>
                                            <table class="table-data">
                                                <tr>
                                                    <td><b>Author:</b></td> 
                                                    <td>${book.author}</td>                                               
                                                </tr>
                                                <tr>
                                                    <td><b>Publisher:</b></td> 
                                                    <td>${book.publisher}</td>
                                                </tr>
                                                <tr>
                                                    <td><b>ISBN:</b></td> 
                                                    <td>${book.isbn}</td>
                                                </tr>
                                                <tr>
                                                    <td><b>Date published:</b></td> 
                                                    <td>${book.datePublished}</td>
                                                </tr>
                                                <tr>
                                                    <td><b>Number of pages:</b></td> 
                                                    <td>${book.numberOfPages}</td>
                                                </tr>
                                                <tr>
                                                    <td><b>Language:</b></td> 
                                                    <td>${book.inLanguage}</td>
                                                </tr>
                                                <tr>
                                                    <td><b>Awards:</b></td> 
                                                    <td>${book.awards}</td>
                                                </tr>
                                                <tr>
                                                    <td><b>Book format:</b></td> 
                                                    <td>${book.bookFormat}</td>
                                                </tr>

                                                <c:choose>
                                                    <c:when test="${book.readOnlineLink == 'http://it-ebooks.info'}">
                                                    </c:when>
                                                      <c:when test="${book.readOnlineLink == 'N/A'}">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <tr>
                                                            <td><b>Read online:</b></td> 
                                                            <td>
                                                                <a href="${book.readOnlineLink}">Link</a>
                                                            </td>
                                                        </tr>
                                                    </c:otherwise>
                                                </c:choose>
                                            </table>
                                        </td>
                                    </tr>
                                </table>

                                <div>
                                    <c:if test="${! empty book.reviews}">
                                        <div class="shadow-divider"></div>
                                        <div class="headline-h"><b>Reviews</b></div>
                                        <c:forEach var="review" items="${book.reviews}">
                                            <div class="books-list">
                                                <div class="critic-name">
                                                    <b>${review.author}</b>
                                                </div>
                                                <div class="critic-date">
                                                    ${review.datePublished}
                                                </div>
                                                <div class="critic-body">
                                                    ${review.reviewBody}
                                                </div>
                                                <div class="critic-book-score">
                                                    ${review.reviewRating}
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>

                            </c:forEach>
                        </c:when>
                            <c:otherwise>
                                <div class="headline">
                                    Sorry, but that book, or book author does not exists.
                                </div>
                            </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>

</html>
