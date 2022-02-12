<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
<fmt:setBundle basename="text" var="rb" scope="session"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
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
            background-image: url("http://i.imgur.com/9bA8cj6.jpg");
            background-size: cover;
        }
    </style>
</head>
<body>
<div class="form">
<form class="form-horizontal" action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="command" value="REGISTRATION"/>
    <input type="hidden" name="role" value="${pageContext.request.getParameter("role")}"/>
    <c:set var="number" value="${pageContext.request.getParameter('number')}"/>
    <c:if test="${number == 1}">
        <input type="hidden" name="path" value="/welcomePage.jsp"/>
    </c:if>
    <c:if test="${number == 3}">
        <input type="hidden" name="path" value="/AdminPage.jsp"/>
    </c:if>
    <c:if test="${number == 2}">
        <input type="hidden" name="path" value="/ManagerPage.jsp"/>
    </c:if>

    <div class="form-group">
        <div class="form-group">

    <div class="form-group">
    <label class="col-sm-4"><fmt:message key="registrationPage1" bundle="${rb}"/>:</label>
        <div class="col-sm-10">
        <input type="text" class="form-control" name="name" value=""/>
        </div>
    </div>

    <div class="form-group">
    <label class="col-sm-10"><fmt:message key="registrationPage2" bundle="${rb}"/>:</label>
        <div class="col-sm-10">
        <input type="text" class="form-control" name="lastname" value=""/>
        </div>
    </div>

    <div class="form-group">
    <label class="col-sm-4"><fmt:message key="registrationPage3" bundle="${rb}"/>:</label>
        <div class="col-sm-10">
        <input type="password" class="form-control" name="password" value=""/>
        </div>
    </div>

    <div class="form-group">
    <label class="col-sm-4"><fmt:message key="registrationPage4" bundle="${rb}"/>:</label>
        <div class="col-sm-10">
        <input type="text" class="form-control" name="login" value=""/>
        </div>
    </div>
            <small>${incorrectLogin}</small>
    <div class="form-group">
    <label class="col-sm-4"><fmt:message key="registrationPage5" bundle="${rb}"/>:</label>
        <div class="col-sm-10">
        <input type="text" class="form-control" name="telephone" value=""/>
        </div>
    </div>
    ${nullDataForUser}
    <p></p>
    <fmt:message key="registrationPage6" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" value="${msg}"/>
</form>
</div>
</body>
</html>