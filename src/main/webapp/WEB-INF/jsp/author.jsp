<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View books by author in BookStore</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div> <%@ include file="menu.jsp" %> </div>

<h2 align="center">Author: ${requestScope.book.nameAuthor}</h2>
<table class="table">
    <thead>
    <tr>
        <th>Title:</th>
        <th>Date release books:</th>
        <th>Price:</th>
        <th>ISBN:</th>
        <th>Cover:</th>
    </tr>
    </thead>
    <tbody>
            <c:forEach items="${requestScope.books}" var="book" varStatus="counter">
                <tr>
                    <td class="center">${counter.count}</td>
                    <td><a href="/books/find_book_by_id/${book.id}">${book.title}</a></td>
                    <td class="center">${book.dateReleaseBook}</td>
                    <td class="center">${book.price}</td>
                    <td class="center">${book.coverBook}</td>
                    <td class="center">
                        <form action="/orders/add_to_cart" method="post">
                          <input type="submit" name="id" value="Add to cart"/></form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
</table>

<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
