    <header>
        <div class="login-header">
            <img src="/sound-shop/images/header-image.jpg" alt="Store Banner">
            <div class="button-container">
                <c:choose>
                    <c:when test="${not empty user}">
                        <h4>Greetings, ${user.firstName}</h4>
                        <c:choose>
                            <c:when test="${user.role == 'admin'}">
                                <a href="/sound-shop/AdminItemsServlet?action=list" class="button">Admin list</a>
                            </c:when>
                    </c:choose>
                        <a href="/sound-shop/includes/cart.jsp" class="button">Item cart</a>
                        <a href="<c:url value='/LogoutServlet'/>" class="button">Logout</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/sound-shop/includes/login.jsp" class="button">Login</a>
                        <a href="/sound-shop/includes/register.jsp" class="button">Register</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </header>
