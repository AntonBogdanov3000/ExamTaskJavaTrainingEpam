<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
<fmt:setBundle basename="text" var="rb" scope="session"/>


<!DOCTYPE html>
<html lang="en">
<style >
    html{
       background-image: url('img/img.png');
        background-size: cover;
    }
</style>

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
    <h2><fmt:message key="priceListPage1" bundle="${rb}"/></h2>
    <table class="table table-striped">
<thead>
<tr>
    <th><h4>Operation</h4></th>
    <th><h4>Price, $</h4></th>
<tr>
</thead>
        <tbody>
    <c:forEach var="operation" items="${operationList}">
        <tr>
        <td>${operation.getOperationName()}</td>
        <td>${operation.getOperationPrice()}</td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
