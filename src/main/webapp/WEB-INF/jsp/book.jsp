<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>

    <head>
        <title>View books in BookStore</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>

    <body>

        <div>
            <%@ include file="menu.jsp" %>
        </div>

        <h2 align="center">Book: ${requestScope.book.title}</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Author:</th>
                    <th>Date release books:</th>
                    <th>Price:</th>
                    <th>ISBN:</th>
                    <th>Cover:</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        ${requestScope.book.nameAuthor}
                    </td>
                    <td class="center">
                        ${requestScope.book.dateReleaseBook}
                    </td>
                    <td class="center">
                        ${requestScope.book.price}
                    </td>
                    <td class="center">
                        ${requestScope.book.isbn}
                    </td>
                    <td class="center">
                        ${requestScope.book.coverBook}
                    </td>
                </tr>
            </tbody>
        </table>
        <c:if test="${sessionScope.user.role.toString() == ADMIN}">
            <form action="/books/book_update_form" method="post">
                <input type="hidden" name="id" value="${requestScope.book.id}" />
                <input type="submit" name="deleted" value="Update Book" />
            </form>
            <form action="/books/book_deleted" method="post">
                <input type="hidden" name="id" value="${requestScope.book.id}" />
                <input type="submit" name="deleted" value="Delete Book" />
            </form>
        </c:if>
        <a href="#" onclick="history.back();return false;" class="history-back">
            <-Back< /a>
    </body>

    </html>