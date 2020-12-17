<%--
  Created by IntelliJ IDEA.
  User: Stami
  Date: 10.12.2020
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        <%@include file="/css/mainPage.css"%>
    </style>
</head>
<div class="center">
<body>
<h1>Login Page</h1>
<form action="login" method="post">
    <input type="text" name="username" placeholder="Username">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" value="Login">
</form>
<%if(request.getAttribute("error")!=null){ %>
<p class="error"><%= request.getAttribute("error")%></p>
<%}%>
</body>
</div>
</html>
