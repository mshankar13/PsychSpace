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
    <script src="${contextPath}/resources/js/learnNavbar.js"></script>
    <script src="${contextPath}/resources/js/evaluation.js"></script>
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
        <%--<!-- Course Title -->--%>
        <h1 class="absolute-text">[Course Title]</h1>
    </div>
    <div class="ps-feature-content">
        <div class="row ps-feature">
            <div class="row ps-text-content">
                <div class="col-lg-2 ps-col-left">
                    <div class="ps-well">
                        <%--<!-- Learn Sidebar -->--%>
                        <%@include file="learn-sidebar.html" %>
                    </div>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- Learn Navbar -->--%>
                    <%@include file="learn-navbar.html" %>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- If Daily Evaluation has not been completed: -->--%>
                    <input type="hidden" value="${hasEvaluation}" id="hasEvaluation" />
                    <c:set var="hasEvaluation" value="${hasEvaluation}"/>
                    <c:choose>
                        <c:when test="${hasEvaluation == 'false'}">
                            <%--<!-- Content Start-->--%>
                            <div class="ps-well">
                                <h2>Daily Evaluation for [Date]
                                    <hr>
                                </h2>
                                <br>
                                <br>
                                <%--<!-- Form Start -->--%>
                                <form:form method="post" modelAttribute="evaluation"
                                           action="/learn/${courseKey}/evaluation/submit" class="ps-all-questions">
                                    <form:hidden path="authorKey" value=""/>
                                    <form:hidden path="author" value=""/>
                                    <form:hidden path="date" value=""/>
                                    <form:hidden path="courseKey" value="${courseKey}"/>
                                    <form:hidden path="evaluationKey" value=""/>
                                    <form:hidden path="score" value=""/>
                                    <%--<!-- Question Start -->--%>
                                    <div class="ps-question">
                                        <%--<!-- Question Text -->--%>
                                        <h3>How many ${goal.unit} did you complete today?</h3>
                                        <div class="ps-answers">
                                            <%--<!-- Answer Start -->--%>
                                            <div class="ps-answer">
                                                <span><form:input class="ps-number-input" type="number"  min="0" path="rawScore"
                                                                  placeholder="Number" id="evaluation-rawScore"/>
                                                ${goal.unit} out of ${goal.value} ${goal.unit}</span>
                                            </div>
                                            <%--<!-- Answer End-->--%>
                                        </div>
                                    </div>
                                    <%--<!-- Question End -->--%>
                                    <%--<!-- Question Start -->--%>
                                    <div class="ps-question ps-optional-response">
                                        <%--<!-- Question Text -->--%>
                                        <h3>What are your feelings?</h3>
                                        <div class="ps-answers">
                                            <%--<!-- Answer Start -->--%>
                                            <div class="ps-answer">
                                                <form:textarea class="ps-text-area" id="evaluation-content" rows="4"
                                                               path="content"/>
                                            </div>
                                            <%--<!-- Answer End-->--%>
                                        </div>
                                    </div>
                                    <%--<!-- Question End -->--%>
                                    <div class="center">
                                        <%--<!-- Submit Survey Button -->--%>
                                        <button type="submit" class="button" id="btn-submit-evaluation">
                                            <span>Submit Responses</span>
                                        </button>
                                    </div>
                                </form:form>
                                <%--<!-- Form End -->--%>
                            </div>
                            <%--<!-- Content End -->--%>
                            <%--<!-- If End -->--%>
                        </c:when>
                        <%--<!-- Else Daily Evaluation has been completed: -->--%>
                        <c:otherwise>
                            <div class="ps-well">
                                <%--<!-- Load the list of completed evaluations -->--%>
                                <h2>Loading All Evaluations...</h2>
                                <%--<c:forEach items="${evaluationList}" var="singleEvaluation">--%>
                                <%--</c:forEach>--%>
                            </div>
                            <%--<!-- Else End -->--%>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</header>

<%@include file="footer.html" %>
<%--<script src="${contextPath}/resources/js/style.js"></script>--%>
<script src="${contextPath}/resources/js/animations.js"></script>

</body>
</html>