<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <style>
    .form form {
    width: 300px;
    height: border-box;
    margin: 0 auto;
    padding-top: 20px;
    }
    </style>
</head>
<body>
<div class="form">
<form class="form-horizontal" action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="path" value="/ManagerPage.jsp"/>
    <input type="hidden" name="command" value="UPDATE_ORDER"/>
    <input type="hidden" name="order" value="${pageContext.request.getParameter("updOrder")}"/>

    <div class="form-group col-sm-10">
    <select name="operation">
        <c:forEach items="${operationList}" var="operation">
            <option>${operation}</option>
        </c:forEach>
    </select>
    </div>
    <fmt:message key="updateOrderPage1" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" value="${msg}"/>
</form>
    </div>
</body>
</html>
