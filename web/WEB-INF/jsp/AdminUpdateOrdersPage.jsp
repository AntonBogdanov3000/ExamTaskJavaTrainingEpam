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
    <h2><fmt:message key="adminUpdOrdersPage1" bundle="${rb}"/></h2>
    <table class="table table-striped">
        <thead>
        <tr>
        <th>ID</th>
        <th></th>
        <th>Date</th>
        <th>Price,$</th>
        <th>Operations</th>
        <th>Manager id</th>
        <th>Client id</th>
        </tr>
        </thead>
        <tbody>
    <c:forEach var="order" items="${orderList}">
        <tr>
            <td>${order.getId()}<td>
            <td>${order.getDate()}</td>
            <td>${order.getPrice()}</td>
            <td>${order.getOperationList()}</td>
            <td>${order.getManagerId()}</td>
            <td>${order.getUserId()}</td>
            <td>
            <form action="${pageContext.request.contextPath}/control" method="post">
                <input type="hidden" name="path" value="AdminPage.jsp">
                <input type="hidden" name="command" value="DELETE_ORDER">
                <input type="hidden" name="order" value="${order.getId()}">
                <fmt:message key="adminUpdOrdersPage2" var="msg" bundle="${rb}"/>
                <input type="submit" class="btn-danger" value="${msg}"/>
            </form>
                </td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
