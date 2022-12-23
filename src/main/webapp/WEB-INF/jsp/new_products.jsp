<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>New products</title>
        </head>

        <body>
            <c:forEach items="${requestScope.books}" var="book" varStatus="counter">
<table class="brick">
    <tbody>
        <tr>
            <td><a href="/books/find_book_by_id/${book.id}">${book.title}</a></td>
            <td>Image</td> 
        </tr>
    </tbody>
</table>
            </c:forEach>

        </body>

        </html>