<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Spring pagination using data tables</title>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
        <script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
        <script type="text/javascript">

            $(document).ready(function() {

                $("#example").dataTable({
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

        <form action="book/find" method="get">
            <input type="text" id="name" name="name" size="40"/>
            <input type="submit" value="Find"/>
        </form>

        <form:form action="" method="GET">
            <h2 >Spring MVC pagination using data tables<br><br></h2>
            <table width="70%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
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
                                            <a href="BookApplication/book/find?name=${book.name}">${book.name}</a>
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




        <c:if test="${book.name == null}">
            <c:out value="There is no book for specified attribute"></c:out>
        </c:if>


        <br>
        <br>

        <table border="1">
            <c:forEach var="pub" items="${pubList}">
                <tr>
                    <td><a href="BookApplication?publisher=${pub}">${pub}</a></td>
                </tr>
            </c:forEach>
        </table>

        <table border="1">
            <c:forEach var="book" items="${pubBooks}">
                <tr>
                    <td>${book.name}</a></td>
                </tr>
            </c:forEach>
        </table>



        <br>
        <br>

    </body>
</html>