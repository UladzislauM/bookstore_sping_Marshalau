<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>

        <head>
            <meta charset="utf-8">
            <title>Authors in BookStore</title>
            <link rel="stylesheet" href="/css/style.css">
        </head>

        <body>

            <div>
                <%@ include file="menu.jsp" %>
            </div>

            <h3 align="center">AllAuthors: </h3>

            <p align="left">All authors: ${requestScope.authors_count}</p>

            <table class="table">
                <thead>
                    <tr>
                        <th align="left">Authors:</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.authors}" var="book" varStatus="counter">
                        <tr>
                            <td><a href="/books/find_book_by_author/${book.nameAuthor}">${book.nameAuthor}</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </body>

        </html>