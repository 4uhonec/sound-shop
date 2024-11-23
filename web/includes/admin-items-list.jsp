<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sound.entities.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/sound-shop/styles/main.css"/>
        <title>Admin items list</title>
    </head>
    <body>
        <%@include file="/includes/header.jsp" %>
        <h1>Admin's items list</h1>
        <c:choose>
            <c:when test="${not empty user}">
                <c:choose>
                    <c:when test="${user.role == 'admin'}">
                        <h2>Items List</h2>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Code</th>
                                    <th>Category</th>
                                    <th>Price</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%List<Item> itemlist = (List) request.getAttribute("items-list");
                                for(Item item: itemlist){%>
                                    <tr>
                                        <td><%= item.getName()%></td>
                                        <td><%= item.getCode()%></td>
                                        <td><%= item.getCategory()%></td>
                                        <td><%= item.getPrice()%> $</td>
                                        <td>
                                            <form action="/sound-shop/AdminItemsServlet" method="get" style="display:inline;">
                                                <input type="hidden" name="action" value="edit">
                                                <input type="hidden" name ="item" value="<%=item.getCode()%>">
                                                <button type="submit">Edit</button>
                                            </form>
                                            <form action="/sound-shop/AdminItemsServlet" method="get" style="display:inline;">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name ="item" value="<%=item.getCode()%>">
                                                <button type="submit">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                            <form action="/sound-shop/includes/add-new-item.jsp" method ="get">
                                <button type="submit">Add new item</button>
                            </form>
                    </c:when>
                </c:choose>
            </c:when>
        </c:choose>
        <a href="index.jsp">Back to main page</a>
    </body>
</html>