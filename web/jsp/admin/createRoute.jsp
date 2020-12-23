<%--
  Created by IntelliJ IDEA.
  User: Stami
  Date: 23.12.2020
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/admin/createRoute" method="post">
    <input type="text" name="startStation" placeholder="Start station">
    <input type="datetime-local" name="startDate" placeholder="Time start of route">
    <input type="text" name="endStation" aria-autocomplete="Finish Station">
    <input  type="datetime-local" name="endDate" placeholder="Time of arriving">
    <input type="submit" value="Create">
</form>

<div class="center">
    <h1>List of stations</h1>

    <table border="1">
        <tr>
            <td>No station</td>
            <td>Station name</td>
        </tr>
        <c:forEach var="station" items="${stations}">
            <tr>
                <td><c:out value="${station.id}"/></td>
                <td><c:out value="${station.name}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
