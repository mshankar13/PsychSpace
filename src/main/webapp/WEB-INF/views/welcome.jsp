<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Maven Pro' rel='stylesheet'>
    <link href='${contextPath}/resources/css/welcome.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/about.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
    <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
    <script src="${contextPath}/resources/js/navbar.js"></script>
</head>
<body>
        <div class="welcome-nav">
            <%@include  file="navbar.html" %>
        </div>
        <header class="welcome-header">
            <div class="header-content">
                <div class="header-content-inner">
                    <!-- <h1 id="homeHeading">PsychSpace</h1> -->
                    <div class="header-content-inner-logos">
                        <div class="logo-img">
                            <img class="welcome-img-logo" src="${contextPath}/resources/img/logo.png" alt="PsychSpace">
                        </div>
                        <div class="logo-text-img">
                            <img class="welcome-img-text" src="${contextPath}/resources/img/logo_text.png" alt="PsychSpace">
                        </div>
                        <p>Develop healthy habits and achieve personal goals through self-discipline</p>
                    </div>
                    <!--
                    <div class="header-content-inner-buttons">
                        <a class="button" id="button-about" href="${contextPath}/about"><span>About</span></a>
                        <a class="button" id="button-enter" href="${contextPath}/home"><span>Enter Site</span></a>
                    </div>
                    -->
                </div>
            </div>
        </div>
    </header>
        <%@include  file="about.html" %>
        <%@include  file="footer.html" %>
</body>

</html>