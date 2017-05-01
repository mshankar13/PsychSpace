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
                    <h1 class="absolute-text">News</h1>
                    <form class="ps-search">
                        <input type="text" name="search" placeholder="Search...">
                        <input type="submit" value="Search Article">
                    </form>
                </div>
                </div>
                <div class="ps-feature-content">
                    <div class="row ps-feature">
                        <div class="ps-feature-info">
                            <!-- Title -->
                            <h1>Search Results<hr></h1>
                            <br>
                        </div>

                        <div class="row ps-text-content">
                            <!-- start course -->
                            <c:forEach items="${articleList}" var="newsItem">
                                <div class="col-lg-4">
                                    <div class="ps-well">
                                        <p><span class="glyphicon glyphicon-time"></span> Posted ${newsItem.date}</p>
                                        <h2 class="ps-feature-info-header"> ${newsItem.title}<hr> </h2>
                                        <br>
                                        <img class="img-responsive course-img fadein" src="http://placehold.it/900x300" alt="">
                                        <br>
                                        <p> by <a class="button fadein" href=""><span> ${newsItem.author}</span></a> </p>
                                        <br>
                                        <p class="fadein"> ${newsItem.content} </p>
                                        <div class="center">
                                            <a class="button fadein btn-read-more" href="/article/${newsItem.newsKey}"><span>Read More</span></a>
                                        </div>
                                    </div>
                                    <br>
                                </div>
                            </c:forEach>
                            <!-- End course -->
                        </div>
                    </div>
                </div>
            </header>

            <%@include file="footer.html" %>
                <%--<script src="${contextPath}/resources/js/style.js"></script>--%>
            <script src="${contextPath}/resources/js/animations.js"></script>


        </body>

        </html>