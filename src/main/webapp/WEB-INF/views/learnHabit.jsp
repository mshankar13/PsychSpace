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
        <h1 class="absolute-text">${courseTitle}</h1>
    </div>
    <div class="ps-feature-content">
        <c:set var="hasStarted" value="${hasStarted}" />
        <input type="hidden" value="${courseStartDate}" id="course-start-date">
        <div class="row ps-feature">
            <div class="row ps-text-content">
                <div class="col-lg-2 ps-col-left">
                    <div class="ps-well">
                        <!-- Learn Sidebar for Todos-->
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
                                <c:if test="${hasEvaluation == false and hasStarted == true}">
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
                    <!-- Learn Navbar -->
                    <%@include file="learn-navbar.html" %>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <div class="ps-well">
                        <%--Goal--%>
                        <div class="col-lg-12">
                                <div class="col-md-2">
                                    <h1>My Goal</h1>
                                </div>
                                <div class="col-md-7">
                                    <h3>Make sure it's set by ${dueDates.goalDueDate}</h3>
                                </div>
                                <div class="col-md-3 right">
                                    <button class="ps-btn" id="btn-example-goal">See examples</button>
                                </div>
                                <input type="hidden" value="${goal}" id="goal">
                                <input type="hidden" value="${dueDates.goalDueDate}" id="goal-due-date">
                                <input type="hidden" value="${dueDates.habitDueDate}" id="habit-due-date">
                                <form:form class="form-horizontal" method="post"
                                           modelAttribute="goal" action="/learn/${courseKey}/habit/submitGoal">
                                    <div class="form-group ps-well">
                                        <span class="col-md-4">By the end of the course, I want to</span>
                                        <form:input class="col-md-2" type="text" path="goalName" placeholder="Action"
                                                    id="action"/>
                                        <form:input class="col-md-2" type="number" path="value" placeholder="Value"
                                                    id="value"/>
                                        <form:input class="col-md-2" type="text" path="unit" placeholder="Unit"
                                                    id="unit"/>
                                        <span class="col-md-2">per day.</span>
                                        <form:hidden path="goalKey" value=""/>
                                        <form:hidden path="userName" value=""/>
                                        <form:hidden path="userKey" value=""/>
                                        <form:hidden path="courseKey" value="${courseKey}"/>
                                    </div>
                                    <div class="right">
                                        <button type="submit" class="ps-btn-primary">Save</button>
                                    </div>
                                </form:form>
                        </div>
                            <hr>
                    </div>
                </div>
                <br>
                    <%--Habit--%>
                <div class="col-lg-10 ps-col-right">
                    <div class="ps-well">
                        <div class="col-lg-12">
                            <%--Habit Section Title--%>
                            <div class="col-md-8">
                                <h1>My Habit<hr></h1>
                            </div>
                            <div class="col-md-4">
                                <h3>Make sure you set by ${dueDates.habitDueDate}</h3>
                            </div>
                        </div>
                        <%--Habit Loop--%>
                        <div class="col-lg-4">
                            <div class="ps-well">
                                <h1>Cue:
                                    <hr>
                                </h1>
                                <h2>[Go to Library] </h2>
                                <div class="center">
                                    <button type="button" class="btn-comment btn btn-primary btn-comment-edit">Set Cue
                                    </button>
                                    <button type="button" class="btn-comment btn btn-primary btn-comment-edit">Change
                                        Cue
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="ps-well">
                                <h1>Routine:
                                    <hr>
                                </h1>
                                <h2>[Study 3 hours per day]</h2>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="ps-well">
                                <h1>Reward:
                                    <hr>
                                </h1>
                                <h2>[Candy from the Trolley] </h2>
                                <div class="center">
                                    <button type="button" class="btn-comment btn btn-primary btn-comment-edit">Set
                                        Reward
                                    </button>
                                    <button type="button" class="btn-comment btn btn-primary btn-comment-edit">Change
                                        Reward
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <!-- End Learn Page -->
        <%--Sample Goals Modal--%>
        <div class="modal fade" id="exampleGoalsModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="ps-modal-dialog">
                <div class="ps-modal-content" role="document">
                    <div class="ps-modal-header row">
                        <div class="col-sm-10">
                            <h1 class="modal-title">Example Goals</h1>
                        </div>
                        <div class="col-sm-2">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </div>
                    <div class="ps-modal-body">
                        <p>I want to study 1 chapter per day.</p>
                        <p>I want to read 10 pages per day.</p>
                        <p>I want to do 5 problems per day.</p>
                        <p>I want to practice Spanish 30 minutes per day.</p>
                    </div>
                    <div class="ps-modal-footer right">
                        <button type="button" class="ps-btn" data-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</header>

<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/learn.js"></script>
<script src="${contextPath}/resources/js/learnNavbar.js"></script>
<script src="${contextPath}/resources/js/raphael.min.js"></script>
<script src="${contextPath}/resources/js/raphael.icons.min.js"></script>
<script src="${contextPath}/resources/js/wheelnav.min.js"></script>
<script src="${contextPath}/resources/js/scrollreveal.js"></script>
<script src="${contextPath}/resources/js/animations.js"></script>

</body>
</html>