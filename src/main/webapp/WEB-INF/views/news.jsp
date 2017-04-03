<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
    <%--<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
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
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/news.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
</head>

<body>
<div classs="navbar-wrapper">
    <%@include file="navbar.html" %>
</div>
<header class="news-header">
    <div ng-controller="ArticleCtrl" class="news-content">
        <!--<div class="page-title fadein"><h1>NEWS</h1><hr></div> -->
        <div class="section-title fadein">
            <h2>Featured Article</h2>
            <hr>
        </div>
        <div class="row articles">
            <div class="row article-info2 fadein">
                <div class="row article-title">
                    <h1>Article Title: Lorem ipsum dolor sit amet, consectetur adipisicing elit </h1></div>
                <div class="col-lg-4">
                    <p> by <a class="button fadein" href="#"><span>Article Author</span></a> </p>
                    <p><span class="glyphicon glyphicon-time"></span> Posted on March 31, 2017 at 12:00 AM</p>

                </div>
                <div class="col-lg-8">
                    <img class="img-responsive" src="http://placehold.it/900x300" alt="">
                    <p class="article-preview">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus, vero, obcaecati, aut, error quam sapiente nemo saepe quibusdam sit excepturi nam quia corporis eligendi eos magni recusandae laborum minus inventore...</p>
                    <div class="center">
                        <a class="button fadein btn-read-more" id="button-register" href=""><span>Read More</span></a>
                    </div>

                </div>
            </div>
        </div>
        <div class="section-title fadein">
            <h2>All Articles</h2>
            <hr>
        </div>
        <div class="row articles">
            <div class="article-info fadein">
                <div class="row article-title">
                    <h1>Article Title: Lorem ipsum dolor sit amet, consectetur adipisicing elit </h1>
                    <p> by <a class="button fadein" href="#"><span>Article Author</span></a> </p>
                    <p><span class="glyphicon glyphicon-time"></span> March 31, 2017 at 12:00 AM</p>
                </div>
                <div class="row">
                    <img class="img-responsive" src="http://placehold.it/900x300" alt="">
                    <p class="article-preview">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus, vero, obcaecati, aut, error quam sapiente nemo saepe quibusdam sit excepturi nam quia corporis eligendi eos magni recusandae laborum minus inventore...</p>
                </div>
                <div class="center">
                    <a class="button fadein btn-read-more" id="button-register" href=""><span>Read More</span></a>
                </div>
            </div>
            <!-- end article-info-->
            <div class="article-info fadein">
                <div class="row article-title">
                    <h1>Article Title</h1>
                    <p> by <a class="button fadein" href="#"><span>Article Author</span></a> </p>
                    <p><span class="glyphicon glyphicon-time"></span> March 31, 2017 at 12:00 AM</p>
                </div>
                <div class="row">
                    <img class="img-responsive" src="http://placehold.it/900x300" alt="">
                    <p class="article-preview">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus, vero, obcaecati, aut, error quam sapiente nemo saepe quibusdam sit excepturi nam quia corporis eligendi eos magni recusandae laborum minus inventore...</p>
                </div>
                <div class="center">
                    <a class="button fadein btn-read-more" id="button-register" href=""><span>Read More</span></a>
                </div>
            </div>
            <!-- end article-info-->
            <div class="article-info fadein">
                <div class="row article-title">
                    <h1>Article Title</h1>
                    <p> by <a class="button fadein" href="#"><span>Article Author</span></a> </p>
                    <p><span class="glyphicon glyphicon-time"></span> March 31, 2017 at 12:00 AM</p>
                </div>
                <div class="row">
                    <img class="img-responsive" src="http://placehold.it/900x300" alt="">
                    <p class="article-preview">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus, vero, obcaecati, aut, error quam sapiente nemo saepe quibusdam sit excepturi nam quia corporis eligendi eos magni recusandae laborum minus inventore..</p>
                </div>
                <div class="center">
                    <a class="button fadein btn-read-more" id="button-register" href=""><span>Read More</span></a>
                </div>
            </div>
            <!-- end article-info-->
        </div>
    </div>
</header>
<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/animations.js"></script>
</body>
</html>
