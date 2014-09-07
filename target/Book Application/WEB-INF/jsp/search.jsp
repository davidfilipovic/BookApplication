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

                $("#example1").dataTable({
                    "bProcessing": true,
                    "bServerSide": false,
                    "sDom": '<"top"fl>t<"bottom"ip><"clear">',
                    "oLanguage": {
                        "sSearch": "Search by book title:"
                    },
                    "columnDefs": [
                        {"width": "100%", "targets": 0},
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

                    <%--<c:choose>--%>
                    <%--<c:when test="${! empty pubBooks}">--%>
                    <form:form action="" method="GET">
                        <table width="80%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
                                    <table id="example1" class="display" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:choose>
                                                <c:when test="${! empty pubBooks}">
                                                <h2 >List of all books by publisher ${publisher}<br><br></h2>
                                                    <c:forEach var="book" items="${pubBooks}">
                                                    <tr>
                                                        <td>
                                                            <a href="/BookApplication/book/find?name=${book.name}">${book.name}</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${! empty yearBooks}">
                                                <h2 >List of all books by year ${year}<br><br></h2>
                                                    <c:forEach var="book" items="${yearBooks}">
                                                    <tr>
                                                        <td>
                                                            <a href="/BookApplication/book/find?name=${book.name}">${book.name}</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </form:form>
                    <%--</c:when>--%>
                    <%--</c:choose>--%>




                    <%--<c:choose>--%>
                    <%--<c:when test="${! empty pubBooks}">--%>
                    <%--<form:form action="" method="GET">--%>
<!--                        <h2 >List of all books by publisher ${publisher}<br><br></h2>
                        <table width="80%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
                                    <table id="example1" class="display" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                    <%--<c:forEach var="book" items="${yearBooks}">--%>
                        <tr>
                            <td>
                                <a href="/BookApplication/book/find?name=${book.name}">${book.name}</a>
                            </td>
                        </tr>
                    <%--</c:forEach>--%>
                </tbody>
            </table>
        </td>
    </tr>
</table>-->
                    <%--</form:form>--%>
                    <%--</c:when>--%>
                    <%--</c:choose>--%>


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
