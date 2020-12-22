
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/css/mainPage.css"%>
    </style>
</head>
<div class="center">

<body>
<h1>Registration form</h1>
    <form action="/registration" method="post">
        <p><input type="text" name="username" placeholder="username"></p>
        <p><input type="text" name="password" placeholder="password"></p>
        <p><input type="text" name="email" placeholder="email"></p>
        <p><input type="text" name="age" placeholder="age"></p>
        <p><input type="text" name="name" placeholder="name"></p>
        <div>
            <input type="submit" value="Send">
        </div>
        <p class="error">${error}</p>
</form>
</body>
</div>

</html>


