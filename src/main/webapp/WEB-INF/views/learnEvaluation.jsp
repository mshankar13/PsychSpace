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
    <%@include file="navbar.html" %>
</div>

<header class="ps-feature-header">
    <div class="center page-banner">
        <img class="img-responsive" src="http://placehold.it/2000x500" alt="">
        <%--<!-- Course Title -->--%>
        <h1 class="absolute-text">${courseTitle}</h1>
        <input type="hidden" value="${courseStartDate}" id="course-start-date">
    </div>
    <div class="ps-feature-content">
        <div class="row ps-feature">
            <div class="row ps-text-content">
                <div class="col-lg-2 ps-col-left">
                    <c:set var="hasEvaluation" value="${hasEvaluation}"/>
                    <div class="ps-well">
                        <%--<!-- Learn Sidebar -->--%>
                        <h2>To Do:<hr></h2>
                        <c:choose>
                                <c:when test="${hasEvaluation == true and hasSurvey == true and hasHabit == true}">
                                    <h3>Good Job! You currently have no todos.</h3>
                                </c:when>
                                <c:otherwise>
                                    <!-- Set Goal -->
                                    <c:if test="${hasGoal == false}">
                                        <div class="center">
                                            <a class="button-enroll button fadein" id="button-goal"
                                               href="${contextPath}/learn/${courseKey}/survey"><span>Complete Survey</span>
                                            </a>
                                        </div>
                                    </c:if>
                                    <!-- Do Daily Evaluation -->
                                    <c:if test="${hasEvaluation == false}">
                                        <div class="center">
                                            <a class="button-enroll button fadein" id="button-evaluation"
                                               href="${contextPath}/learn/${courseKey}/evaluation"><span>Daily Evaluation</span>
                                            </a>
                                        </div>
                                    </c:if>
                                    <!-- Do Survey -->
                                    <c:if test="${hasEvaluation == false}">
                                        <div class="center">
                                            <a class="button-enroll button fadein" id="button-survey"
                                               href="${contextPath}/learn/${courseKey}/survey"><span>Complete Survey</span>
                                            </a>
                                        </div>
                                    </c:if>
                                    <%--Do Habit--%>
                                    <c:if test="${hasHabit == false}">
                                        <div class="center">
                                            <a class="button-enroll button fadein" id="button-habit"
                                               href="${contextPath}/learn/${courseKey}/habit"><span>Set Habit!</span>
                                            </a>
                                        </div>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                    </div>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- Learn Navbar -->--%>
                    <%@include file="learn-navbar.html" %>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- If Daily Evaluation has not been completed: -->--%>
                    <input type="hidden" value="${hasEvaluation}" id="hasEvaluation" />
                    <c:choose>
                        <c:when test="${hasEvaluation == 'false'}">
                            <%--<!-- Content Start-->--%>
                            <div class="ps-well">
                                <h2>Daily Evaluation for ${todayDate}
                                    <hr>
                                </h2>
                                <br>
                                <%--<!-- Form Start -->--%>
                                <form:form method="post" modelAttribute="evaluation"
                                           action="/learn/${courseKey}/evaluation/submit" class="ps-all-questions">
                                    <form:hidden path="authorKey" value=""/>
                                    <form:hidden path="author" value=""/>
                                    <form:hidden path="date" value=""/>
                                    <form:hidden path="courseKey" value="${courseKey}"/>
                                    <form:hidden path="evaluationKey" value=""/>
                                    <form:hidden path="score" value="${goal.unit}"/>
                                    <h3 class="ps-notice">My final goal is ${goal.goalName} ${goal.value} ${goal.unit} per day.
                                        <br>
                                        This week my goal is ${goal.goalName} ${weeklyGoalValue} ${goal.unit} per day.</h3>
                                    <%--<!-- Question Start -->--%>
                                    <div class="ps-question">
                                        <%--<!-- Question Text -->--%>
                                        <h3>How many ${goal.unit} did you complete today?</h3>
                                        <div class="ps-answers">
                                            <%--<!-- Answer Start -->--%>
                                            <div class="ps-answer">
                                                <span><form:input class="ps-number-input" type="number"  min="0" path="rawScore"
                                                                  placeholder="Number" id="evaluation-rawScore"/>
                                                ${goal.unit} out of ${weeklyGoalValue} ${goal.unit}</span>
                                            </div>
                                            <%--<!-- Answer End-->--%>
                                        </div>
                                    </div>
                                    <%--<!-- Question End -->--%>
                                    <%--<!-- Question Start -->--%>
                                    <div class="ps-question ps-optional-response">
                                        <%--<!-- Question Text -->--%>
                                        <h3>What are your feelings? (Optional)</h3>
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
                                        <button type="submit" class="ps-btn-primary" id="btn-submit-evaluation">
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
                                    <h2>My Daily Evaluations<hr></h2>
                                    <br>
                                    <%--<!-- Table Start -->--%>
                                    <table class="ps-table">
                                        <tr>
                                            <th class="ps-table-key"><h4>Date</h4></th>
                                            <th class="ps-table-key"><h4>How did I do</h4></th>
                                            <td class="ps-table-key"><h4>Weekly Goal</h4></td>
                                            <td class="ps-table-key"><h4>Final Goal</h4></td>
                                            <th class="ps-table-key"><h4>Feelings</h4></th>
                                        </tr>
                                        <%--<!-- Load all evaluations for the current user -->--%>
                                            <c:forEach items="${evaluationList}" var="singleEvaluation">
                                        <tr>
                                            <td class="ps-table-row">${singleEvaluation.date}</td>
                                            <td class="ps-table-row">${singleEvaluation.score}%</td>
                                            <td class="ps-table-row">${weeklyGoalValue}</td>
                                            <td class="ps-table-row">${goal.value}</td>
                                            <td class="ps-table-row"><p>${singleEvaluation.content}</p></td>
                                        </tr>
                                            </c:forEach>
                                        <%--<!-- End load evaluations -->--%>
                                    </table>
                                    <%--<!-- Table End -->--%>
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
<script src="${contextPath}/resources/js/scrollreveal.js"></script>
<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/learnNavbar.js"></script>
<script src="${contextPath}/resources/js/evaluation.js"></script>
<script src="${contextPath}/resources/js/animations.js"></script>
</body>
</html>