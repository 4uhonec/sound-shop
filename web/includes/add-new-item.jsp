

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/sound-shop/styles/main.css"/>
        <title>Add new item</title>
    </head>
    <body>
        <%@include file="/includes/header.jsp" %>
        <c:choose>
            <c:when test="${not empty user and user.role=='admin'}">
                <form action="/sound-shop/AdminItemsServlet" method="post">
                    <input type="hidden" name="action" value="add-item">
                    <p>
                        <label>Name:</label>
                        <input type="text" name="name" required>
                    </p>
                    <p>
                        <label>Code:</label>
                        <input type="text" name="code" required>
                    </p>
                    <p>
                        <label>Description:</label>
                        <textarea name="description" rows="4" cols="50" required></textarea>
                    </p>
                    <p>
                        <label>Category:</label>
                        <input type="text" name="category" required>
                    </p>
                    <p>
                        <label>Price:</label>
                        <input type="number" name="price" step="0.01" required>
                    </p>
                    <p>
                        <input type="submit" value="Add Item">
                    </p>
                </form>
                <h2>${message}</h2>
            </c:when>
            <c:otherwise>
                <<h2>You have to be admin to access this page</h2>
            </c:otherwise>
        </c:choose>
                <a href="/sound-shop/index.jsp">Main page</a>
    </body>
</html>
