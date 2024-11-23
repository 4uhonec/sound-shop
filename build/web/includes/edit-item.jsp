
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/sound-shop/styles/main.css"/>
        <title>Edit item</title>
    </head>
    <body>
        <%@include file="/includes/header.jsp" %>
        <h1>Edit Item</h1>
        <form action="/sound-shop/EditItemServlet" method="post">
            <input type="hidden" name="action" value="submit-update">
            <input type="hidden" name="id" value="${item.itemId}">
            <p>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${item.name}" required>
            </p>
            <input type="hidden" name="code" value="${item.code}"
            <p>
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="7" cols="100" required>${item.description}</textarea>
            </p>
            <p>
                <label for="code">Category:</label>
                <input type="text" id="code" name="category" value="${item.category}" required>
            </p>
            <p>
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" value="${item.price}" step="0.01" required>
            </p>
            <p>
                <input type="submit" value="Update Item">
            </p>
        </form>
                <h4>${message}</h4>
    </body>
</html>
