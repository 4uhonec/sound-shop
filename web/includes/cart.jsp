
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="sound.entities.Cart" %>
<%@ page import="sound.entities.Item" %>
<%@ page import="sound.entities.LineItem" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/sound-shop/styles/main.css"/>
        <title>Cart</title>
    </head>
    <body>
        <%@include file="/includes/header.jsp" %>
        <c:choose>
            <c:when test="${not empty user}">
                <%
                    ServletContext sc = request.getServletContext();
                    Cart cart = (Cart)sc.getAttribute("cart");
                    List<LineItem> lineItems = cart.getItems();
                    
                    int total = 0;
                    for(LineItem item: lineItems){
                        total += item.getTotal();
                    }
                    sc.setAttribute("lineitems", cart.getItems());
                %>
                <h1>Items cart</h1>
                <table border="1">
                    <tr>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                    <c:forEach var="item" items="${lineitems}">
                        <tr>
                            <td>${item.item.name}</td>
                            <td>${item.quantity}</td>
                            <td>${item.total}</td>
                            <td>
                                <form action="/sound-shop/CartServlet?" method="get">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="item" value="${item.item.code}">
                                    <button type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <h3>Total price: <%= total %></h3>
            </c:when>
            <c:otherwise>
                <h1>You have to be logged in to use cart</h1>
                <a href="/sound-shop/includes/login.jsp" class="button">Login</a>
                <a href="/sound-shop/includes/register.jsp" class="button">Register</a>
            </c:otherwise>
        </c:choose>
            <a href="/sound-shop/index.jsp">Back to main page</a>
    </body>
</html>
