<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <title>PsychSpace</title>
    <%--Google sign in--%>
    <meta name="google-signin-client_id" content="268071146674-5jjt494svk74rt4jb5mu4pik8503qbph.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <%--Google fonts--%>
    <link href='http://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Maven Pro' rel='stylesheet'>
    <%--jQuery--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <%--Bootstrap--%>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet"/>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <%--Customized--%>
    <script src="${contextPath}/resources/js/scrollreveal.js"></script>
    <script src="${contextPath}/resources/js/navbar.js"></script>
    <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/ps-row-col.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
</head>
<body>
<div class="navbar-wrapper">
    <%@include file="navbar.html"%>
</div>
<header class="ps-feature-header">
    <div class="center page-banner">
        <img class="img-responsive" src="http://placehold.it/2000x500" alt="">
        <h1 class="absolute-text">Home</h1>
    </div>
    <div class="row ps-feature-content">
        <div class="col-lg-8 ps-col-left">
            <div class="row ps-feature">
                <div class="ps-feature-info">
                    <!-- Title -->
                    <h1>My Current Courses<hr></h1>
                    <br>
                </div>
                <!-- Start Course -->
                <div class="row ps-text-content">
                    <c:forEach items="${myCurrCourses}" var="course">
                    <!-- start course -->
                    <div class="col-lg-4">
                        <div class="ps-well">
                            <h2 class="ps-feature-info-header"> ${course.title} <hr> </h2>
                            <img class="img-responsive course-img" src="http://placehold.it/900x300" alt="">
                            <br>
                            <!-- Description -->
                            <h3 class="ps-feature-info-header"> Description <hr></h3>
                            <p class="ps-feature-preview">
                                 ${course.description}</p>
                            <div>
                                <div class="center">
                                    <a class="button fadein btn-read-more" id="button-learn" href="/learn/${course.courseKey}"><span>Learn</span></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                    <!-- End course -->
                </div>
            </div>
            <br>
            <div class="row ps-feature">
                <div class="ps-feature-info">
                    <!-- Title -->
                    <h1> Popular Courses<hr></h1>
                    <br>
                </div>

                <div class="row ps-text-content">
                    <c:forEach items="${popularCourses}" var="course">
                    <!-- start course -->
                    <div class="col-lg-4">
                        <div class="ps-well">
                            <h2 class="ps-feature-info-header"> ${course.title}<hr> </h2>
                            <img class="img-responsive course-img" src="http://placehold.it/900x300" alt="">
                            <br>
                            <h3 class="ps-feature-info-header"> Dates<hr> </h3>
                            <p>
                                <span class="glyphicon glyphicon-time"></span> Start Date: ${course.startDate}</p>
                            <p>
                                <span class="glyphicon glyphicon-time"></span> End Date: ${course.endDate}</p>
                            <br>
                            <!-- Description -->
                            <h3 class="ps-feature-info-header"> Description <hr></h3>
                            <p class="ps-feature-preview">
                                ${course.description} </p>
                            <br>

                            <div>
                                <div class="center">
                                    <a class="button fadein btn-read-more" id="button-read-more" href="/course/${course.courseKey}"><span>Read More</span></a>
                                </div>
                            </div>
                        </div>
                    </div>
                        <!-- End course -->
                    </c:forEach>
                </div>

            </div>
        </div>
        <!-- end column -->
        <div class="col-lg-4 ps-col-right">

            <div class="left">
                <h1>Recommended Articles<hr></h1>
            </div>
            <div class="ps-well">
                <div class="left">
                    <h2 class="ps-feature-info-header"> ${featuredNews.title} <hr></h2>
                    <div class="center">
                        <img class="ps-feature-img img-responsive" src="http://placehold.it/900x300" alt="">
                        <a class="button fadein btn-read-more" id="button-read" href="/article/${featuredNews.newsKey}"><span>Read More</span></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/animations.js"></script>
</body>
</html>

