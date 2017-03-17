<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" >
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Maven Pro' rel='stylesheet'>
    <link href='${pageContext.request.contextPath}/resources/css/navbar.css' rel='stylesheet'>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
    <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
    <!--<script src="../resources/js/ui-bootstrap-tpls-2.5.0.min.js"></script>-->
    <script src="${pageContext.request.contextPath}/resources/js/navbar.js"></script>
</head>
<body ng-app="psychSpaceHome" ng-cloak>
    <%@include  file="navbar.html" %>
    <%@include  file="footer.html" %>
</body>
</html>
