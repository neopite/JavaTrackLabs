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
</head>
<body>
    <form action="registration" method="post">
       <p> <input type="text" name="username" placeholder="username"></p>
       <p> <input type="text" name="password" placeholder="password"></p>
       <p> <input type="text" name="email" placeholder="email"></p>
       <p> <input type="text" name="age" placeholder="age"></p>
       <p> <input type="text" name="name" placeholder="name"></p>

        <div>
            <input type="submit" value="Send">
        </div>
    </form>
</body>
</html>


