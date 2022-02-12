<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<form class="form-horizontal">
    <input type="hidden" name="path" value="AdminPage.jsp"/>
    <input type="hidden" name="command" value="CREATE_CLEARANCE"/>
    <div class="form-group">
        <div class="form-group">
    <label class="col-sm-6"><fmt:message key="clearanceCreatePage1" bundle="${rb}"/>:</label>
        <div class="col-sm-10">
        <select name="operation">
            <c:forEach items="${operationList}" var="operation">
                <option>${operation}</option>
            </c:forEach>
        </select>
        </div>
    </div>
        <div class="form-group">
    <label class="col-sm-6"><fmt:message key="clearanceCreatePage2" bundle="${rb}"/>:</label>
            <div class="col-sm-10">
        <input type="text" name="name">
            </div>
        </div>
        <small> ${nullEnter}</small>
     <div class="form-group">
    <label class="col-sm-6"><fmt:message key="clearanceCreatePage3" bundle="${rb}"/> %:</label>
        <div class="col-sm-10">
        <input type="text" name="discount"/>
        </div>
     </div>
        <small>${wrongDiscount}</small>
    <div class="form-group">
    <label class="col-sm-6"><fmt:message key="clearanceCreatePage4" bundle="${rb}"/>:</label>
        <div class="col-sm-10">
    <input type="date" name="startDate"/>
        </div>
    </div>
        <div class="form-group">
    <label class="col-sm-6"><fmt:message key="clearanceCreatePage5" bundle="${rb}"/>:</label>
            <div class="col-sm-10">
    <input type="date" name="endDate"/>
    </div>
        </div>
        <small>${IncorrectDate}</small>
    <fmt:message key="clearanceCreatePage6" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" value="${msg}"/>
</form>
</div>
</body>
</html>
