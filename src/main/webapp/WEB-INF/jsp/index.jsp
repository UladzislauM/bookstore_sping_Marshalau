<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>What do you want to work with?(users, books, all_orders):</h3>
<form action="users/users_find" method="get">
    <input type="submit" value="All users"/></form>
<form action="books/books_find" method="get">
    <input type="submit" value="All books"/></form>
<form action="orders/orders_find" method="get">
    <input type="submit" value="All orders"/></form>
</body>
</html>
