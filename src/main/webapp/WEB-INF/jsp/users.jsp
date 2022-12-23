<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
        <html lang="en" dir="ltr">

        <head>
            <meta charset="utf-8">
            <title>Users in BookStore</title>
            <link rel="stylesheet" href="/css/style.css">
        </head>

        <body>

            <div>
                <%@ include file="menu.jsp" %>
            </div>

            <h3>AllUsers (abbreviated representation): </h3>

            <form align="left" action="/users/create" method="post">
                <details>
                    <summary>Create New User</summary>
                    <p>Write Parameters:</p>
                    <input type="hidden" name="is_active" value="true" />
                    <p><input type="text" name="name" placeholder="write Name">
                    <p><input type="text" name="last_name" placeholder="write Last_Name">
                    <p><input type="email" name="email" placeholder="write Email">
                    <p><input type="text" name="password" placeholder="write Password">
                    <p><input type="radio" name="role" value="USER">USER
                        <input type="radio" name="role" value="ADMIN">ADMIN
                        <input type="radio" name="role" value="MANAGER">MANAGER
                    <p><input type="submit" name="submit" value="Create User" /></p>
                </details>
            </form>

            <p align="left">All counts: ${requestScope.user_count}</p>

            <form action="/users" method="post">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Is active</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.users}" var="user" varStatus="counter">
                            <tr>
                                <td class="center"><input type="radio" name="id" value="${user.id}">${counter.count}
                                </td>
                                <td><a href="find_user_by_id/${user.id}">${user.name} ${user.last_name}</a>
                                </td>
                                <td>${user.email}</td>
                                <td class="center">${user.role}</td>
                                <td class="center">${user.is_active}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="submit" formaction="user_deactivate">User Deactivate</button>
                <button type="submit" formaction="user_activate">User Activate</button>
            </form>

            <h3>What do you want to work with?(users, books, all_orders):</h3>
            <form action="/books/books_find" method="get">
                <input type="submit" value="All books" />
            </form>
            <form action="/orders/orders_find" method="get">
                <input type="submit" value="All orders" />
            </form>
        </body>

        </html>
        