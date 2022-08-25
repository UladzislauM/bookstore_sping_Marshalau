<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>What do you want to work with?(users, books, all_orders):</h3>
<form action="controller" method="post">
    <input type="hidden" name="post" value="users"/>
    <input type="submit" value="All users"/></form>
<form action="controller" method="post">
    <input type="hidden" name="post" value="books"/>
    <input type="submit" value="All books"/></form>
<form action="controller" method="post">
    <input type="hidden" name="post" value="all_orders"/>
    <input type="submit" value="All orders"/></form>
</form>
</body>
</html>
