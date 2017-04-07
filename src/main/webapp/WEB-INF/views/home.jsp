<%--
  Created by IntelliJ IDEA.
  User: acerini
  Date: 4/06/2017
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <html lang="en">
            <c:set var="contextPath" value="${pageContext.request.contextPath}" />

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
                <script src="${contextPath}/resources/js/navbar.js"></script>
                <link href='${contextPath}/resources/css/learn.css' rel='stylesheet'>
                <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
                <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
                <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
            </head>

            <body>
                <div classs="navbar-wrapper">
                    <%@include file="navbar.html"%>
                </div>
                <%@include file="home.html"%>
                    <%@include file="footer.html" %>
                        <script src="${contextPath}/resources/js/animations.js"></script>
            </body>

            </html>