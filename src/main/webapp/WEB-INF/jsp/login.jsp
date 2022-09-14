<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login in BookStore</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/normalize.css">
</head>
<body>

<div> <%@ include file="menu.jsp" %> </div>

<div class="head">
HeaD
</div>
<div class="login-form">
<h2>Login</h2>
<form role="login" action="/users/login" method="post">
    <label><span class="screen-reader-text">Login:</span></label>
    <input name="login" type="text"><br>
    <label>Password:</label>
    <input name="password" type="text"><br>
    <input value="Login" type="submit">
</form>
</div>

<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>