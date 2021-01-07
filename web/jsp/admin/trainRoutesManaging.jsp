<%@ page import="lab2_5.com.company.neophite.model.entity.Station" %>
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
<a href="/admin/routeCreationPage">Create Train Route</a>
<br>
<div class="center">
    <h1>List of stations</h1>

    <table border="1">
        <tr>
            <td>Route id</td>
            <td>Start station</td>
            <td>Start Time</td>
            <td>Finish Station</td>
            <td>Finish Date</td>
        </tr>
        <c:forEach var="route" items="${routes}">
            <tr>
                <td><c:out value="${route.id}"/></td>
                <td><c:out value="${route.startStation}"/></td>
                <td><c:out value="${route.startDate}"/></td>
                <td><c:out value="${route.finishStation}"/></td>
                <td><c:out value="${route.finishDate}"/></td>
                <td>
                    <a href="/admin/deleteRoute?routeId=${route.id}&startDate=${route.startDate}&endDate=${route.finishDate}">Delete</a>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
