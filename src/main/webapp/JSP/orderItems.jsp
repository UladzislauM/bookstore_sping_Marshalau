<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>OrderItems in BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<h3 align="center">AllOrderItems (abbreviated representation): </h3>

<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Orders</th>
        <th>Books</th>
        <th>Quantity</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.order_items}" var="order_items" varStatus="counter">
        <tr>
            <td class="center">${counter.count}</td>
<%--            <td>--%>
<%--                <a href="controller?command=order_item_find_by_id&id=${order_items.id}">--%>
<%--                    <c:forEach items="${order_items.ordersDto}" var="order_DTO" varStatus="counterOrder">--%>
<%--                                        <p>${counterOrder.count}</p>--%>
<%--                                        <p>${order_DTO.}</p>--%>
<%--                                    </c:forEach>--%>
<%--            </a>--%>
<%--            </td>--%>
            <td>${order_items.ordersDto}</td>
            <td>${order_items.bookDto}</td>
<%--            <td class="center">--%>
<%--                <a href="controller?command=order_item_find_by_id&id=${order_items.id}">--%>
<%--                    <c:forEach items="${order_items.bookDto}" var="book_DTO" varStatus="counterBook">--%>
<%--                        <p>${counterBook.count}</p>--%>
<%--                        <p>${book_DTO}</p>--%>
<%--                    </c:forEach>--%>
<%--                </a>--%>
<%--            </td>--%>
            <td >${order_items.quantity}</td>
            <td >${order_items.price}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>What do you want to work with?(users, books, all_orders):</h3>
<form action="controller" method="post">
    <input type="hidden" name="command" value="users_find"/>
    <input type="submit" value="All users"/></form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="books_find"/>
    <input type="submit" value="All books"/></form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="all_orders"/>
    <input type="submit" value="All orders"/></form>
</body>
</html>