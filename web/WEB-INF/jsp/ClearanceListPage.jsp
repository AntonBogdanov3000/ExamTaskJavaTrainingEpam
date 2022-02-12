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
        <td>
        <form action="${pageContext.request.contextPath}/control" method="get">
            <input type="hidden" name="path" value="AdminPage.jsp"/>
            <input type="hidden" name="command" value="DELETE_CLEARANCE"/>
            <input type="hidden" name="delClear" value="${clearance.getId()}"/>
            <fmt:message key="clearanceListPage1" var="msg" bundle="${rb}"/>
            <input type="submit" class="btn btn-danger" value="${msg}"/>
        </form>
        </td>
    </tr>
</c:forEach>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/control" method="get">
        <input type="hidden" name="path" value="ClearanceCreatePage.jsp"/>
        <input type="hidden" name="command" value="SHOW_PRICES"/>
        <fmt:message key="clearanceListPage2" var="msg" bundle="${rb}"/>
        <input type="submit" class="btn btn-success" value="${msg}"/>
    </form>
</div>
</body>
</html>
