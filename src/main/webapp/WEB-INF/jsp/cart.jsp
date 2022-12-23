<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>Cart</title>
            <link rel="stylesheet" href="/css/style.css">
        </head>

        <body>

            <div>
                <%@ include file="menu.jsp" %>
            </div>

            <h3>Shopping list:</h3>
            <table class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Cover</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.books}" var="book" varStatus="counter">
                        <tr>
                            <td class="center">${counter.count}</td>
                            <td><a href="/books/find_book_by_id/${book.id}">${book.title}</a></td>
                            <td>${book.nameAuthor}</td>
                            <td class="center">
                                <form action="/cart/book_q" method="post">
                                    <input type="hidden" name="plus" value="true" />
                                    <button name="id" value="${book.id}" />+</button>
                                </form> ${quantity.get(book.id)}
                                <form action="/cart/book_q" method="post">
                                    <input type="hidden" name="plus" value="false" />
                                    <button name="id" value="${book.id}" />-</button>
                                </form>
                            </td>
                            <td class="center">${book.price}</td>
                            <td class="center">${book.coverBook}</td>
                            <td class="center">
                                <form action="/cart/book_delete" method="post">
                                    <button name="id" value="${book.id}" />Remove from Cart</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td>Total cost</td>
                        <td></td>
                        <td></td>
                        <td class="center">${total_cost_cart}</td>
                        <td></td>
                        <td class="center">
                            <c:if test="${sessionScope.cart != null}">
                                <form action="/orders/order_formation" method="post">
                                    <button name="id" value="${book.id}" />Create order</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </tbody>
            </table>
            </form>

            <a href="#" onclick="history.back();return false;" class="history-back">
                <-Back< /a>
        </body>

        </html>