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
<p></p>
${session.setAttribute("f5ForCar",0)}
<div class="container">
    <fmt:message key="welcomePage1" var="title_msg" bundle="${rb}"/>
    <h3>${title_msg}, ${userName}</h3>
    <div class="form-group">
<form action="${pageContext.request.contextPath}/control" method="post">
 <input type="hidden" name="login" value="${pageContext.request.getParameter("login")}"/>
 <input type="hidden" name="path" value="/OrderListPage.jsp"/>
 <input type="hidden" name="command" value="READ_USER_ORDER"/>
 <fmt:message key="welcomePage2" var="msg" bundle="${rb}"/>
 <input type="submit" class="btn btn-info form-control" name="orders" value="${msg}"/>
</form>
    </div>
    <div class="form-group">
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="login" value="${pageContext.request.getParameter("login")}"/>
    <input type="hidden" name="path" value="/CarListPage.jsp"/>
    <input type="hidden" name="command" value="READ_USER_CAR"/>
    <fmt:message key="welcomePage3" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-info form-control" name="cars" value="${msg}"/>
</form>
    </div>
    <div class="form-group">
        <form action="${pageContext.request.contextPath}/control" method="post">
            <input type="hidden" name="path" value="ClearanceListForUserPage.jsp">
            <input type="hidden" name="command" value="SHOW_ALL_CLEARANCE">
            <fmt:message key="welcomePage5" var="msg" bundle="${rb}"/>
            <button type="submit" class="form-control btn btn-info">${msg}</button>
        </form>
    </div>
    <div class="form-group">
        <form action="${pageContext.request.contextPath}/control" method="post">
            <input type="hidden" name="path" value="UserPersonalUpdate.jsp">
            <input type="hidden" name="command" value="UPDATE_BY_USER"/>
            <input type="hidden" name="upd" value="upd"/>
            <fmt:message key="welcomePage6" var="msg" bundle="${rb}"/>
            <button type="submit" class="form-control btn btn-warning">${msg}</button>
        </form>
    </div>
    <div class="form-group">
<form>
    <input type="hidden" name="login" value="${pageContext.request.getParameter("login")}"/>
    <input type="hidden" name="path" value="/main.jsp"/>
    <input type="hidden" name="command" value="LOGOUT"/>
    <fmt:message key="welcomePage4" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" name="logout" value="${msg}"/>
</form>
    </div>
</div>
</body>
</html>
