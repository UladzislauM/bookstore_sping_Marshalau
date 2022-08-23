<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View book in BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<h2 align="center">Book: ${requestScope.book.title}</h2>
<table class="table">
    <thead>
    <tr>
        <th>Author:</th>
        <th>Date release book:</th>
        <th>Price:</th>
        <th>ISBN:</th>
        <th>Cover:</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            ${requestScope.book.nameAuthor}
        </td>
        <td class="center">
            ${requestScope.book.dateReleaseBook}"
        </td>
        <td class="center">
            ${requestScope.book.price}
        </td>
        <td class="center">
            ${requestScope.book.isbn}
        </td>
        <td class="center">
            ${requestScope.book.cover}
        </td>
    </tr>
    </tbody>
</table>
<form action="controller" method="post">
    <input type="hidden" name="post" value="book_update_form"/>
    <input type="hidden" name="id" value="${requestScope.book.id}"/>
    <input type="submit" name="submit" value="Update Book"/></form>

<form action="controller" method="post">
    <input type="hidden" name="post" value="book_delete"/>
    <input type="hidden" name="id" value="${requestScope.book.id}"/>
    <input type="submit" name="delete" value="Delete book"/></form>

<a href="controller?command=books"><-Back</a>
</body>
</html>
