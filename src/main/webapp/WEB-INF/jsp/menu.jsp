<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en" dir="ltr">
    <html>

    <head>
      <meta charset="utf-8">
      <title>Login in BookStore</title>
      <link rel="stylesheet" href="/css/menuStyle.css">
      <link rel="stylesheet" href="/css/style.css">
      <link rel="stylesheet" href="/css/normalize.css">
    </head>

    <body>

      <ul class="menu-main">
        <li><a href="/">Home</a></li>
        <li><a href="/books/books_find">Catalog</a></li>
        <li><a href="/books/authors_find">Authors</a></li>
        <li><a href="">About</a></li>
        <li><a href="">Blog</a></li>
        <li><a href="">Contact</a></li>
        <c:if test="${sessionScope.user == null}">
          <li><a href="/login">Login</a></li>
          <li><a href="/users/registration">Registration</a></li>
        </c:if>
        <c:if test="${sessionScope.user != null}">
          <li>
            <form class="current" action="/cart/get_cart" method="post">
              <input type="submit" value="Cart" />
            </form>
          </li>
          <li>
            <form action="/orders/find_orders" method="post">
              <input class="current" type="submit" value="Orders" />
            </form>
          </li>
          <li>
            <form action="/logout" method="post">
              <input type="submit" value="Logout" />
            </form>
            <c:if test="${sessionScope.users.role.toString() == ADMIN}">
          <li>
            <form action="/users/users_find" method="post">
              <input type="submit" value="All Users" />
            </form>
          </li>
        </c:if>
        </li>
        </c:if>
      </ul>

    </body>

    </html>