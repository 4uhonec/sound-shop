

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css"/>
        <title>Login</title>
    </head>
    <body>
        <div class="login-wrapper">
            <div class="login-container">
                <h1>Login</h1>
                <form action="/sound-shop/login" method="post">
                    <input type="text" name="email" placeholder="Email" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <button type="submit">Login</button>
                </form>
                <a href="register.jsp">Don't have an account? Register</a>
                <h4>${message}</h4>
            </div>
        </div>
    </body>
</html>
