<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
        <html lang="en" dir="ltr">

        <head>
            <meta charset="utf-8">
            <title>BookStoreError</title>
            <link rel="stylesheet" href="/css/style.css">
        </head>

        <body>
            <img height="150px" src="/images/404.png" alt="404 Page not found...">
            <div>${message != null ? message : 'Something went wrong...'}</div>
            <a href="#" onclick="history.back();return false;" class="history-back">
                <-Back< /a>
        </body>

        </html>