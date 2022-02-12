<%@ page contentType="text/html;charset=UTF-8" %>
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
            background-image: url("https://www.garageliving.com/hs-fs/hubfs/Imported_Blog_Media/bright-clean-garage-01-1.jpg?width=970&height=645&name=bright-clean-garage-01-1.jpg");
            background-size: cover;
            color: azure;
        }
    </style>
</head>
<body>
<div class="container">
<form role="form" action="${pageContext.request.contextPath}/control" method="post">
    <input type="hidden" name="login" value="${pageContext.request.getParameter("login")}"/>
    <input type="hidden" name="path" value="/welcomePage.jsp"/>
    <input type="hidden" name="command" value="ADD_CAR"/>
<div>
    <label><fmt:message key="addCarPage2" bundle="${rb}"/>:
        <input type="text"  class="form-control" name="model" value=""/>
    </label>
</div>
    <div>
    <label><fmt:message key="addCarPage3" bundle="${rb}"/>:
        <input type="text" class="form-control" name="plate" value=""/>
    </label>
    </div>
    <div>
    <label><fmt:message key="addCarPage4" bundle="${rb}"/>:
        <input type="text" class="form-control" name="year" value=""/>
    </label>
    </div>
    <small>${unCorrectYear}</small>
    <div>
    <label><fmt:message key="addCarPage5" bundle="${rb}"/>:
        <input type="text" class="form-control" name="mileage" value=""/>
    </label>
    </div>
    <small>${nullData}</small>
       <fmt:message key="addCarPage1" var="msg" bundle="${rb}"/>
    <input type="submit" class="btn btn-success" value="${msg}"/>
</form>
</div>

</body>
</html>
