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
</head>
<body>
<div class="container">
    <fmt:message key="userUpdatePage1" var="title_msg" bundle="${rb}"/>
    <h3>${title_msg}</h3>
<form action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="path" value="AdminPage.jsp"/>
    <input type="hidden" name="command" value="UPDATE_USER"/>
    <input type="hidden" name="upd" value=""/>
    <input type="hidden" name="updUser" value="${pageContext.request.getAttribute("updUser")}"/>
<div class="form-group">
    <label><fmt:message key="userUpdatePage2" bundle="${rb}"/>:
    <input type="text" class="form-control" name="name" value="${pageContext.request.getAttribute("name")}">
    </label>
</div>
    <div class="form-group">
    <label><fmt:message key="userUpdatePage3" bundle="${rb}"/>:
    <input type="text" class="form-control" name="lastname" value="${pageContext.request.getAttribute("lastname")}">
    </label>
    </div>
    <div class="form-group">
    <label><fmt:message key="userUpdatePage4" bundle="${rb}"/>:
    <input type="text" class="form-control" name="role" value="${pageContext.request.getAttribute("role")}">
    </label>
    </div>
    <div class="form-group">
    <label><fmt:message key="userUpdatePage5" bundle="${rb}"/>:
    <input type="text" class="form-control" name="tel" value="${pageContext.request.getAttribute("tel")}">
    </label>
    </div>
    <div class="form-group">
    <label><fmt:message key="userUpdatePage6" bundle="${rb}"/>:
    <input type="text" class="form-control" name="login" value="${pageContext.request.getAttribute("login")}">
    </label>
    </div>
    <div class="form-group">
    <label><fmt:message key="userUpdatePage7" bundle="${rb}"/>:
    <input type="text" class="form-control" name="password" value="${pageContext.request.getAttribute("password")}">
    </label>
    </div>
    <fmt:message key="userUpdatePage8" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" value="${msg}">
</form>
</div>
</body>
</html>
