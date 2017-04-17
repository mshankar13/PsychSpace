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
                                <script src="${contextPath}/resources/js/style.js"></script>
                                <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
                                <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
                                <link href='${contextPath}/resources/css/ps-row-col.css' rel='stylesheet'>
                                <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
        </head>

        <body>
            <div class="navbar-wrapper">
                <%@include file="navbar.html"%>
            </div>
            <%@include file="catalogue.html"%>
                <%@include file="footer.html" %>
                    <script src="${contextPath}/resources/js/animations.js"></script>


        </body>

        </html>