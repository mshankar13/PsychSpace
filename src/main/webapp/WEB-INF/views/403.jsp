<%--
  Created by IntelliJ IDEA.
  User: acerini
  Date: 3/24/2017
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setStatus(404); %>
<html lang="en">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Maven Pro' rel='stylesheet'>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-aria.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.js"></script>
    <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
    <script src="${contextPath}/resources/js/scrollreveal.js"></script>
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/404.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
</head>
</head>
<body>
<div class="error-background">
    <header class="error-header">
        <div class="error-header-content">
            <div class="error-header-content-inner">
                <div class="error-header-content-inner-logos">
                    <div class="error-logo fadein-once">
                        <img class="error-img-logo" src="${contextPath}/resources/img/logo.png" alt="PsychSpace">
                    </div>
                    <div class="error-logo fadein-once">
                        <img class="error-img-text" src="${contextPath}/resources/img/logo_text.png" alt="PsychSpace">
                    </div>
                    <h1 class="fadein-once">403 (Forbidden): Access Denied.</h1>
                </div>
            </div>
            <div class="error-header-content-inner-buttons fadein-once">
                <a class="button" id="button-enter" href="${contextPath}/"><span>Home</span></a>
            </div>
        </div>
    </header>
    <script src="${contextPath}/resources/js/animations.js"></script>
</div>
</div>
</body>
</html>