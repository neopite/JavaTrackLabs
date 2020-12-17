<%--
  Created by IntelliJ IDEA.
  User: Stami
  Date: 09.12.2020
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
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
    <form action="registration" method="post">
        <p><input type="text" name="username" placeholder="username"></p>
        <p><input type="text" name="password" placeholder="password"></p>
        <p><input type="text" name="email" placeholder="email"></p>
        <p><input type="text" name="age" placeholder="age"></p>
        <p><input type="text" name="name" placeholder="name"></p>
        <div>
            <input type="submit" value="Send">
        </div>
        <%if(request.getAttribute("error")!=null) { %>
        <p class="error"><%=request.getAttribute("error")%></p>
        <% }%>
</form>
</body>
</div>

</html>


