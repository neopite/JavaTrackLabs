<%@ page import="lab3.com.company.neophite.model.entity.Station" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Managing</title>
</head>
<body>
<h1>Create new Station</h1>
<form action="/admin/addStation" method="post">
    <input type="text" name="station_name" placeholder="Name of the new Station">
    <input type="submit" value="Submit">
</form>
<br>
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
            <td>
                <a href="/admin/deleteStation?stationId=${station.id}">Delete</a>
            </td>
            <td>
                <a href="/admin/editStation?stationId=${station.id}">Edit</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
