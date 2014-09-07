<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                    "iDisplayLength": 25,
                    "bProcessing": true,
                    "bServerSide": false,
                    "sDom": '<"top"fl>t<"bottom"ip><"clear">',
                    "oLanguage": {
                        "sSearch": "Search by book title, author, or publisher:"
                    },
                    "columnDefs": [
                        {"width": "50%", "targets": 0},
                        {"width": "35%", "targets": 1},
                        {"width": "10%", "targets": 2}
                    ]
                });

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

        <div id="home-header-main">
            <div class="degree">
                <div class="wrapper">
                    <div class="title-holder">
                        <div class="title-m">
                            <a href="/BookApplication/">Books application</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="main">
            <div class="wrapper">
                <div class="home-content-main">
                    
                      <a href="/BookApplication/search">click</a>

                    <form:form action="" method="GET">
                        <h2 >List of all books<br><br></h2>
                        <table width="80%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
                                    <table id="example" class="display" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Author</th>
                                                <th>Publisher</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="book" items="${bookList}">
                                                <tr>
                                                    <td>
                                                        <a href="/BookApplication/book/find?name=${book.name}">${book.name}</a>
                                                    </td>
                                                    <td>${book.author}</td>
                                                    <td>${book.publisher}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </form:form>

                    <br><br><br><br>

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