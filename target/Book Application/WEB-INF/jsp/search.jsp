<%-- 
    Document   : search
    Created on : Sep 7, 2014, 11:11:37 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Book Application</title>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
        <script type="text/javascript">

            $(document).ready(function() {

                $("#example").dataTable({
                    "bProcessing": true,
                    "bServerSide": false,
                    "sDom": '<"top"fl>t<"bottom"ip><"clear">',
                    "oLanguage": {
                        "sSearch": "Search by book title:"
                    },
                    "columnDefs": [
                        {"width": "60%", "targets": 0},
                        {"width": "20%", "targets": 1},
                        {"width": "30%", "targets": 2}
                    ]
                });

                $("#example2").dataTable({
                    "bProcessing": true,
                    "bServerSide": false,
                    "sDom": '<"top"fl>t<"bottom"ip><"clear">',
                    "oLanguage": {
                        "sSearch": "Search by book title:"
                    },
                    "columnDefs": [
                        {"width": "50%", "targets": 0},
                        {"width": "20%", "targets": 1},
                        {"width": "30%", "targets": 2}
                    ]
                });
                
                 $("#example3").dataTable({
                    "bProcessing": true,
                    "bServerSide": false,
                    "sDom": '<"top"fl>t<"bottom"ip><"clear">',
                    "oLanguage": {
                        "sSearch": "Search by book title:"
                    },
                    "columnDefs": [
                        {"width": "50%", "targets": 0},
                        {"width": "20%", "targets": 1},
                        {"width": "30%", "targets": 2}
                    ]
                });
            });
        </script>
    </head>

    <body>
        <div id="main">
            <div class="wrapper">
                <div class="home-content-main">

                    <table>
                        <tr>
                            <td width="100"></td>
                            <td width="500">
                                <form action="" method="get">
                                    <h2>List of all publishers<br><br></h2>
                                    <table border="1" class="table-pub">
                                        <c:forEach var="pub" items="${pubList}">
                                            <tr>
                                                <td><a href="/BookApplication/search/resp?publisher=${pub}" id="publisher">${pub}</a></td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </form>
                                <form:form method="POST" commandName="check" action="">  
                                    <table>  
                                        <tbody><tr>  
                                                <td>
                                                    <form:checkbox path="isChecked" 
                                                                   label="Search books with link for online reading?"></form:checkbox>
                                                    </td>  
                                                </tr>  
                                                <tr>  
                                                    <td colspan="2">  
                                                        <input type="submit" value="Submit">  
                                                    </td>  
                                                </tr>  
                                            </tbody></table>    
                                    </form:form>  
                            </td>
                            <td width="500">
                                <form action="" method="post">
                                    <h2 >List of all years available<br><br></h2>
                                    <table border="1" class="table-pub">
                                        <c:forEach var="year" items="${years}">
                                            <tr>
                                                <td><a href="/BookApplication/search/resy?year=${year}">${year}</a></td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </form>
                            </td>
                        </tr>
                    </table>

                    
                    <br><br><br>


                    <form:form action="" method="GET">
                        <table width="80%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
                                    <table id="example" class="display" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Year</th>
                                                <th>Publisher</th>
                                            </tr>
                                        </thead>
                                        <c:choose>
                                            <c:when test="${! empty pubBooks}">
                                                <tbody>
                                                <h3>List of all books by publisher ${publisher}<br><br></h3>
                                                    <c:forEach var="book" items="${pubBooks}">
                                                    <tr>
                                                        <td>
                                                            <a href="/BookApplication/book/find?name=${book.name}">${book.name}</a>
                                                        </td>
                                                        <td>
                                                            ${book.datePublished}
                                                        </td>
                                                        <td>
                                                            ${book.publisher}
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </c:when>
                                            <c:when test="${! empty yearBooks}">
                                                <tbody>
                                                <h3>List of all books by year ${year}<br><br></h3>
                                                    <c:forEach var="book" items="${yearBooks}">
                                                    <tr>
                                                        <td>
                                                            <a href="/BookApplication/book/find?name=${book.name}">${book.name}</a>
                                                        </td>
                                                        <td>
                                                            ${book.datePublished}
                                                        </td>
                                                        <td>
                                                            ${book.publisher}
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </c:when>
                                        </c:choose>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </form:form>

                    <br><br><br>

                    <c:choose>
                        <c:when test="${! empty booksForYearAndPublisher}">
                            <form:form action="" method="GET">
                                <table width="80%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
                                            <table id="example2" class="display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Name</th>
                                                        <th>Year</th>
                                                        <th>Publisher</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <h3>Books for publisher ${publisher} and year ${year}<br><br></h3>
                                                    <c:forEach var="book" items="${booksForYearAndPublisher}">
                                                    <tr>
                                                        <td>
                                                            <a href="/BookApplication/book/find?name=${book.name}">${book.name}</a>
                                                        </td>
                                                        <td>
                                                            ${book.datePublished}
                                                        </td>
                                                        <td>
                                                            ${book.publisher}
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </form:form>
                        </c:when>
                    </c:choose>

                    <br><br><br>

                        <c:if test="${(check.isChecked) && (! empty booksReadOnlineYear)}">
                            <form:form action="" method="GET">
                                <table width="80%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
                                            <table id="example3" class="display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Name</th>
                                                        <th>Year</th>
                                                        <th>Publisher</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="book" items="${booksReadOnlineYear}">
                                                    <tr>
                                                        <td>
                                                            <a href="/BookApplication/book/find?name=${book.name}">${book.name}</a>
                                                        </td>
                                                        <td>
                                                            ${book.datePublished}
                                                        </td>
                                                        <td>
                                                            ${book.publisher}
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </form:form>
                        </c:if>

                    <br><br><br>

                </div>
            </div>
        </div>

        <div id="footer">
            <div class="degree"></div>
        </div>
        <div id="bottom">
            <div class="wrapper">© Copyright 2014. All Rights Reserved</div>
        </div>

    </body>
</html>
