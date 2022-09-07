<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View order in BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<h2 align="center">Order: ${requestScope.order.totalCost}</h2>
<table class="table">
    <thead>
    <tr>
        <th>User:</th>
        <th>Timestamp</th>
        <th>Total cost:</th>
        <th>Status:</th>
        <th>Item:</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            ${requestScope.order.user.name} ${requestScope.order.user.last_name}
        </td>
        <td class="center">
            ${requestScope.order.timestamp}"
        </td>
        <td class="center">
            ${requestScope.order.totalCost}
        </td>
        <td class="center">
            ${requestScope.order.status}
        </td>
        <td class="center">
            ${requestScope.order.items.size()}:
            <table class="table">
                <thead>
                <tr>
                    <th>Author:</th>
                    <th>Date release books:</th>
                    <th>Price:</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.order.items}" var="order_item" varStatus="counter">
                    <tr>
                        <td class="center">
                                ${order_item.book.nameAuthor}"
                        </td>
                        <td class="center">
                                ${order_item.book.dateReleaseBook}"
                        </td>
                        <td class="center">
                                ${order_item.book.price}"
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>
<form action="controller" method="post">
    <input type="hidden" name="command" value="order_update_form"/>
    <input type="hidden" name="id" value="${requestScope.order.id}"/>
    <input type="submit" name="submit" value="Update Order"/></form>

<a href="controller?command=all_orders"><-Back</a>
</body>
</html>
