<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Book in BookStore</title>
    <link rel="stylesheet" href="./CSS/style.css">
</head>
<body>
<h2 align="center">Book: ${requestScope.order.id}</h2>

<p>Status:</p>

<form action="controller" method="post">
    <input type="hidden" name="post" value="order_update"/>
    <input type="hidden" name="id" value="${requestScope.order.id}"/>
    <p><input type="radio" name="status_name" value="IN_PROCESSING">IN_PROCESSING
        <input type="radio" name="status_name" value="ASSEMBLED">ASSEMBLED
        <input type="radio" name="status_name" value="AWAITING_PAYMENT">AWAITING_PAYMENT
        <input type="radio" name="status_name" value="READY_TO_SHIP">READY_TO_SHIP
        <input type="radio" name="status_name" value="SENT">SENT
    <input type="submit" name="delete" value="Update Order"/></form>

<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
