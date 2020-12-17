<%@ page import="lab3.com.company.neophite.model.entity.TrainTrip" %>
<%@ page import="java.util.List" %>
<%@ page import="lab3.com.company.neophite.model.entity.User" %>
<%@ page import="lab3.com.company.neophite.model.entity.Role" %><%--
  Created by IntelliJ IDEA.
  User: Stami
  Date: 01.12.2020
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Залізнична касса</title>
    <style>
        <%@include file="/css/mainPage.css"%>
    </style>
</head>
<div class="center">
    <% if (request.getSession().getAttribute("user") != null) {%>
    <%@include file="user-header.jspf" %>
    <%if (((User)(request.getSession().getAttribute("user"))).getRoles().contains(new Role("Admin"))) { %>
    <%@include file="admin-header.jspf" %>
    <% } %>
    <% } else  { %>
    <%@include file="guest-header.jspf" %>
    <%} %>
    <body>
    <h1>Railway Ticket office</h1>
    <form action="trips" method="post">
        <input type="text" name="fromStation" placeholder="From">
        <input type="text" name="toStation" placeholder="To">
        <input type="submit" value="Find">
    </form>
    <table border="1">
        <% if
        (request.getAttribute("trips") != null) {%>
        <tr>
            <td> Trip NO</td>
            <td>From station</td>
            <td>To station</td>
            <td>Price $</td>
            <td>Train model</td>
            <td>Available seats</td>
            <% if
            (request.getSession().getAttribute("user") != null) { %>

            <% }%>
        </tr>
        <%
            for (TrainTrip trainTrip : (List<TrainTrip>) request.getAttribute("trips")) {
        %>

        <tr>
            <td><%= trainTrip.getId()  %>
            </td>
            <td><%= trainTrip.getTraintRoute().getStartStation().getName() %>
            </td>
            <td><%= trainTrip.getTraintRoute().getFinishStation().getName() %>
            </td>
            <td><%= trainTrip.getPrice() %>
            </td>
            <td><%= trainTrip.getTrainId().getModel()  %>
            </td>
            <td><%= trainTrip.getAvailableSeats()  %>
            </td>
            <% if (request.getSession().getAttribute("user") != null
            ) { %>
            <td>
                <a href="/buyTicket?trainTrip=<%=trainTrip.getId()%>">Buy Ticket</a>
            </td>
            <% }%>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
<div>

</div>
</body>
</html>
