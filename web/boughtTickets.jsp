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
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="center">
<h1>All bought tickets of user :<% if(request.getAttribute("user")!=null) {%>
    <%= ((User)(request.getAttribute("user"))).getUsername() %>
    <% }%>
</h1>
    <table border="1">
        <% if (request.getAttribute("usrTickets") != null) {%>
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
        <%for (Ticket ticket : (List<Ticket>) request.getAttribute("usrTickets")) { %>
        <tr>
            <td><%= ticket.getId()  %></td>
            <td><%= ticket.getTrainTripId().getTrainId().getModel()%></td>
            <td><%= ticket.getTrainTripId().getTraintRoute().getStartStation().getName() %></td>
            <td><%= ticket.getTrainTripId().getTraintRoute().getStartDate()%></td>
            <td><%= ticket.getTrainTripId().getTraintRoute().getFinishStation().getName()%></td>
            <td><%= ticket.getTrainTripId().getTraintRoute().getFinishDate()%></td>
            <td><%= ticket.getTrainTripId().getPrice()%></td>
            <td><%= ticket.getPlace()%></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
</body>
</html>
