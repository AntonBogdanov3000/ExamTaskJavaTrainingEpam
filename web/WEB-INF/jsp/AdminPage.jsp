<%@ page contentType="text/html;charset=UTF-8"%>
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
    <fmt:message key="adminPage1" var="msg" bundle="${rb}"/>
    <h3>${msg}, ${userName}</h3>
    <br><br>
    <div class="form-group">
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="path" value="AdminUpdateOrdersPage.jsp"/>
    <input type="hidden" name="idManager" value=""/>
    <input type="hidden" name="command" value="SHOW_ORDERS"/>
    <fmt:message key="adminPage2" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-info form-control" value="${msg}"/>
</form>
        <p></p>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="path" value="ClientsListPage.jsp"/>
    <input type="hidden" name="role" value="1">
    <input type="hidden" name="command" value="SHOW_CLIENTS"/>
    <fmt:message key="adminPage3" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-info form-control" value="${msg}"/>
</form>
        <p></p>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="path" value="ClientsListPage.jsp"/>
    <input type="hidden" name="role" value="2"/>
    <input type="hidden" name="command" value="SHOW_CLIENTS"/>
    <fmt:message key="adminPage4" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-info form-control" value="${msg}"/>
</form>
        <p></p>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="path" value="registration.jsp"/>
    <input type="hidden" name="command" value=""/>
    <input type="hidden" name="number" value="3"/>
    <input type="hidden" name="role" value="2"/>
    <fmt:message key="adminPage5" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-info form-control" value="${msg}"/>
</form>
        <p></p>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="path" value="ClearanceListPage.jsp"/>
    <input type="hidden" name="command" value="SHOW_ALL_CLEARANCE"/>
    <fmt:message key="adminPage6" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-info form-control" value="${msg}"/>
</form>
        <p></p>
<form action="${pageContext.request.contextPath}/control">
    <input type="hidden" name="path" value="AdminCarListPage.jsp"/>
    <input type="hidden" name="command" value="SHOW_ALL_CARS"/>
    <fmt:message key="adminPage8" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-info form-control" value="${msg}"/>
</form>
        <p></p>
<form method="get">
    <input type="hidden" name="path" value="/main.jsp"/>
    <input type="hidden" name="command" value="LOGOUT"/>
    <fmt:message key="adminPage7" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" name="logout" value="${msg}"/>
</form>
    </div>
</div>
</body>
</html>
