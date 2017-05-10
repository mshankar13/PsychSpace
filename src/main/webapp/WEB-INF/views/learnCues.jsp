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
    <script src="${contextPath}/resources/js/habit.js"></script>
</head>

<body>
<div class="navbar-wrapper">
    <%@include file="navbar.html" %>
</div>

<header class="ps-feature-header">
    <div class="center page-banner">
        <img class="img-responsive" src="http://placehold.it/2000x500" alt="">
        <!-- Course Title -->
        <h1 class="absolute-text">${courseTitle}</h1>
        <input type="hidden" value="${courseStartDate}" id="course-start-date">
    </div>
    <div class="ps-feature-content">
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
                    <!-- Learn Navbar -->
                    <%@include file="learn-navbar.html" %>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <!-- Content -->
                    <!-- Start -->
                    <div class="col-lg">
                        <div class="ps-well">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="ps-well">
                                        <h1>New Cue<hr></h1>
                                        <button type="button" class="btn-comment btn btn-primary btn-cue-add">New</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="ps-well">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="ps-well">
                                        <h1>My Cues<hr></h1>
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="ps-well">
                                                    <h2>Negative Cues<hr></h2>
                                                </div>
                                            </div>
                                            <%--<div class="col-lg-6">--%>
                                                <%--<div class="ps-well">--%>
                                                    <%--<h3>[Load Negative]</h3>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                            <c:forEach items="${negativeCues}" var="cue">
                                            <div class="col-lg-6">
                                                <div class="ps-well">
                                                    <%--<h2>[Name of Cue]<hr></h2>--%>
                                                    <div class="left">
                                                        <h4>Where were you?</h4>
                                                        <p>${cue.location}</p>
                                                    </div>
                                                    <br>
                                                    <div class="left">
                                                        <h4>What time was it?</h4>
                                                        <p>${cue.time}</p>
                                                    </div>
                                                    <br>
                                                    <div class="left">
                                                        <h4>What was your emotional state?</h4>
                                                        <p>${cue.emotionalState}</p>
                                                    </div>
                                                    <br>
                                                    <div class="left">
                                                        <h4>Who else was around?</h4>
                                                        <p>${cue.environment}</p>
                                                    </div>
                                                    <br>
                                                    <div class="left">
                                                        <h4>What action proceeded the cue?</h4>
                                                        <p>${cue.action}</p>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>
                                            </c:forEach>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="ps-well">
                                                    <h2>Positive Cues<hr></h2>
                                                </div>
                                            </div>
                                            <%--<div class="col-lg-6">--%>
                                                <%--<div class="ps-well">--%>
                                                    <%--<h3>[Load Positive] </h3>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                            <c:forEach items="${positiveCues}" var="cue">
                                            <div class="col-lg-6">
                                                <div class="ps-well">
                                                    <h2>[Name of Cue]<hr></h2>
                                                    <div class="left">
                                                        <h4>Where were you?</h4>
                                                        <p>${cue.location}</p>
                                                    </div>
                                                    <br>
                                                    <div class="left">
                                                        <h4>What time was it?</h4>
                                                        <p>${cue.time}</p>
                                                    </div>
                                                    <br>
                                                    <div class="left">
                                                        <h4>What was your emotional state?</h4>
                                                        <p>${cue.emotionalState}</p>
                                                    </div>
                                                    <br>
                                                    <div class="left">
                                                        <h4>Who else was around?</h4>
                                                        <p>${cue.environment}</p>
                                                    </div>
                                                    <br>
                                                    <div class="left">
                                                        <h4>What action proceeded the cue?</h4>
                                                        <p>${cue.action}</p>
                                                    </div>
                                                    <br>
                                                </div>
                                            </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="ps-well">
                            <div class="row">
                                <div class="col-lg-4 ps-col-left">
                                    <div class="ps-well">
                                        <h1>Cue</h1>
                                        <h1>Routine</h1>
                                        <h1>Reward</h1>
                                    </div>
                                </div>
                                <div class="col-lg-8 ps-col-right">
                                    <div class="ps-well">
                                        <div id='piemenu' data-wheelnav data-wheelnav-slicepath='DonutSlice' data-wheelnav-marker data-wheelnav-markerpath='PieLineMarker' data-wheelnav-rotateoff data-wheelnav-navangle='270' data-wheelnav-cssmode data-wheelnav-init>
                                            <div data-wheelnav-navitemtext='Cue'></div>
                                            <div data-wheelnav-navitemtext='Routine'></div>
                                            <div data-wheelnav-navitemtext='Reward'></div>
                                        </div>
                                        <div class="center">
                                            <h2 id="ps-cue">[d]</h2>
                                            <h2 id="ps-rew">[Logfdtive]</h2>
                                            <h2 id="ps-resp">f</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Add Cue Modal-->
                    <div class="modal ps-modal-type-comment fade" id="add-cue-modal" role="dialog">
                        <div class="ps-modal-dialog">
                            <!-- Modal content-->
                            <div class="ps-modal-content">
                                <div class="ps-modal-header">
                                    <button type="button" class="ps-type-close close ps-modal-close" data-dismiss="modal">
                                        <h1>X</h1></button>
                                    <div class="left">
                                        <h2 class="ps-feature-info-header"> New Cue<hr></h2>
                                    </div>
                                </div>
                                <div class="ps-modal-body">
                                    <div class="form-group">
                                        <!-- Start Content -->
                                        <div class="left">
                                            <!-- Form -->
                                            <div class="left">
                                                <h4>Positive or Negative Cue?</h4>
                                                <button type="button" class="btn-comment btn btn-primary btn-cue-positive">Positive</button>
                                                <button type="button" class="btn-comment btn btn-primary btn-cue-negative">Negative</button>
                                            </div>
                                            <br>
                                            <div class="left">
                                                <h4>Cue Summary:</h4>
                                                <textarea class="form-control" id="cues-cue-name" rows="1"></textarea>
                                            </div>
                                            <br>
                                            <div class="left">
                                                <h4>Where were you?</h4>
                                                <textarea class="form-control" id="cues-cue-location" rows="1"></textarea>
                                            </div>
                                            <br>
                                            <div class="left">
                                                <h4>What time was it?</h4>
                                                <textarea class="form-control" id="cues-cue-time" rows="1"></textarea>
                                            </div>
                                            <br>
                                            <div class="left">
                                                <h4>What was your emotional state?</h4>
                                                <textarea class="form-control" id="cues-cue-feelings" rows="1"></textarea>
                                            </div>
                                            <br>
                                            <div class="left">
                                                <h4>Who else was around?</h4>
                                                <textarea class="form-control" id="cues-cue-environment" rows="1"></textarea>
                                            </div>
                                            <br>
                                            <div class="left">
                                                <h4>What action proceeded the cue?</h4>
                                                <textarea class="form-control" id="cues-cue-action" rows="1"></textarea>
                                            </div>
                                            <br>
                                            <div class="right">
                                                <button id="btn-cue-post" type="submit" class="btn-comment btn btn-primary">Submit
                                                </button>
                                            </div>
                                        </div>
                                        <!-- End Content -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Edit Modal -->
                </div>
            </div>
            <!-- End course -->
        </div>
    </div>
</header>

<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/learnNavbar.js"></script>
<script src="${contextPath}/resources/js/learn.js"></script>
<script src="${contextPath}/resources/js/cues.js"></script>
<script src="${contextPath}/resources/js/raphael.min.js"></script>
<script src="${contextPath}/resources/js/raphael.icons.min.js"></script>
<script src="${contextPath}/resources/js/wheelnav.min.js"></script>
<script src="${contextPath}/resources/js/scrollreveal.js"></script>
<script src="${contextPath}/resources/js/animations.js"></script>

</body>
</html>