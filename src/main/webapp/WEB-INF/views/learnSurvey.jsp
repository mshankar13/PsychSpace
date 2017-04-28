<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <script src="${contextPath}/resources/js/survey.js"></script>
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
                <!-- Course Title -->
                <h1>Course Title
                    <hr>
                </h1>
                <br>
            </div>
        </div>
        <div class="row ps-text-content">
            <div class="col-lg-2 ps-col-left">
                <div class="ps-well">
                    <!-- Learn Sidebar -->
                    <%@include file="learn-sidebar.html" %>
                </div>
            </div>
            <div class="col-lg-10 ps-col-right">
                <!-- Start Content -->
                <div class="col-lg">
                    <div class="ps-well">
                        <!-- Survey Name -->
                        <h2>Survey Name
                            <hr>
                        </h2>
                        <br>
                        <br>
                        <!-- Form Start -->
                        <form class="ps-all-questions">
                            <!-- Question Start -->
                            <div class="ps-question">
                                <!-- Question Text -->
                                <h3>Question Text</h3>
                                <div class="ps-answers">
                                    <!-- Answer Start -->
                                    <div class="ps-answer">
                                        <!-- Answer Button -->
                                        <!-- Set "id" equal to answer ID -->
                                        <!-- Set "name" equal to question ID -->
                                        <input id="radio-1" type="radio" name="r-group-1"/>
                                        <!-- Answer Text -->
                                        <!-- Set "for" equal to answer ID -->
                                        <label for="radio-1">Answer</label>
                                    </div>
                                    <!-- Answer End-->
                                </div>
                            </div>
                            <!-- Question End -->
                            <div class="center">
                                <!-- Submit Survey Button -->
                                <button type="button" class="button" id="btn-submit-survey" disabled>
                                    <span>Submit Survey</span></button>
                            </div>
                        </form>
                        <!-- Form End -->
                    </div>
                </div>
                <!-- End Content -->
            </div>
        </div>
    </div>
</header>


<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/animations.js"></script>
</body>

</html>