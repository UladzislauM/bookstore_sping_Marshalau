<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<html>
<head>
    <meta charset="utf-8">
    <title>Registration in BookStore</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/normalize.css">
</head>
<body>

<div class="head">
HeaD
</div>
<div class="registration-form">
<h2 align="center">Create New User</h2>
<form role="registration" action="/users/create" method="post">
    <label>Name(Login):</label>
    <input name="name" type="text"><br>
    <label>Last Name:</label>
    <input name="last_name" type="text"><br>
    <label>email:</label>
    <input name="email" type="text"><br>
    <label>Password:</label>
    <input name="password" type="text"><br>
    <input name="role" value="USER" type="hidden">
    <input name="is_active" value="true" type="hidden">
    <div class="center">
    <input value="Create account" type="submit">
    </div>
</form>
</div>

<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
