<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

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
                    <h1 class="absolute-text">Learn</h1>
                </div>
                <!-- Current Courses -->
                <div class="ps-feature-content">
                    <div class="row ps-feature">
                        <div class="ps-feature-info">
                            <h1>My Current Courses<hr></h1>
                            <br>
                        </div>
                        <c:set var="myCurrCourses" value="${myCurrCourses}" />
                        <c:choose>
                            <c:when test="${empty myCurrCourses}" >
                                <p class="ps-notice">You don't have any current course yet.
                                    Go to <a href="${contextPath}/catalogue">Catalogue</a> to enroll.
                                </p>
                            </c:when>
                            <c:otherwise>
                                <div class="row ps-text-content">
                                    <c:forEach items="${myCurrCourses}" var="course">
                                        <!-- Start Course -->
                                        <div class="col-lg-4">
                                            <div class="ps-well">
                                                <!-- Course Title -->
                                                <h2 class="ps-feature-info-header"> ${course.title}<hr> </h2>
                                                <img class="img-responsive course-img" src="http://placehold.it/900x300" alt="">
                                                <h3 class="ps-feature-info-header"> Course Description <hr></h3>
                                                <!-- Course Description-->
                                                <p class="ps-feature-preview">${course.description}</p>
                                                <div>
                                                    <div class="center">
                                                        <!-- Button for Course Learn -->
                                                        <a class="button fadein btn-read-more" id="button-learn" href="/learn/${course.courseKey}"><span>Learn</span></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End course -->
                                    </c:forEach>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
                <!-- End Current Courses -->
                <!-- Past Courses -->
                <div class="ps-feature-content">
                    <div class="row ps-feature">
                        <div class="ps-feature-info">
                            <h1> My Past Courses<hr></h1>
                            <br>
                        </div>
                        <c:set var="myPastCourses" value="${myPastCourses}" />
                        <c:choose>
                            <c:when test="${empty myPastCourses}">
                                <p class="ps-notice">You don't have any past course yet.</p>
                            </c:when>
                            <c:otherwise>
                                <div class="row ps-text-content">
                                    <c:forEach items="${myPastCourses}" var="course">
                                        <!-- Start Course -->
                                        <div class="col-lg-4">
                                            <div class="ps-well">
                                                <!-- Course Title -->
                                                <h2 class="ps-feature-info-header"> ${course.title}<hr> </h2>
                                                <img class="img-responsive course-img" src="http://placehold.it/900x300" alt="">
                                                <h3 class="ps-feature-info-header"> Course Description <hr></h3>
                                                <!-- Course Description -->
                                                <p class="ps-feature-preview">${course.description}</p>
                                                <div>
                                                    <div class="center">
                                                        <!-- Button for Course Learn -->
                                                        <a class="button fadein btn-read-more" id="button-learn" href="/learn/${course.courseKey}"><span>Learn</span></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End course -->
                                    </c:forEach>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
                <!-- End Past Courses -->
            </header>

            <%@include file="footer.html" %>
            <script src="${contextPath}/resources/js/scrollreveal.js"></script>
            <script src="${contextPath}/resources/js/navbar.js"></script>
            <script src="${contextPath}/resources/js/survey.js"></script>
            <script src="${contextPath}/resources/js/animations.js"></script>
        </body>

        </html>