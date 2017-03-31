<%--
  Created by IntelliJ IDEA.
  User: acerini
  Date: 3/27/2017
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <html lang="en">
            <c:set var="contextPath" value="${pageContext.request.contextPath}" />

            <head>
                <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
                <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
                <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
                <link href='http://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
                <link href='http://fonts.googleapis.com/css?family=Maven Pro' rel='stylesheet'>
                <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
                <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
                <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-aria.min.js"></script>
                <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
                <script src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0/angular-material.min.js"></script>
                <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
                <script src="${contextPath}/resources/js/scrollreveal.js"></script>
                <script src="${contextPath}/resources/js/navbar.js"></script>
                <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
                <link href='${contextPath}/resources/css/article.css' rel='stylesheet'>
                <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
                <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'> </head>

            <body ng-app="newsdetApp">
                <div classs="navbar-wrapper">
                    <%@include file="navbar.html" %>
                </div>
                <header class="nd-header">
                    <div ng-controller="ArticleCtrl" class="nd-content">
                        <div class="row article">
                            <div class="article-info">
                                <!-- Title -->
                                <h1>Article Title</h1>
                                <!-- Author -->
                                <p class="lead"> by <a href="#">Article Author</a> </p>
                                <hr>
                                <!-- Date/Time -->
                                <p><span class="glyphicon glyphicon-time"></span> Posted on March 31, 2017 at 12:00 AM</p>
                                <hr>
                                <!-- Preview Image -->
                                <!-- <img class="img-responsive" src="http://placehold.it/900x300" alt="">--></div>
                            <!-- end article info -->
                            <div class="article-content">
                                <div class="col-lg-9 article-text">
                                    <!-- Post Content -->
                                    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus, vero, obcaecati, aut, error quam sapiente nemo saepe quibusdam sit excepturi nam quia corporis eligendi eos magni recusandae laborum minus inventore?</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ut, tenetur natus doloremque laborum quos iste ipsum rerum obcaecati impedit odit illo dolorum ab tempora nihil dicta earum fugiat. Temporibus, voluptatibus.</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos, doloribus, dolorem iusto blanditiis unde eius illum consequuntur neque dicta incidunt ullam ea hic porro optio ratione repellat perspiciatis. Enim, iure!</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error, nostrum, aliquid, animi, ut quas placeat totam sunt tempora commodi nihil ullam alias modi dicta saepe minima ab quo voluptatem obcaecati?</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Harum, dolor quis. Sunt, ut, explicabo, aliquam tenetur ratione tempore quidem voluptates cupiditate voluptas illo saepe quaerat numquam recusandae? Qui, necessitatibus, est!</p>
                                </div>
                                <!--end article-text -->
                                <!-- Suggestions Column -->
                                <div class="col-md-3 suggestions">
                                    <!-- Blog Categories Well -->
                                    <div class="well">
                                        <h3>Suggested Articles</h3>
                                        <ul class="suggested-articles">
                                            <li><a href="#">Article 1</a> </li>
                                            <li><a href="#">Article 2</a> </li>
                                            <li><a href="#">Article 3</a> </li>
                                            <li><a href="#">Article 4</a> </li>
                                        </ul>
                                        <!-- /.row -->
                                    </div>
                                    <!-- end well -->
                                </div>
                                <!-- end suggestions -->
                            </div>
                            <!-- end article-content -->
                        </div>
                        <!-- end row artcile -->
                        <hr>
                        <div class="row social">
                            <h2>Discussion</h2>
                            <!-- Blog Comments -->
                            <!-- Comments Form -->
                            <div class="well">
                                <h4>Leave a Comment:</h4>
                                <form role="form">
                                    <div class="form-group">
                                        <textarea class="form-control" rows="3"></textarea>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </form>
                            </div>
                            <hr>
                            <!-- Posted Comments -->
                            <!-- Comment -->
                            <div class="media">
                                <a class="pull-left" href="#"> <img class="media-object" src="http://placehold.it/64x64" alt=""> </a>
                                <div class="media-body">
                                    <h4 class="media-heading">Comment #1
                    <small>March 31, 2017 at 9:30 AM</small>
                </h4> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                    <br>
                                    <button type="submit" class="btn-comment btn btn-primary">Reply</button>
                                    <button type="submit" class="btn-comment btn btn-primary">Like</button>
                                </div>
                            </div>
                        </div>
                        <!-- Social -->
                    </div>
                    <!-- nd-content-->
                </header>
                <%@include file="footer.html" %>
                    <script src="${contextPath}/resources/js/animations.js"></script>
            </body>

            </html>