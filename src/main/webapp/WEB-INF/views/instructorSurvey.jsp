<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <%--Bootstrap--%>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
    <div class="row" >
        <div class="col-md-2">
            <div class="list-group">
                <a href="${contextPath}/instructor" class="list-group-item">Instructor Home</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="row div-top">
                <input type="hidden" value="${courseKey}" id="course-key">
                <input type="hidden" value='${courseSurvey}' id="survey">
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
                    <li class="active"><a href="#" id="survey-a">Survey</a></li>
                    <li><a href="#" id="videos-a">Videos</a></li>
                    <li><a href="#" id="evaluation-a">Evaluations</a></li>
                </ul>
            </div>

            <div>
                <h1 id="create-survey-h1">Create Survey</h1>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="survey-title" class="col-sm-2 control-label">Name</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="survey-title" placeholder="Survey Name" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="survey-due-date" class="col-sm-2 control-label">Due Date</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" id="survey-due-date"/>
                        </div>
                        <div class="col-sm-6"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label label-questions">Questions</label>
                    </div>
                    <div id="survey-q-group">
                        <div class="question-group">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Question 1</label>
                                <div class="col-md-6">
                                    <input class="form-control input-question"/>
                                </div>
                                <label class="col-sm-1 control-label">Type</label>
                                <div class="col-md-2">
                                    <input class="form-control input-type"/>
                                </div>
                                <div class="col-md-1">
                                    <button type="button" class="btn btn-default btn-sm" id="btn-survey-add-question">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="form-group answer-row">
                                <%--Answer--%>
                                <label class="col-sm-2 control-label">Answer</label>
                                <div class="col-md-4">
                                    <input class="input-answer"/>
                                </div>
                                <%--Score--%>
                                <label class="col-sm-1 control-label">Score</label>
                                <div class="col-md-3">
                                    <input type="number" class="input-score"/>
                                </div>
                                <div class="col-md-2">
                                    <button type="button" class="btn btn-default btn-sm btn-survey-add-answer">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="question-group">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Question
                                    <span class="question-number">2</span>
                                </label>
                                <%--input question--%>
                                <div class="col-md-6">
                                    <input class="form-control input-question"/>
                                </div>
                                <%--input type--%>
                                <label class="col-sm-1 control-label">Type</label>
                                <div class="col-md-2">
                                    <input class="form-control input-type"/>
                                </div>
                                <%--remove button--%>
                                <div class="col-md-1">
                                    <button type="button" class="btn btn-default btn-sm btn-survey-remove-question">
                                        <span class="glyphicon glyphicon-minus"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="form-group answer-row">
                                <label class="col-sm-2 control-label">Answer</label>
                                <div class="col-md-4">
                                    <input class="input-answer"/>
                                </div>
                                <label class="col-sm-1 control-label">Score</label>
                                <div class="col-md-3">
                                    <input type="number" class="input-score"/>
                                </div>
                                <div class="col-md-2">
                                    <button type="button" class="btn btn-default btn-sm btn-survey-add-answer">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="instructor-btn-primary" id="survey-submit">Create</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

<script src="${contextPath}/resources/js/instructorSurvey.js"></script>
<script src="${contextPath}/resources/js/navbar.js"></script>
</html>

