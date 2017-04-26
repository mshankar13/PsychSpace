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
            <link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet" />
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
                    <h1 class="absolute-text">Course Catalogue</h1>
                    <form class="ps-search" action="catalogue/Search" method="get">
                        <input type="text" name="search" placeholder="Search...">
                        <input type="submit" value="Search Course">
                    </form>
                </div>
                <div class="ps-feature-content">
                    <div class="row ps-feature">
                        <div class="ps-feature-info">
                            <h1>Open Courses<hr></h1>
                            <br>
                        </div>

                        <div class="row ps-text-content">
                            <c:forEach items="${openCourses}" var="course">
                            <!-- start course -->
                            <div class="col-lg-4 fadein">
                                <div class="ps-well">
                                    <!-- Course Title -->
                                    <h2 class="ps-feature-info-header"> ${course.title}<hr> </h2>
                                    <br>
                                    <!-- Course Image -->
                                    <img class="img-responsive course-img" src="http://placehold.it/900x300" alt="">
                                    <br>
                                    <!-- Status -->
                                    <h3 class="ps-feature-info-header"> Status<hr> </h3>
                                    <!-- Start Date -->
                                    <p>
                                        <span class="glyphicon glyphicon-time"></span> ${course.status}
                                    </p>
                                    <br>
                                    <h3 class="ps-feature-info-header"> Dates<hr> </h3>
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
                                    <!-- Description -->
                                    <h3 class="ps-feature-info-header"> Description <hr></h3>
                                    <p class="ps-feature-preview">
                                        ${course.description}
                                    </p>
                                    <br>
                                    <div>
                                        <div class="center">
                                            <!-- Button Read More -->
                                            <a class="button fadein btn-read-more" href="/course/${course.courseKey}"><span>Read More</span></a>
                                            <!-- Button Enroll -->
                                            <%--<a class="button fadein btn-read-more" href=""><span>Enroll</span></a>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End course -->
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="ps-feature-content">
                    <div class="row ps-feature">
                        <div class="ps-feature-info">
                            <!-- Title -->
                            <h1> All Courses<hr></h1>
                            <br>
                        </div>
                        <div class="row ps-text-content">
                            <c:forEach items="${courseList}" var="course">
                            <!-- start course -->
                            <div class="col-lg-4">
                                <div class="ps-well">
                                    <!-- Course Title -->
                                    <h2 class="ps-feature-info-header"> ${course.title}<hr> </h2>
                                    <br>
                                    <!-- Course Image -->
                                    <img class="img-responsive course-img fadein" src="http://placehold.it/900x300" alt="">
                                    <br>
                                    <h3 class="ps-feature-info-header fadein"> Dates<hr> </h3>
                                    <!-- Start Date -->
                                    <p>
                                        <span class="glyphicon glyphicon-time"></span> Start Date: ${course.startDate}
                                    </p>
                                    <!-- End Date -->
                                    <p>
                                        <span class="glyphicon glyphicon-time"></span> End Date: ${course.endDate}
                                    </p>
                                    <br>
                                    <!-- Topics -->
                                    <h3 class="ps-feature-info-header"> Topics <hr></h3>
                                    <!-- Course Preview -->
                                    <p class="ps-feature-preview">
                                        ${course.description}
                                    </p>
                                    <br>
                                    <div>
                                        <div class="center">
                                            <!-- Button Read More -->
                                            <a class="button fadein btn-read-more" href="/course/${course.courseKey}"><span>Read More</span></a>
                                            <!-- Button Enroll -->
                                            <%--<a class="button fadein btn-read-more" href="/course/${course.courseKey}"><span>Enroll</span></a>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End course -->
                            </c:forEach>
                        </div>
                    </div>

                </div>


            <%@include file="footer.html" %>
                <script src="${contextPath}/resources/js/animations.js"></script>
        </body>

        </html>