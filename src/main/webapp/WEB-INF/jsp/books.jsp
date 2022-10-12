<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
        <html lang="en" dir="ltr">

        <head>
            <meta charset="utf-8">
            <title>Books in BookStore</title>
            <link rel="stylesheet" href="/css/style.css">
        </head>

        <body>

            <jsp:include page="menu.jsp" />

            <h3 class="h3">AllBooks:</h3>
            <c:if test="${sessionScope.user != null}">
                <c:if test="${sessionScope.users.role.toString() == ADMIN}">
                    <form action="/books/create" method="post">
                        <details>
                            <summary>Create New Book</summary>
                            <p>Write Parameters:</p>
                            <input type="hidden" name="deleted" value="false" />
                            <p><input type="text" name="title" placeholder="write title">
                            <p><input type="text" name="nameAuthor" placeholder="write author">
                            <p>(Format data: yyyy-MM-dd)</p>
                            <p><input type="date" name="dateReleaseBook" placeholder="write data purchase">
                            <p><input type="text" name="price" placeholder="write price">
                            <p><input type="text" name="isbn" placeholder="write isbn">
                            <p><input type="radio" name="coverBook" value="HARD">HARD
                                <input type="radio" name="coverBook" value="SOFT">SOFT
                                <input type="radio" name="coverBook" value="EXCLUSIVE">EXCLUSIVE
                            <p><input type="submit" name="submit" value="Create Book" /></p>
                        </details>
                    </form>
                </c:if>
            </c:if>
            <p>All counts: ${requestScope.book_count}</p>
            <table class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>DataPurchase</th>
                        <th>Price</th>
                        <th>Cover</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.books}" var="book" varStatus="counter">
                        <tr>
                            <td class="center">
                                <c:if test="${sessionScope.user != null}">
                                    <c:if test="${sessionScope.user.role.toString() == ADMIN}">
                                        <input type="radio" name="id" value="${book.id}">
                                    </c:if>
                                </c:if>
                                ${counter.count}
                            </td>
                            <td><a href="/books/find_book_by_id/${book.id}">${book.title}</a></td>
                            <td>${book.nameAuthor}</td>
                            <td class="center">${book.dateReleaseBook}</td>
                            <td class="center">${book.price}</td>
                            <td class="center">${book.coverBook}</td>123
                            <td class="center">
                                <form action="/cart/book_to_cart" method="post">
                                    <button name="id" value="${book.id}" />Add to Cart</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${sessionScope.user != null}">
                <c:if test="${sessionScope.users.role.toString() == ADMIN}">
                    <input type="submit" name="id" value="Delete Book" />
                </c:if>
            </c:if>
            </form>
        </body>

        </html>