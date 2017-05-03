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
    <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/ps-row-col.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
</head>

<body>
<div class="navbar-wrapper">
    <%@include file="navbar.html" %>
</div>

<header class="ps-feature-header">
    <div class="center page-banner">
        <img class="img-responsive" src="http://placehold.it/2000x500" alt="">
        <!-- Course Title -->
        <h1 class="absolute-text">[Course Title]</h1>
    </div>
    <div class="ps-feature-content">
        <div class="row ps-feature">
            <div class="row ps-text-content">
                <div class="col-lg-2 ps-col-left">
                    <div class="ps-well">
                        <!-- Learn Sidebar -->
                        <%@include file="learn-sidebar.html" %>
                    </div>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <!-- Learn Navbar -->
                    <ul class="nav nav-tabs">
                        <li ><a href="${contextPath}/learn/${courseKey}">Home</a></li>
                        <li class="active"><a href="${contextPath}/learn/${courseKey}/goal">Build My Habit</a></li>
                        <li><a href="${contextPath}/learn/${courseKey}/cues">Cues</a></li>
                        <li><a href="${contextPath}/learn/${courseKey}/videos">Videos</a></li>
                        <li><a href="${contextPath}/learn/${courseKey}/forum">Forum</a></li>
                    </ul>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <!-- Content -->
                    <div class="col-lg ps-col-right">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="ps-well">
                                    <h1>My Goal<hr></h1>
                                    <input type="hidden" value="${goal}" id="goal">
                                    <form:form class="form-horizontal"  method="post"
                                               modelAttribute="goal" action="/learn/${courseKey}/goal/submit">
                                        <div class="form-group">
                                            <span class="col-md-4">By the end of the course, I want to</span>
                                            <form:input class="col-ma-2" type="text" path="goalName" placeholder="Action" id="action" />
                                            <form:input class="col-md-2" type="number" path="value" placeholder="Value" id="value"/>
                                            <form:input class="col-md-2" type="text" path="unit" placeholder="Unit" id="unit"/>
                                            <span class="col-md-2">per day.</span>
                                            <form:hidden path="goalKey" value=""/>
                                            <form:hidden path="username" value=""/>
                                            <form:hidden path="userKey" value=""/>
                                            <form:hidden path="courseKey" value="${courseKey}"/>
                                        </div>
                                        <div class="right">
                                            <button type="submit" class="btn-primary">Save</button>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="ps-well">
                                    <h1>Cue:<hr></h1>
                                    <h2>[Go to Library] </h2>
                                    <div class="center">
                                        <button type="button" class="btn-comment btn btn-primary btn-comment-edit">Set Cue</button>
                                        <button type="button" class="btn-comment btn btn-primary btn-comment-edit">Change Cue</button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="ps-well">
                                    <h1>Routine:<hr></h1>
                                    <h2>[Study 3 hours per day]</h2>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="ps-well">
                                    <h1>Reward:<hr></h1>
                                    <h2>[Candy from the Trolley] </h2>
                                    <div class="center">
                                        <button type="button" class="btn-comment btn btn-primary btn-comment-edit">Set Reward</button>
                                        <button type="button" class="btn-comment btn btn-primary btn-comment-edit">Change Reward</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End course -->
        </div>
    </div>
</header>

<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/learn.js"></script>
<script src="${contextPath}/resources/js/scrollreveal.js"></script>
<script src="${contextPath}/resources/js/animations.js"></script>

</body>
</html>