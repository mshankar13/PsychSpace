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
                </div>
                <div class="ps-feature-content">
                    <div class="row ps-feature">
                        <div class="ps-feature-info">
                            <!-- Title -->
                            <h1>Featured Article<hr></h1>
                            <br>
                        </div>
                        <div class="ps-well">
                            <p><span class="glyphicon glyphicon-time"></span> Posted on March 31, 2017 at 12:00 AM</p>

                            <h1>Article Title: Lorem ipsum dolor sit amet, consectetur adipisicing elit<hr></h1>
                            <br>

                            <div class="col-lg-8 ps-col-left">
                                <img class="img-responsive" src="http://placehold.it/900x300" alt="">
                                <br>
                                <p> by <a class="button fadein" href="#"><span>Article Author</span></a> </p>

                            </div>
                            <div class="col-lg-4 ps-col-right">
                                <div class="media ps-comment">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus, vero, obcaecati, aut, error quam sapiente nemo saepe quibusdam sit excepturi nam quia corporis eligendi eos magni recusandae laborum minus inventore...</p>


                                    <div class="center">
                                        <a class="button fadein btn-read-more" id="button-register" href=""><span>Read More</span></a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                </div>
                <div class="ps-feature-content">
                    <div class="row ps-feature">
                        <div class="ps-feature-info">
                            <!-- Title -->
                            <h1>All Articles<hr></h1>
                            <br>
                        </div>

                        <div class="row ps-text-content">
                            <!-- start course -->
                            <c:forEach items="${newsList}" var="news">
                            <div class="col-lg-4">
                                <div class="ps-well">
                                    <p><span class="glyphicon glyphicon-time"></span> Posted ${news.date}</p>
                                    <h2 class="ps-feature-info-header"> ${news.title}<hr> </h2>
                                    <img class="img-responsive course-img" src="http://placehold.it/900x300" alt="">
                                    <br>
                                    <p> by <a class="button fadein" href="#"><span> ${news.author}</span></a> </p>
                                    <p> ${news.content} </p>
                                    <div class="center">
                                        <a class="button fadein btn-read-more" id="button-register" href=""><span>Read More</span></a>
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                            <!-- End course -->
                        </div>
                    </div>
                </div>
            </header>

                <%@include file="footer.html" %>
                    <script src="${contextPath}/resources/js/animations.js"></script>
        </body>

        </html>