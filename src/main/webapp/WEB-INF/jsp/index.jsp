<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<div> <%@ include file="menu.jsp" %> </div>

<h3>What do you want to work with?(users, books, all_orders):</h3>
<form action="users/users_find" method="post">
    <input type="submit" value="All users"/></form>
<form action="books/books_find" method="get">
    <input type="submit" value="All books"/></form>
<form action="orders/orders_find" method="get">
    <input type="submit" value="All orders"/></form>
<form action="/login" method="get">
    <input type="submit" value="Login"/></form>
<form action="/users/registration" method="get">
    <input type="submit" value="Registration"/></form>
</body>
</html>
