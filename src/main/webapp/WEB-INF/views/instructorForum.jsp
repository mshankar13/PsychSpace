<%--
  Created by IntelliJ IDEA.
  User: aliao
  Date: 5/16/2017
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
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
    <jsp:include page="navbar.jsp" />
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
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- Learn Navbar -->--%>
                    <%@include file="learn-navbar.html" %>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- Content -->--%>
                    <c:set var="threadLists" value="${threads}"/>
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
                        <c:choose>
                            <c:when test="${empty threadLists}">
                                <h2>You have no lists!</h2>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${threads}" var="singleThread">
                                    <c:set var="threadUserKey" value="${singleThread.userKey}"/>
                                    <c:choose>
                                        <%-- If thread was created by the current user--%>
                                        <c:when test="${currUserKey == threadUserKey}">
                                            <ul class="ps-ul">
                                                    <%-- Link to thread page --%>
                                                <li class="ps-li threadListTitle">
                                                    <a href="/instructor/${courseKey}/forum/${singleThread.threadKey}"> ${singleThread.title} </a>
                                                </li>
                                            </ul>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <%-- My Threads End--%>
                    <br>
                    <%-- Community Thread Start --%>
                    <div class="col-lg">
                        <div class="ps-well">
                            <div>
                                <h2>Community Threads
                                    <hr>
                                </h2>
                            </div>
                            <c:choose>
                                <c:when test="${empty threadLists}">
                                    <h2>There are no community threads!</h2>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${threads}" var="singleThread">
                                        <c:set var="threadUserKey" value="${singleThread.userKey}"/>
                                        <c:choose>
                                            <%-- If thread was created by a community user--%>
                                            <c:when test="${currUserKey != threadUserKey}">
                                                <ul class="ps-ul">
                                                        <%-- Link to thread page --%>
                                                    <li class="ps-li threadListTitle">
                                                        <a href="/instructor/${courseKey}/forum/${singleThread.threadKey}"> ${singleThread.title} </a>
                                                    </li>
                                                </ul>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <%-- Community Threads End--%>

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
                                                <form:hidden path="threadKey" value="0" id="add-thread-modal-key"/>
                                                <form:hidden path="courseKey" value="0"/>
                                                <form:hidden path="userKey" value="0"/>
                                                <form:hidden path="date" value="0"/>
                                                <form:hidden path="inThreadName" value="0"/>

                                                <div class="left">
                                                    <h4>Thread Name:</h4>
                                                    <form:textarea id="forum-title" path="title" class="form-control"
                                                                   rows="1"
                                                                   autofocus="true"/>
                                                </div>
                                                <br>
                                                <div class="left">
                                                    <h4>Thread Content</h4>
                                                    <form:textarea id="forum-thread-content" path="content"
                                                                   class="form-control"
                                                                   rows="5"
                                                                   autofocus="true"/>
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
