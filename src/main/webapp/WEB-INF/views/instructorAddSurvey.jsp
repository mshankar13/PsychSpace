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
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <%--Bootstrap--%>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <%--Customized--%>
    <link href="${contextPath}/resources/css/instructor.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/navbar.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/instructorSurvey.js"></script>
    <script src="${contextPath}/resources/js/navbar.js"></script>
</head>
<body>
<div class="navbar-wrapper">
    <%@include file="navbar.html"%>
</div>
<div class="wrapper">
    <div class="row" >
        <div class="col-md-3">
            <div class="list-group">
                <a href="${contextPath}/addCourse" class="list-group-item">Create Course</a>
                <a href="${contextPath}/editCourse" class="list-group-item">Edit Course</a>
                <a href="${contextPath}/addSurvey" class="list-group-item active">Create Survey</a>
                <a href="${contextPath}/editSurvey" class="list-group-item">Edit Survey</a>
                <a href="${contextPath}/deleteSurvey" class="list-group-item">Delete Survey</a>
                <a href="${contextPath}/addVideo" class="list-group-item">Add Video</a>
                <a href="${contextPath}/editVideo" class="list-group-item">Edit Video</a>
                <a href="${contextPath}/deleteVideo" class="list-group-item">Delete Video</a>
                <a href="${contextPath}/addEvaluation" class="list-group-item">Create Evaluation</a>
                <a href="${contextPath}/editEvaluation" class="list-group-item">Edit Evaluation</a>
            </div>
        </div>
        <div class="col-md-9">
            <div>
                <h1>Create Survey</h1>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="select-course" class="col-sm-2 control-label">Select Course</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="select-course">
                            <c:forEach items="${courses}" var="course">
                                <option class="select-course" value="${course.courseKey}" label="${course.title}">${course.title}</option>
                            </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-survey-title" class="col-sm-2 control-label">Name</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="add-survey-title" placeholder="Survey Name" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-survey-due-date" class="col-sm-2 control-label">Due Date</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" id="add-survey-due-date"/>
                        </div>
                        <div class="col-sm-6"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label label-questions">Questions</label>
                    </div>
                    <div id="add-survey-q-group">
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
                    <button type="submit" class="instructor-btn" id="add-survey-submit">Create</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

