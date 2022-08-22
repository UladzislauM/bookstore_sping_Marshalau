<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookStore</title>
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
        <th>Status:</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <form id="form1" action="controller?post=book_update_form" method="post">
            <input type="hidden" name="id" value="${requestScope.book.id}"/></form>
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
        <td class="center">${requestScope.book.status}
        </td>
    </tr>
    </tbody>
</table>
<p><input form="form1" type="submit" name="submit" value="Update Book"/>
<form id="form2" action="controller?post=book_delete&id=${requestScope.book.id}" method="post"></form>
<input form="form2" type="submit" name="delete" value="Delete book"/>
</p>
<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
