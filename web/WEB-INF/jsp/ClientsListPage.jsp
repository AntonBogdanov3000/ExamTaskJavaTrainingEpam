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
    <fmt:message key="clientListPage3" var="title_msg" bundle="${rb}"/>
    <title>${title_msg}</title>
</head>
<body>
<div class="container">
<h2><fmt:message key="clientListPage4" bundle="${rb}"/></h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Lastname</th>
            <th>Login</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
    <c:forEach var="client" items="${clientList}" >
       <tr>
           <td>${client.getName()}</td>
           <td>${client.getLastName()}</td>
           <td>${client.getLogin()}</td>
           <td>${client.getRole()}</td>
           <td>
        <form>
            <input type="hidden" name="path" value="AdminPage.jsp">
            <input type="hidden" name="command" value="DELETE_USER">
            <input type="hidden" name="delUser" value="${client.getId()}"/>
            <fmt:message key="clientListPage1" var="msg" bundle="${rb}"/>
            <input type="submit" class="btn btn-danger" value="${msg}"/>
        </form>
           </td>
           <td>
        <form>
            <input type="hidden" name="path" value="UserUpdatePage.jsp">
            <input type="hidden" name="command" value="UPDATE_USER"/>
            <input type="hidden" name="upd" value="upd"/>
            <input type="hidden" name="updUser" value="${client.getId()}"/>
            <fmt:message key="clientListPage2" var="msg" bundle="${rb}"/>
            <input type="submit" class="btn btn-warning" value="${msg}"/>
        </form>
           </td>
       </tr>
    </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

