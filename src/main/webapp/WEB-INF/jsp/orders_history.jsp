<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>Orders history</title>
            <link rel="stylesheet" href="/css/style.css">
        </head>

        <body>

            <div>
                <%@ include file="menu.jsp" %>
            </div>

            <h3>Orders list:</h3>
            <c:forEach items="${requestScope.orders}" var="order" varStatus="counter">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Cover</th>
                            <th>Status</th>
                            <th>Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${order.items}" var="order_item" varStatus="counter">
                            <tr>
                                <td class="center">${counter.count}</td>
                                <td><a href="/books/find_book_by_id/${order_item.book.id}">${order_item.book.title}</a>
                                </td>
                                <td>${order_item.book.nameAuthor}</td>
                                <td class="center">${order_item.quantity}</td>
                                <td class="center">${order_item.book.price}</td>
                                <td class="center">${order_item.book.coverBook}</td>
                                <td class="center">${order.status}</td>
                                <td class="center"></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td></td>
                            <td>Total cost</td>
                            <td></td>
                            <td></td>
                            <td class="center">${total_cost_cart}</td>
                            <td></td>
                            <td></td>
                            <td class="center">
                                <form action="/orders/confirm_order" method="post">
                                    <button name="id_order" value="${order.id}" />Confirm</button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
                </form>
            </c:forEach>

            <a href="#" onclick="history.back();return false;" class="history-back">
                <-Back< /a>
        </body>

        </html>