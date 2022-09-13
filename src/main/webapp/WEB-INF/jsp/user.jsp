<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View user in BookStore</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h3>User: ${requestScope.user.name}</h3>
<table class="table">
    <thead>
    <tr>
        <th>Last Name</th>
        <th>Email</th>
        <th>Password</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            ${requestScope.user.last_name}
        </td>
        <td class="center">
            ${requestScope.user.email}
        </td>
        <td class="center">
            ${requestScope.user.password}
        </td>
        <td class="center">
            ${requestScope.user.role}
        </td>
    </tr>
    </tbody>
</table>
<form action="/users/user_update_form" method="post">
    <input type="hidden" name="id" value="${requestScope.user.id}"/>
    <input type="submit" name="submit" value="Update User"/></form>

<form action="/users/user_activate" method="post">
    <input type="hidden" name="id" value="${requestScope.user.id}"/>
    <input type="submit" name="activate" value="Activate user"/></form>
<form action="/users/user_deactivate" method="post">
    <input type="hidden" name="id" value="${requestScope.user.id}"/>
    <input type="submit" name="deactivate" value="Deactivate user"/></form>
<form action="/users/users_find" method="post">
    <input type="submit" value="All users"/></form>

<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
