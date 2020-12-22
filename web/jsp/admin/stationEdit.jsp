
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="center">
    <h1>Edit station</h1>
    <form action="/admin/editStation" method="post">
        <input name="stationId" value="${stationId}" readonly>
        <input type="text" placeholder="New name" name="stationName">
        <input type="submit" value="Edit">
        </form>
</div>
</body>
</html>
