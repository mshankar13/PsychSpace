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
        <h1 class="absolute-text">${course.title}</h1>
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
                    <%--<!-- Learn Navbar -->--%>
                    <%@include file="learn-navbar.html" %>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- Content -->--%>
                    <c:forEach items="${threads}" var="singleThread">
                        <c:set var="threadUserKey" value="${singleThread.userKey}"/>
                        <c:choose>
                            <%-- If thread was created by the current user--%>
                            <c:when test="${currUserKey == threadUserKey}">
                                <%-- My Thread Start --%>
                                <div class="ps-well">
                                    <div>
                                        <h2>My Threads
                                            <hr>
                                        </h2>
                                        <button type="button"
                                                class="btn-comment btn btn-primary btn-thread-add">+
                                        </button>
                                    </div>
                                    <ul class="ps-ul">
                                            <%-- Link to thread page --%>
                                        <li class="ps-li threadListTitle">
                                            <a href="/learn/${courseKey}/forum/${singleThread.threadKey}"> ${singleThread.title} </a>
                                        </li>
                                    </ul>

                                </div>
                                <%-- My Threads End--%>
                                <br>
                            </c:when>
                            <c:otherwise>
                                <%-- Else thread was not created by the user--%>
                                <%-- Community Thread Start --%>
                                <div class="col-lg">
                                    <div class="ps-well">
                                        <div>
                                            <h2>Community Threads
                                                <hr>
                                            </h2>
                                        </div>
                                        <ul class="ps-ul">
                                                <%-- Link to thread page --%>
                                            <li class="ps-li">
                                                <a href="/learn/${courseKey}/forum/${singleThread.threadKey}"> ${singleThread.title} </a>
                                            </li>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <%-- Community Threads End--%>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <%-- List Threads End--%>
                    <br>
                    <!-- Add Forum Modal-->
                    <div class="modal ps-modal-type-comment fade" id="add-thread-modal" role="dialog">
                        <div class="ps-modal-dialog">
                            <!-- Modal content-->
                            <div class="ps-modal-content">
                                <div class="ps-modal-header">
                                    <button type="button" class="ps-type-close close ps-modal-close"
                                            data-dismiss="modal">
                                        <h1>X</h1></button>
                                    <div class="left">
                                        <h2 class="ps-feature-info-header"> New Thread
                                            <hr>
                                        </h2>
                                    </div>
                                </div>
                                <div class="ps-modal-body">
                                    <div class="form-group">
                                        <!-- Start Content -->
                                        <div class="left">
                                            <!-- Form -->
                                            <form:form method="post" action="/learn/${courseKey}/forum/addThread"
                                                       modelAttribute="thread">
                                                <form:hidden path="threadKey" value="0" id="edit-comment-modal-key"/>
                                                <form:hidden path="courseKey" value="0"/>
                                                <form:hidden path="userKey" value="0"/>
                                                <form:hidden path="inThreadName" value="0"/>
                                                <form:hidden path="date" value="0"/>

                                                <div class="left">
                                                    <h4>Thread Name:</h4>
                                                    <form:textarea id="forum-title" path="title" class="form-control"
                                                                   rows="1"
                                                                   autofocus="true"/>
                                                        <%--<textarea class="form-control" id="forum-thread-name"--%>
                                                        <%--rows="1"></textarea>--%>
                                                </div>
                                                <br>
                                                <div class="left">
                                                    <h4>Thread Content</h4>
                                                    <form:textarea id="forum-thread-content" path="content"
                                                                   class="form-control"
                                                                   rows="5"
                                                                   autofocus="true"/>
                                                        <%--<textarea class="form-control" id="forum-thread-content"--%>
                                                        <%--rows="5"></textarea>--%>
                                                </div>
                                                <br>
                                                <div class="left">
                                                        <%-- Hidden input for anonymity --%>
                                                    <h4>Display Name or Stay Anonymous?</h4>
                                                    <button type="button" class="btn-comment btn btn-primary">Display
                                                        Name
                                                    </button>
                                                    <button type="button" class="btn-comment btn btn-primary">Stay
                                                        Anonymous
                                                    </button>
                                                </div>
                                                <br>
                                                <div class="right">
                                                    <button id="btn-thread-post" type="submit"
                                                            class="btn-comment btn btn-primary">Create Thread
                                                    </button>
                                                </div>
                                            </form:form>
                                        </div>
                                        <!-- End Content -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Add Forum Modal -->
                </div>
                <!-- Content End-->
            </div>
            <!-- End Learn Page -->
        </div>
    </div>
</header>

<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/scrollreveal.js"></script>
<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/learnNavbar.js"></script>
<script src="${contextPath}/resources/js/forum.js"></script>
<script src="${contextPath}/resources/js/animations.js"></script>
</body>
</html>