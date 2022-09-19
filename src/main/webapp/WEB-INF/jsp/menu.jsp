<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<html>
<head>
    <meta charset="utf-8">
    <title>Login in BookStore</title>
    <link rel="stylesheet" href="/css/menuStyle.css">
</head>
<body>

<ul class="menu-main">
  <li><a href="/" class="current">Home</a></li>
  <li><a href="/books/books_find" class="current">Catalog</a></li>
  <li><a href="/books/authors_find" class="current">Authors</a></li>
  <li><a href="">About</a></li>
  <li><a href="">Blog</a></li>
  <li><a href="">Contact</a></li>
  <c:if test="${sessionScope.user == null}">
  <li><a href="/login" class="current">Login</a></li>
  </c:if>
  <c:if test="${sessionScope.user != null}">
  <li><form class="current" action="/cart/get_cart" method="post">
      <input type="submit" value="Cart"/></form></li>
    <li>
    <li><form class="current" action="/orders/find_orders" method="post">
      <input type="submit" value="Orders"/></form></li>
    <li>
          <form action="/logout" method="post">
          <input type="submit" value="Logout"/></form>
             <c:if test="${sessionScope.user.role.toString() == ADMIN}">
                <li>
                <form action="/users/users_find" method="post">
                       <input type="submit" value="All Users"/></form>
                </li>
             </c:if>
    </li>
  </c:if>
</ul>

</body>
</html>