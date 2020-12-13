<%@ page import="lab3.com.company.neophite.model.entity.TrainTrip" %>
<%@ page import="lab3.com.company.neophite.model.entity.Train" %>
<%@ page import="java.util.List" %><%--
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
</head>
<%@include file="guest-header.jspf" %>
<body>
<h1>Railway Ticket office</h1>
<form action="trips" method="post">
    <input type="text" name="fromStation" placeholder="From">
    <input type="text" name="toStation" placeholder="To">
    <input type="submit" value="Find">
</form>
<table>
    <% if (request.getAttribute("trips") != null) {%>
    <%for (TrainTrip trainTrip : (List<TrainTrip>) request.getAttribute("trips")) { %>
    <tr>
        <td><%= trainTrip.getId()  %></td>
        <td><%= trainTrip.getTraintRoute().getStartStation() %></td>
        <td><%= trainTrip.getTraintRoute().getFinishStation() %></td>
        <td><%= trainTrip.getPrice() %></td>
        <td><%= trainTrip.getTrainId()  %></td>
        <td><%= trainTrip.getAvailableSeats()  %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
