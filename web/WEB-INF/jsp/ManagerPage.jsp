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
    <fmt:message key="managerPage1" var="title_msg" bundle="${rb}"/>
    <h2>${title_msg}, ${userName}</h2>
    <div class="form-group">
<form action="${pageContext.request.contextPath}/control">
    <input type="hidden" name="command" value=""/>
    <input type="hidden" name="number" value="2"/>
    <input type="hidden" name="role" value="1"/>
    <input type="hidden" name="path" value="/registration.jsp"/>
    <fmt:message key="managerPage2" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-info form-control" name="button" value="${msg}"/>
</form>
    </div>
    <div class="form-group">
<form action="${pageContext.request.contextPath}/control">
    <input type="hidden" name="command" value="SHOW_PRICES"/>
    <input type="hidden" name="path" value="/CreateOrderPage.jsp"/>
    <fmt:message key="managerPage3" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-info form-control" value="${msg}"/>
</form>
    </div>
<div class="form-group">
<form action="${pageContext.request.contextPath}/control">
    <input type="hidden" name="command" value="READ_USER_ORDER"/>
    <input type="hidden" name="path" value="/UpdateOrdersPage.jsp"/>
    <input type="text" name="login" value=""/>
    <fmt:message key="managerPage4" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-info" value="${msg}"/>
</form>
</div>
${nullClientMessage}
    <div class="form-group">
    <form method="get">
        <input type="hidden" name="path" value="/OrderListPage.jsp"/>
        <input type="hidden" name="command" value="SHOW_ORDERS"/>
        <input type="hidden" name="idManager" value="${pageContext.session.getAttribute("idManager")}"/>
        <fmt:message key="managerPage6" var="msg" bundle="${rb}"/>
        <input type="submit" class="btn btn-info form-control" name="myOrders" value="${msg}"/>
    </form>
    </div>
    <div class="form-group">
        <form action="${pageContext.request.contextPath}/control" method="post">
            <input type="hidden" name="path" value="ClearanceListForUserPage.jsp">
            <input type="hidden" name="command" value="SHOW_ALL_CLEARANCE">
            <fmt:message key="managerPage7" var="msg" bundle="${rb}"/>
            <button type="submit" class="form-control btn btn-info">${msg}</button>
        </form>
    </div>
<form method="get">
    <input type="hidden" name="path" value="/main.jsp"/>
    <input type="hidden" name="command" value="LOGOUT"/>
    <fmt:message key="managerPage5" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" name="logout" value="${msg}"/>
</form>
</div>
</body>
</html>
