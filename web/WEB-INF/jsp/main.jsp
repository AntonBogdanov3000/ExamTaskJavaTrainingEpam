<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="lang" value="${pageContext.session.getAttribute('locale')}"/>

<fmt:setLocale value="en_EN"/>

<c:if test="${lang != null}">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
</c:if>
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
            background-image: url("https://wallpapercave.com/wp/wp5553648.jpg");
            background-repeat: no-repeat;
            background-size: cover;
        }
        .navbar-brand{
            font-family: "Calibri Light";
            font-size: x-large;
            color: white;

        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">
                <fmt:message key="mainPage4" bundle="${rb}"/>
            </a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active">
                    <form action="${pageContext.request.contextPath}/control" method="post">
                        <input type="hidden" name="path" value="registration.jsp"/>
                        <input type="hidden" name="command" value=""/>
                        <input type="hidden" name="number" value="1"/>
                        <input type="hidden" name="role" value="1"/>
                        <fmt:message key="mainPage1" var="msg" bundle="${rb}"/>
                        <button type="submit" class="btn btn-success navbar-btn">${msg}
                        </button>
                    </form>
                </li>
                <li class="active">
                <form action="${pageContext.request.contextPath}/control" method="get">
                    <input type="hidden" name="path" value="PriceListPage.jsp"/>
                    <input type="hidden" name="command" value="SHOW_PRICES"/>
                    <fmt:message key="mainPage2" var="msg" bundle="${rb}"/>
                    <button type="submit" class="btn btn-success navbar-btn">
                        <i class="glyphicon glyphicon-usd"></i>${msg}
                    </button>
                </form>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <span class="navbar-btn glyphicon glyphicon-globe" style="color: white; font-size: 17px"></span>
                </li>
                <li>
                <form action="${pageContext.request.contextPath}/control" id="lang" method="get">
                    <input type="hidden" name="command" value="CHANGE_LANG"/>
                    <input type="hidden" name="path" value="main.jsp"/>
                    <select class="btn dropdown navbar-btn " name="language" onchange="document.getElementById('lang').submit()">
                        <option  value=""><fmt:message key="mainPage6" bundle="${rb}"/></option>
                        <option value="english" ${language eq 'english' ? 'selected' : ''}>English</option>
                        <option value="russian" ${language eq 'russian' ? 'selected' : ''}>Русский</option>
                        <option value="deutsch" ${language eq 'deutsch' ? 'selected' : ''}>Deutsch</option>
                    </select>
                </form>
                </li>
                <li class="active">
                <form action="${pageContext.request.contextPath}/control" method="post">
                    <input type="hidden" name="path" value="LogInPage.jsp"/>
                    <input type="hidden" name="command" value=""/>
                    <input type="hidden" name="locale" value="${pageContext.session.getAttribute('locale')}">
                    <fmt:message key="mainPage3" var="msg" bundle="${rb}"/>
                    <button type="submit" class="btn btn-success navbar-btn">
                        <i class="glyphicon glyphicon-log-in"></i>  ${msg}
                    </button>
                </form>
                </li>
                <li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>


