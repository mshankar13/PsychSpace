<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
    <%--<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href='http://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Maven Pro' rel='stylesheet'>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-aria.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.js"></script>
    <%--<script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>--%>
    <script src="${contextPath}/resources/js/navbar.js"></script>
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
</head>
<body>
    <%@include  file="navbar.html" %>
    <div layout="row">
        <div flex>First item in row</div>
        <div flex>Second item in row</div>
    </div>
    <div layout="column">
        <div flex>First item in column</div>
        <div flex>Second item in column</div>
    </div>



    <div layout="row" layout-margin>
        <div flex>
            <h3>Title 1</h3>
            <div>
                <p>date</p>
            </div>
            <div>
                <p>paragraph</p>
                <span>Read more</span>
            </div>
        </div>
    </div>
    <div layout="row" layout-margin>
        <div flex>
            <h3>Title 2</h3>
            <div>
                <p>date</p>
            </div>
            <div>
                <p>paragraph</p>
                <span>Read more</span>
            </div>
        </div>
        <div flex>Second item in row</div>
        <div flex>Third item in row</div>
    </div>
    <div layout="row" layout-margin>
        <div flex>
            <h3>Title 3</h3>
            <div>
                <p>date</p>
            </div>
            <div>
                <p>paragraph</p>
                <span>Read more</span>
            </div>
        </div>
        <div flex>Second item in row</div>
        <div flex>Third item in row</div>
    </div>
</body>
</html>
