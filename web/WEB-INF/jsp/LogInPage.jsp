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
        body{
            color: azure;
            background-image: url("https://dukestreetmotors.co.uk/wp-content/uploads/2018/07/Duke-Street-Motors-Garage-Entrance-1.jpg");
            background-size: cover;
        }
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
<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="command" value="LOGIN"/>

    <div class="form-group">
    <div class="form-group">
    <label class="col-sm-6"><fmt:message key="loginPage1" bundle="${rb}"/></label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="login" value=""/>
    </div>
    </div>

    <small>${errorLoginMessage}</small>

    <div class="form-group">
   <label class="col-sm-6"><fmt:message key="loginPage2" bundle="${rb}"/></label>
        <div class="col-sm-10">
            <input type="password" class="form-control" name="password" value=""/>
        </div>
    </div>
    <fmt:message key="loginPage3" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" value="${msg}"/>
</form>
</div>
</body>
</html>
