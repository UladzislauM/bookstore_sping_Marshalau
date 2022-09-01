<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Orders in BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<h3 align="center">AllOrders (abbreviated representation): </h3>

<form align="left" action="controller" method="post">
    <details>
        <summary>Create New Order</summary>
        <p>Write Parameters:</p>
        <input type="hidden" name="command" value="order_create"/>
        <p><input type="text" name="user_id" placeholder="write user id">
        <p><input type="text" name="total_cost" placeholder="write total cost">
        <p>(Format data: yyyy-MM-dd)</p>
        <p><input type="date" name="timestamp" placeholder="write timestamp">
        <p><input type="radio" name="status_name" value="IN_PROCESSING">IN_PROCESSING
            <input type="radio" name="status_name" value="ASSEMBLED">ASSEMBLED
            <input type="radio" name="status_name" value="AWAITING_PAYMENT">AWAITING_PAYMENT
            <input type="radio" name="status_name" value="READY_TO_SHIP">READY_TO_SHIP
            <input type="radio" name="status_name" value="SENT">SENT
        <p><input type="submit" name="submit" value="Create Order"/></p>
    </details>
</form>

<p align="left">All counts: </p>

<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>User name</th>
        <th>Total cost</th>
        <th>Timestamp</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.orders}" var="order" varStatus="counter">
        <tr>
            <td class="center">${counter.count}</td>
            <td>
                <a href="controller?command=find_order_by_id&id=${order.id}">${order.user.name} ${order.user.last_name}</a>
            </td>
            <td class="center">${order.totalCost}</td>
            <td class="center">${order.timestamp}</td>
            <td class="center">${order.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>What do you want to work with?(users, books, all_orders):</h3>
<form action="controller" method="post">
    <input type="hidden" name="command" value="users"/>
    <input type="submit" value="All users"/></form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="books"/>
    <input type="submit" value="All books"/></form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="all_orders"/>
    <input type="submit" value="All orders"/></form>
</body>
</html>
