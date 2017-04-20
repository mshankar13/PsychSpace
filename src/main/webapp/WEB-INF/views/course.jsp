<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<head>
    <title>PsychSpace</title>
    <%--Google sign in--%>
    <meta name="google-signin-client_id"
          content="268071146674-5jjt494svk74rt4jb5mu4pik8503qbph.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <%--Google fonts--%>
    <link href='http://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Maven Pro' rel='stylesheet'>
    <%--jQuery--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <%--Bootstrap--%>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css"
          rel="stylesheet"/>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <%--Customized--%>
    <script src="${contextPath}/resources/js/scrollreveal.js"></script>
    <script src="${contextPath}/resources/js/navbar.js"></script>
    <script src="${contextPath}/resources/js/course.js"></script>
    <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/ps-row-col.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
</head>

<body>
<div classs="navbar-wrapper">
    <%@include file="navbar.html" %>
</div>
<header class="ps-feature-header">
    <div class="ps-feature-content">
        <div class="row ps-feature">
            <div class="ps-feature-info">
                <!-- Title -->
                <h1>${course.title}
                    <hr>
                </h1>
                <br>
            </div>
        </div>

        <!-- Course Info -->
        <div class="row ps-text-content">
            <div class="col-lg-4 ps-col-left">
                <div class="ps-well">
                    <div class="left">
                        <h3 class="ps-feature-info-header"> Status
                            <hr>
                        </h3>
                        <!-- Course Status -->
                        <p>
                            <span class="glyphicon glyphicon-check"></span> Status: ${course.status}
                        </p>
                        <!-- Course Capacity -->
                        <p>
                            <span class="glyphicon glyphicon-check"></span> Capacity: ${course.capacity}
                        </p>
                        <!-- Course Current Size -->
                        <p>
                            <span class="glyphicon glyphicon-check"></span> Current Size: ${course.currSize}
                        </p>
                    </div>
                    <br>
                    <!-- Dates -->
                    <h3 class="ps-feature-info-header"> Dates
                        <hr>
                    </h3>
                    <!-- Start Date -->
                    <p>
                        <span class="glyphicon glyphicon-time"></span> Start Date: ${course.startDate}
                    </p>
                    <!-- End Date -->
                    <p>
                        <span class="glyphicon glyphicon-time"></span> End Date: ${course.endDate}
                    </p>
                    <!-- Enroll Date -->
                    <p>
                        <span class="glyphicon glyphicon-time"></span> Enrollment Ends: ${course.enrollDate}
                    </p>
                    <!-- Drop Date -->
                    <p>
                        <span class="glyphicon glyphicon-time"></span> Last Day to Drop: ${course.dropDate}
                    </p>
                    <br>
                    <%--<h3 class="ps-feature-info-header"> Topic <hr> </h3>--%>
                    <%--<p class="ps-feature-preview">--%>
                    <%--Lorem ipsum dolor sit amet, consectetur adipisicing elit. </p>--%>
                    <%--<br>--%>
                    <!-- Author -->
                    <h3 class="ps-feature-info-header"> Instructor
                        <hr>
                    </h3>
                    <div class="media instructor-feature">
                        <a class="pull-left" href="#"> <img class="media-object" src="http://placehold.it/64x64" alt="">
                        </a>
                        <div class="media-body">
                            <!-- Course Instructor -->
                            <a class="button-instructor button fadein" href=""
                               id="button-instructor"><span>${course.instructor}</span></a>
                            <%--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.</p>--%>
                        </div>
                    </div>
                    <br>
                    <div>
                        <!-- Enroll Button -->
                        <div class="center">
                            <input id="button-user-enroll-status" value=${isEnrolled} hidden>
                            <input id="button-enroll-status" value=${course.status} hidden>
                            <a class="button-enroll button" id="button-enroll" href="/course/${course.courseKey}/enroll"><span> Enroll Now</span></a>
                            <a class="button-enroll button" id="button-unenroll" href="/course/${course.courseKey}/unenroll"><span> Unenroll</span></a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Course Info -->
            <!-- Course Description -->
            <div class="col-lg-8 ps-col-right">
                <div class="ps-well">
                    <div class="center">
                        <!-- Course Image -->
                        <img class="ps-feature-img img-responsive" src="http://placehold.it/900x300" alt="">
                    </div>
                    <h3 class="ps-feature-info-header"> Course Description
                        <hr>
                    </h3>
                    <!-- Post Content / Course Description Text --->
                    <p>${course.description}</p>
                </div>
            </div>
            <!-- End Course Description-->
        </div>
        <!-- end article-content -->
        <!-- end row artcile -->
        <%--<div class="row ps-text-content">--%>
        <%--<div class="left">--%>
        <%--<h2>Reviews<hr></h2>--%>
        <%--</div>--%>
        <%--<!-- Comment -->--%>
        <%--<div class="media ps-comment">--%>
        <%--<a class="pull-left" href="#"> <img class="media-object" src="http://placehold.it/64x64" alt=""> </a>--%>
        <%--<div class="media-body">--%>
        <%--<h4>Jane Doe <small>March 31, 2017</small><hr></h4>--%>
        <%--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>--%>
        <%--<div class="right">--%>
        <%--<button id="btn-comment-edit" type="submit" class="btn-comment btn btn-primary">Edit</button>--%>
        <%--<button id="btn-comment-like" type="submit" class="btn-comment btn btn-primary">Like</button>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
        <!-- Social -->
    </div>
    <!-- article-content-->
</header>

<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/animations.js"></script>
</body>

</html>