<%@ page import="lab2_5.com.company.neophite.model.entity.TrainTrip" %>
<%@ page import="java.util.List" %>
<%@ page import="lab2_5.com.company.neophite.model.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>Залізнична касса</title>
  <style>
    <%@include file="/css/mainPage.css"%>
  </style>
</head>
<div class="center">
  <c:if test="${user!=null}" >
    <%@include file="user-header.jspf" %>
  </c:if>
  <c:if test="${user==null}" >
    <%@include file="guest-header.jspf" %>
  </c:if>
  <body>
  <h1>Railway Ticket office</h1>
  <form action="/trips" method="post">
    <input type="text" name="fromStation" placeholder="From">
    <input type="text" name="toStation" placeholder="To">
    <input type="datetime-local" name="dateFrom">
    <input type="datetime-local" name="dateTo">
    <input type="submit" value="Find">
  </form>
  <table border="1">
    <tr>
      <td> Trip NO</td>
      <td>From station</td>
      <td>To station</td>
      <td>Price $</td>
      <td>Train model</td>
      <td>Available seats</td>
    </tr>
    <c:forEach items="${trips}" var="trip">
      <tr>
        <td>${trip.id}</td>
        <td>${trip.trainRoute.startStation.name}</td>
        <td>${trip.trainRoute.finishStation.name}</td>
        <td>${trip.price}</td>
        <td>${trip.trainId.model}</td>
        <td>${trip.availableSeats}</td>
        <c:if test="${user!=null}">
          <td>
            <a href="/user/buyTicket?trainTrip=${trip.id}">Buy Ticket</a>
          </td>
        </c:if>
      </tr>
    </c:forEach>
  </table>
  <p class="error">${error}</p>
  <p class="error">${dateError}</p>
  <p class="error">${errorMoney}</p>
  <%@include file="pagination.jsp"%>
</div>
</body>
</html>
