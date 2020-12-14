<%@ page import="lab3.com.company.neophite.model.entity.Station" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Stami
  Date: 14.12.2020
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Managing</title>
</head>
<body>
<h1>Create new Station</h1>
<form action="/addStation">
    <input type="text" name="station_name" placeholder="Name of the new Station">
    <input type="submit" value="Submit">
</form>
<br>
<div class="center">
<h1>List of stations</h1>

<table border="1">
    <% if (request.getAttribute("stations") != null) {%>
    <tr>
        <td>No station</td>
        <td>Station name</td>
    </tr>
    <%for (Station station : (List<Station>) request.getAttribute("stations")) { %>
    <tr>
        <td><%= station.getId()  %></td>
        <td><%= station.getName() %></td>
        <td><a href="/deleteStation">Delete</a></td>
    </tr>
    <%
            }
        }
    %>
</table>
</div>
</body>
</html>
