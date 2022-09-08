<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View order in BookStore</title>
    <link rel="stylesheet" href="CSS/style.css">
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
            <a href="controller?command=get_user_by_id&id=${requestScope.order.user.id}">${requestScope.order.user.name} ${requestScope.order.user.last_name}</a>
        </td>
        <td class="center">
            ${requestScope.order.timestamp}
        </td>
        <td class="center">
            ${requestScope.order.totalCost}
        </td>
        <td class="center">
            ${requestScope.order.status}
        </td>
        <td class="center"> Quantity - ${requestScope.order.items.size()} :
            <table class="table">
                <thead>
                <tr>
                    <th>N:</th>
                    <th>Book:</th>
                    <th>Quantity:</th>
                    <th>Price:</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.order.items}" var="order_item" varStatus="counter">
                    <tr>
                        <td class="center">
                                ${counter.count}
                        </td>
                        <td class="center">
                            <a href="controller?command=get_book_by_id&id=${order_item.book.id}">${order_item.book.title}
                                - ${order_item.book.nameAuthor}</a>
                        </td>
                        <td class="center">
                                ${order_item.quantity}
                        </td>
                        <td class="center">
                                ${order_item.price}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>

<a href="controller?command=all_orders"><-Back</a>
</body>
</html>
