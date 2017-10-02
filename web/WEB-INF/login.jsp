<%-- 
    Document   : login
    Created on : Sep 18, 2017, 5:55:28 PM
    Author     : 715583
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="index" method="post">
            <p>Username:<input type="text" name="Uname" value="${username}"></p>
            <p>Password:<input type="text" name="Pword" value="${password}"></p>
            <input type="submit" value="Login">
        </form>
            ${display}
    </body>
</html>
