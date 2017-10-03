<%-- 
    Document   : mainPage
    Created on : Sep 18, 2017, 5:01:51 PM
    Author     : 715583
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Home Page</h1>
        <p>Hello,${user.username}</p>
        <p><a href="login?action=logout" >Logout</a></p>
    </body>
</html>
