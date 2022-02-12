<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
<fmt:setBundle basename="text" var="rb" scope="session"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2><fmt:message key="carListPage2" bundle="${rb}"/></h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Model</th>
            <th>Year</th>
            <th>Plate</th>
            <th>Mileage</th>
        </tr>
        </thead>
        <tbody>
   <c:forEach var="vehicle" items="${vehicleList}">
       <tr>
           <td>${vehicle.getModel()}</td>
           <td>${vehicle.getYear()}</td>
           <td>${vehicle.getPlate()}</td>
           <td>${vehicle.getMileage()}</td>
       </tr>
   </c:forEach>
        </tbody>
    </table>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="login" value="${pageContext.request.getParameter("login")}"/>
    <input type="hidden" name="command" value=""/>
    <input type="hidden" name="path" value="/AddCarPage.jsp"/>
    <fmt:message key="carListPage1" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" name="addCar" value="${msg}"/>
</form>
</div>
</body>
</html>
