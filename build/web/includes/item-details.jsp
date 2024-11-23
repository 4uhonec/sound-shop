
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="sound.entities.Item" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/sound-shop/styles/main.css"/>
    <title>Item Detail</title>
    <style>

        body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

.item-container {
    display: flex;
    padding: 20px;
}

.item-details {
    flex: 1;
    padding-right: 20px;
}

.item-image {
    max-width: 30%;
    text-align: right;
}

.item-image img {
    max-width: 100%;
    height: auto;
}
    </style>
</head>
<body>
    <%@include file="/includes/header.jsp" %>

<div class="item-container">
    <%
        Item item = (Item) request.getAttribute("item");
        if (item != null) {
    %>
    <%
        String url = "images/" + item.getCode() + "-big.jpg";
    %>
    <div class="details-image">
        <img src="<%= url %>" alt="<%= item.getName() %>">
    </div>
    <div class="item-details">
        <h1><%= item.getName() %></h1>
        <p><strong>Description:</strong> <%= item.getDescription() %></p>
        <p><strong>Category:</strong> <%= item.getCategory() %></p>
        <p><strong>Price:</strong> $<%= item.getPrice() %></p>
        <c:choose>
            <c:when test="${not empty user}">
                <form action="/sound-shop/CartServlet" method="get">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name ="item" value="${item.code}">
                    <button type="submit">Add to cart</button>
                </form>
                
                <c:choose>
                    <c:when test="${user.role == 'admin'}">
                        <form action="/sound-shop/EditItemServlet" method="post">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="item" value="${item.code}">
                            <button type="submit">Edit item</button>
                        </form>
                        <form action="/sound-shop/EditItemServlet" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="item" value="${item.code}">
                            <button type="submit">Delete item</button>
                        </form>
                    </c:when>
                </c:choose>
            </c:when>
        </c:choose>
        <a href="/sound-shop/index.jsp" style="text-align:center;">Back to Home</a>
    </div>
    <%
        } else {
    %>
        <p>Item not found.</p>
        <a href="/sound-shop/index.jsp" style="text-align:center;">Back to Home</a>
    <%
        }
    %>
    
</div>

</body>
</html>
