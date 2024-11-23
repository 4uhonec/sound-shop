
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css"/>
        <title>Registration</title>
    </head>
    <body>
        <div class="register-container">
            <h1>Register</h1>
            <form action="/sound-shop/RegisterServlet" method="post">
                <input type="text" name="firstName" placeholder="First Name" required>
                <input type="text" name="lastName" placeholder="Last Name" required>
                <input type="text" name="address" placeholder="Address" required>
                <input type="email" name="email" placeholder="Email" required>
                <input type="password" name="password" placeholder="Password" required>
                <input type="submit" value="Register">
            </form>
            <h5>${message}</h5>
        </div>
        <p><a href="/sound-shop/index.jsp">Back to Home</a></p>
    </body>
</html>
