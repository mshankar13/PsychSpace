<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
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
        <link href='${contextPath}/resources/css/about.css' rel='stylesheet'>
        <link href='${contextPath}/resources/css/welcome.css' rel='stylesheet'>
        <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
        <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
        <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
</head>
<body>

<div class="welcome-background">
    <div class="navbar-wrapper">
        <jsp:include page="navbar.jsp" />
    </div>
    <header class="welcome-header">
        <div class="header-content">
            <div class="header-content-inner">
                <!-- <h1 id="homeHeading">PsychSpace</h1> -->
                <div class="header-content-inner-logos">
                    <div class="logo-img fadein">
                        <img class="welcome-img-logo" src="${contextPath}/resources/img/logo.png" alt="PsychSpace">
                    </div>
                    <div class="logo-img fadein">
                        <img class="welcome-img-text" src="${contextPath}/resources/img/logo_text.png" alt="PsychSpace">
                    </div>
                    <div class="logo-img fadein">
                        <p>Develop healthy habits and achieve personal goals through self-discipline</p>
                    </div>
                </div>

                <div class="header-content-inner-buttons">
                    <!--
                    <a class="button fadein" id="button-register"
                       href=""><span>Register</span></a>
                    <a class="button fadein" id="button-login" href=""}/login"><span>Login</span></a>
                    <br>
                    -->
                    <a class="button anchor-link fadein" id="button-about" href="#about-psychspace"><span>More About PsychSpace</span></a>
                </div>

            </div>
        </div>
    </header>
    <%@include file="about.html" %>
    <%@include file="footer.html" %>
</div>
<script src="${contextPath}/resources/js/animations.js"></script>
</body>

</html>