<%@ page import="lab3.com.company.neophite.model.entity.User" %>
<%@ page import="lab3.com.company.neophite.model.entity.Ticket" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Stami
  Date: 17.12.2020
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="center">
<h1>All bought tickets of user : <c:out value="${user.name}"/>
</h1>
    <table border="1">
        <tr>
            <td>Ticket ID</td>
            <td>Train Model</td>
            <td>Station Start</td>
            <td>Time From</td>
            <td>Station End</td>
            <td>Time end</td>
            <td>Price</td>
            <td>Place</td>
        </tr>
        <c:forEach var="ticket" items="${usrTickets}">
        <tr>
            <td><c:out value="${ticket.id}" /></td>
            <td><c:out value="${ticket.trainTripId.trainId.model}" /></td>
            <td><c:out value="${ticket.trainTripId.trainRoute.startStation.name}" /></td>
            <td><c:out value="${ticket.trainTripId.trainRoute.startDate}" /></td>
            <td><c:out value="${ticket.trainTripId.trainRoute.finishStation.name}" /></td>
            <td><c:out value="${ticket.trainTripId.trainRoute.finishDate}" /></td>
            <td><c:out value="${ticket.trainTripId.price}" /></td>
            <td><c:out value="${ticket.place}" /></td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
