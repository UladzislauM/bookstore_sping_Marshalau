<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Books in BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<h3 align="center">AllBooks (abbreviated representation): </h3>

<form align="left" action="controller" method="post">
    <details>
        <summary>Create New Book</summary>
        <p>Write Parameters:</p>
        <input type="hidden" name="post" value="book_create"/>
        <p><input type="text" name="title" placeholder="write title">
        <p><input type="text" name="name_author" placeholder="write author">
        <p>(Format data: yyyy-MM-dd)</p>
        <p><input type="date" name="data_purchase" placeholder="write data purchase">
        <p><input type="text" name="price" placeholder="write price">
        <p><input type="text" name="isbn" placeholder="write isbn">
        <p><input type="radio" name="cover_book" value="HARD">HARD
            <input type="radio" name="cover_book" value="SOFT">SOFT
            <input type="radio" name="cover_book" value="EXCLUSIVE">EXCLUSIVE
        <p><input type="submit" name="submit" value="Create Book"/></p>
    </details>
</form>

<p align="left">All counts: ${requestScope.book_count}</p>

<form action="controller" method="post">
    <input type="hidden" name="post" value="book_delete"/>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>DataPurchase</th>
            <th>Price</th>
            <th>Cover</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.books}" var="book" varStatus="counter">
            <tr>
                <td class="center"><input type="radio" name="id" value="${book.id}">${counter.count}</td>
                <td><a href="controller?command=get_book_by_id&id=${book.id}">${book.title}</a></td>
                <td>${book.nameAuthor}</td>
                <td class="center">${book.dateReleaseBook}</td>
                <td class="center">${book.price}</td>
                <td class="center">${book.cover}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" name="id" value="Delete Book"/>
</form>

<form align="left" action="controller" method="get">
    <h3>What do you want to work with?(users, books):</h3>
    <p><input type="text" name="command" placeholder="write command">
        <input type="submit" value="Submit"/></p>
</form>
</body>
</html>
