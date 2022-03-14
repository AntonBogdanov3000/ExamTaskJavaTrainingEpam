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
    <h2>Order history of client: ${pageContext.request.getAttribute('pageName')}</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Date</th>
            <th>Price</th>
            <th>Operations</th>
            <th>Manager</th>
        </tr>
        </thead>
        <tbody>
    <c:forEach var="order" items="${orderList}">
        <tr>
            <td>${order.getId()}</td>
            <td>${order.getDate()}</td>
            <td>${order.getPrice()}</td>
            <td>${order.getOperationList()}</td>
            <td>${order.getManagerId()}</td>
            <td>
            <form action="${pageContext.request.contextPath}/control">
                <input type="hidden" name="path" value="/OrderToUpdatePage.jsp"/>
                <input type="hidden" name="command" value="SHOW_PRICES"/>
                <input type="hidden" name="updOrder" value="${order.getId()}"/>
                <fmt:message key="ordersToUpdatePage1" var="msg" bundle="${rb}"/>
                <input type="submit" class="btn btn-info" value="${msg}"/>
            </form>
            </td>
            </tr>
    </c:forEach>
        </tbody>
    </table>
    <form>
        <input type="hidden" name="path" value="ManagerPage.jsp"/>
        <input type="hidden" name="command" value=""/>
        <fmt:message key="ordersToUpdatePage2" var="msg" bundle="${rb}"/>
        <input type="submit" class="btn btn-success" value="${msg}"/>
    </form>
    </div>

</body>
</html>
