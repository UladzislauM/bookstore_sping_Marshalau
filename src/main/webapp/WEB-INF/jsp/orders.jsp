<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Orders in BookStore</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h3 align="center">AllOrders (abbreviated representation): </h3>

<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>User name</th>
        <th>Timestamp</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.orders}" var="order" varStatus="counter">
        <tr>
            <td class="center">${counter.count}</td>
            <td>
                <a href="/orders/find_order_by_id/${order.id}">${order.user.name} ${order.user.last_name}</a>
            </td>
            <td class="center">${order.timestamp}</td>
            <td class="center">${order.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>What do you want to work with?(users, books, all_orders):</h3>
<form action="/users/users_find" method="get">
    <input type="submit" value="All users"/></form>
<form action="/books/" method="get">
    <input type="hidden" name="command" value="books_find"/>
    <input type="submit" value="All books"/></form>
<form action="/orders/orders_find" method="get">
    <input type="submit" value="All orders"/></form>
</body>
</html>
