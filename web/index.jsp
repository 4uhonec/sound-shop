
<%@page import="sound.database.ItemDB"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/main.css"/>
    <title>Sound Store</title>
    
</head>
<body>
    
    <%@include file="/includes/header.jsp" %>
    
    <nav id="main-menu">
        <a href="<c:url value='/CatalogServlet?category=all' />">all</a>
        <c:forEach var="item" items="${categories}">
            <a href="<c:url value='/CatalogServlet?category=${item}' />">${item}</a>
        </c:forEach>
    </nav>
        <% ServletContext sc = request.getServletContext();
        sc.setAttribute("items", ItemDB.getItems());
        sc.setAttribute("categories", ItemDB.getCategories());%>
    <div class="grid">
        <c:forEach var="item" items="${items}">
            <div class="item">
                <img src="images/${item.code}.jpg" alt="${item.code}">
                <h4>${item.name}</h4>
                <p>Price: ${item.price}$</p>
                <a href="<c:url value='/DetailsServlet?item=${item.code}'/>">View details</a>
            </div>
        </c:forEach>
        
    </div>
        
    <footer>
        <p>Copyright © 2024 Sound Store</p>
    </footer>
</body>
</html>

