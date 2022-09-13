<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user in BookStore</title>
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
        <form id="form1" action="/users/user_update/${requestScope.user.id}" method="post">
            <input type="hidden" value="${requestScope.user.name}" name="name"/>
            <input type="hidden" value="${requestScope.user.is_active}" name="is_active"/>
            <td>
                <input form="form1" type="text" value="${requestScope.user.last_name}" name="last_name">
            </td>
            <td class="center">
                <input form="form1" type="email" value="${requestScope.user.email}" name="email">
            </td>
            <td class="center">
                <input form="form1" type="text" value="${requestScope.user.password}" name="password">
            </td>
            <td class="center">${requestScope.user.role}
                <select form="form1" name="role" size="1">
                    <option selected value="USER">USER</option>
                    <option selected value="ADMIN">ADMIN</option>
                    <option selected value="MANAGER">MANAGER</option>
                </select>
            </td>
        </form>
    </tr>
    </tbody>
</table>
<p><input form="form1" type="submit" name="submit" value="Update User"/>

<form action="/users/user_activate" method="post">
    <input type="hidden" name="id" value="${requestScope.user.id}"/>
    <input type="submit" name="activate" value="Activate user"/></form>
<form action="/users/user_deactivate" method="post">
    <input type="hidden" name="id" value="${requestScope.user.id}"/>
    <input type="submit" name="deactivate" value="Deactivate user"/></form>

<a href="#" onclick="history.back();return false;" class="history-back"><-Back</a>
</body>
</html>
