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
    <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
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
        <h1 class="absolute-text">${courseTitle}</h1>
        <input type="hidden" value="${courseStartDate}" id="course-start-date">
    </div>
    <div class="ps-feature-content">
        <div class="row ps-feature">
            <div class="row ps-text-content">
                <!-- start course -->
                <div class="col-lg-2 ps-col-left">
                    <div class="ps-well">
                        <%--Learn Sidebar for Todos--%>
                        <jsp:include page="learnSidebar.jsp"/>
                    </div>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%@include file="learn-navbar.html" %>
                    <!-- Start Content -->
                </div>
                <div class="col-lg-10 ps-col-right">
                    <div class="ps-well">
                        <!-- Survey Name -->
                        <h2>${survey.title}
                            <hr>
                        </h2>
                        <br>
                        <br>
                        <input type="hidden" value="${survey.courseKey}" id="course-key">
                        <input type="hidden" value='${survey.courseTitle}' id="course-title">
                        <input type="hidden" value='${survey.title}' id="survey-title">
                        <input type="hidden" value='${survey.dueDate}' id="survey-due-date">
                        <!-- Form Start -->
                        <form class="ps-all-questions">
                            <!-- Questions Start -->
                            <c:forEach items="${survey.questions}" var="question">
                                <div class="ps-question question-group">
                                    <!-- Question Text -->
                                    <h3 class="input-question">${question.key.content}</h3>
                                    <input type="hidden" class="input-type" value=${question.key.type}>
                                    <div class="ps-answers answer-row">
                                        <!-- Answer Start -->
                                        <c:forEach items="${question.value}" var="answer">
                                            <div class="ps-answer left">
                                                <!-- Answer Button -->
                                                <!-- Set "id" equal to answer key + answer-->
                                                <!-- Set "name" equal to question key -->
                                                <!-- Set "value" to score answer score -->
                                                <input id="${answer.questionKey}${answer.answer}" class="input-score"
                                                       type="radio" value="${answer.score}"
                                                       name="${answer.questionKey}"/>
                                                <!-- Answer Text -->
                                                <!-- Set "for" equal to answer key + answer-->
                                                <label for="${answer.questionKey}${answer.answer}"
                                                       class="input-answer">${answer.answer}</label>
                                            </div>
                                        </c:forEach>
                                        <!-- Answer End-->
                                    </div>
                                </div>
                            </c:forEach>
                            <!-- Question End -->
                            <div class="center">
                                <!-- Submit Survey Button -->
                                <button type="button" class="ps-btn-primary" id="btn-submit-survey">
                                    <span>Submit Survey</span></button>
                            </div>
                        </form>
                        <!-- Form End -->
                    </div>
                    <!-- End Content -->
                    <br>
                </div>
            </div>
            <!-- End course -->
        </div>
    </div>
</header>


<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/survey.js"></script>
<script src="${contextPath}/resources/js/scrollreveal.js"></script>
<script src="${contextPath}/resources/js/animations.js"></script>
</body>

</html>