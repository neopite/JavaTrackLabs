<%--
  Created by IntelliJ IDEA.
  User: Stami
  Date: 17.12.2020
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="center">
    <h1>Edit station</h1>
    <form action="/editStation" method="post">
        <input name="stationId" value="<%=request.getAttribute("stationId")%>" readonly>
        <input type="text" placeholder="New name" name="stationName">
        <input type="submit" value="Edit">
        </form>
</div>
</body>
</html>
