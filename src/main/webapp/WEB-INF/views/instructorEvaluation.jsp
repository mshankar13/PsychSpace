<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
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
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <link href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <%--Bootstrap--%>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet"/>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.js"></script>
    <%--Customized--%>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
    <link href="${contextPath}/resources/css/instructor.css" rel="stylesheet">
</head>
<body>
<div class="navbar-wrapper">
    <%@include file="navbar.html"%>
</div>
<div class="wrapper">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="${contextPath}/instructor" class="list-group-item">Instructor Home</a>
            </div>
        </div>
        <div class="col-md-10">

            <div class="row div-top">
                <div class="col-md-9">
                    <label for="select-course" class="col-md-3 control-label">Select Course</label>
                    <select class="col-md-6" id="select-course">
                        <c:forEach items="${courses}" var="course">
                            <c:choose>
                                <c:when test="${course.courseKey ==  courseKey}">
                                    <option selected="selected" class="select-course" value="${course.courseKey}" label="${course.title}">${course.title}</option>
                                </c:when>
                                <c:otherwise>
                                    <option class="select-course" value="${course.courseKey}" label="${course.title}">${course.title}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <button class="instructor-btn-primary" id="btn-add-course">Create Course</button>
                </div>
            </div>

            <div class="tab">
                <ul class="nav nav-tabs">
                    <li><a href="#" id="course-a">Course</a></li>
                    <li><a href="#" id="survey-a">Survey</a></li>
                    <li><a href="#" id="videos-a">Videos</a></li>
                    <li class="active"><a href="#" id="evaluation-a">Evaluations</a></li>
                </ul>
            </div>

            <div>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="goal-due-date" class="col-sm-2 control-label">Goal Due Date</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="goal-due-date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cues-due-date" class="col-sm-2 control-label">Cues Due Date</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="cues-due-date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="habit-due-date" class="col-sm-2 control-label">Habit Due Date</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="habit-due-date">
                        </div>
                    </div>
                    <button type="submit" class="instructor-btn-primary" id="evaluations-dates-submit">Save</button>
                </form>

            </div>
        </div>
    </div>
</div>
</body>

<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/instructorEvaluation.js"></script>
</html>

