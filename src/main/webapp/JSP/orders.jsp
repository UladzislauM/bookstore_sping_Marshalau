<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Orders in BookStore</title>
    <link rel="stylesheet" href="CSS/style.css">
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
    <c:forEach items="${requestScope.orders}" var="order_items" varStatus="counter">
        <tr>
            <td class="center">${counter.count}</td>
            <td>
                <a href="controller?command=find_order_by_id&id=${order_items.id}">${order_items.user.name} ${order_items.user.last_name}</a>
            </td>
            <td class="center">${order_items.timestamp}</td>
            <td class="center">${order_items.status}</td>
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
