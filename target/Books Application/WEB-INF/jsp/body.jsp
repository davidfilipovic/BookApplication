<%-- 
    Document   : body
    Created on : Sep 5, 2014, 2:13:29 AM
    Author     : David
--%>

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

                    <c:if test="${book.name != null}">
                        <c:forEach var="book" items="${books}">

                            <div class="headline">${book.name}</div>
                            <div class="shadow-divider"></div>
                            <table width="1000" align="center" border-top="0">
                                <tr>
                                    <td width="50" align="left"></td>
                                    <td width="250">
                                        <img src="${book.image}" height="300">
                                    </td>
                                    <td width="750">
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
                                                <td><b>Read online:</b></td> 
                                                <td><a href="${book.readOnlineLink}"></a></td>
                                            </tr>

                                        </table>
                                    </td>
                                    <td width="50" align="right"></td>
                                </tr>
                            </table>
                            <div class="shadow-divider"></div>
                            <div class="headline-h"><b>Reviews</b></div>

                            <div>
                                <c:if test="${! empty book.reviews}">
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
                    </c:if>


                </div>
            </div>
        </div>


</html>
