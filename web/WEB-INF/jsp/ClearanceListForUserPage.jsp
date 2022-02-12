<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>

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
    <h2><fmt:message key="clearanceListPage3" bundle="${rb}"/></h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Start</th>
            <th>Finish</th>
            <th>Operation</th>
            <th>Discount,%</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="clearance" items="${clearanceList}">
            <tr>
                <td>${clearance.getId()}</td>
                <td>${clearance.getName()}</td>
                <td>${clearance.getStartDate()}</td>
                <td>${clearance.getEndDate()}</td>
                <td>${clearance.getOperation()}</td>
                <td>${clearance.getDiscount()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
