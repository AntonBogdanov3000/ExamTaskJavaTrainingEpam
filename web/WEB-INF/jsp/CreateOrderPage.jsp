<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style>
        .form form {
            width: 300px;
            height: border-box;
            margin: 0 auto;
            padding-top: 20px;
        }
        body{
            background-image: url("https://www.cio.com/wp-content/uploads/2021/12/at-your-service_customer-service_customer-centric_service-bell_by-bgton-getty-100810207-orig-3.jpg?quality=50&strip=all");
            background-size: cover;
        }
    </style>
</head>
<body>
<form class="form-inline " action="${pageContext.request.contextPath}/control" method="post">
    <label class="col-sm-6"><fmt:message key="createOrderPage6" bundle="${rb}"/>:</label>
    <div class="col-sm-10 form-group">
        <input type="hidden" name="command" value="GET_CLIENT_CARS">
        <input type="hidden" name="path" value="CreateOrderPage.jsp">
        <fmt:message key="createOrderPage7" var="msg" bundle="${rb}"/>
        <input type="text" name="login" value="" placeholder="${msg}">
        <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-search"></span></button>
        <small>${wrongLogin}</small>
    </div>
</form>
<br><br>
<div class="form">
<form class="form-horizontal" action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="path" value="/ManagerPage.jsp"/>
    <input type="hidden" name="manager" value="${pageContext.request.getAttribute("manager")}"/>
    <input type="hidden" name="command" value="CREATE_ORDER"/>

        <div class="form-group">
<label class="col-sm-6"><fmt:message key="createOrderPage1" bundle="${rb}"/>:</label>
            <div class="col-sm-10">
                <input type="text" value="${customer.getName()} ${customer.getLastName()}">
                <input type="hidden" name="login" value="${customer.getLogin()}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-6"><fmt:message key="createOrderPage2" bundle="${rb}"/>:</label>
            <div class="col-sm-10">
            <select name="cars">
            <c:forEach items="${customerCars}" var="car">
                <option>${car}</option>
            </c:forEach>
        </select>
        </div>
        </div>
    <label class="col-sm-6"><fmt:message key="createOrderPage5" bundle="${rb}"/>:</label>
        <div class="form-group col-sm-10">
    <select name="operation">
        <c:forEach items="${operationList}" var="operation">
            <option>${operation}</option>
        </c:forEach>
    </select>
        </div>
        <div class="form-group">
<label class="col-sm-6"><fmt:message key="createOrderPage3" bundle="${rb}"/>:</label>
            <div class="col-sm-10">
    <input type="text" name="price" value=""/>
            </div>
        </div>
    <fmt:message key="createOrderPage4" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" value="${msg}"/>
</form>
</div>
</body>
</html>
