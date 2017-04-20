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
    <script src="${contextPath}/resources/js/instructorAddSurvey.js"></script>
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
                <a href="${contextPath}/addEvaluation" class="list-group-item">Create Evaluation</a>
                <a href="${contextPath}/editEvaluation" class="list-group-item">Edit Evaluation</a>
            </div>
        </div>
        <div class="col-md-9">
            <div>
                <h1>Create Survey</h1>
                <form>
                <%--<form:form class="form-horizontal" method="post"--%>
                           <%--modelAttribute="survey" action="addSurvey">--%>
                    <div class="form-group">
                        <label for="select-course">Select Course</label>
                        <select class="form-control" id="select-course">
                        <c:forEach items="${courses}" var="course">
                            <option>${course.title}</option>
                        </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="add-survey-title">Title</label>
                        <input type="text" class="form-control" id="add-survey-title" placeholder="Title" />
                        <%--<form:input type="text" class="form-control" id="survey-title" path="title" placeholder="Title" />--%>
                    </div>
                    <div id="add-survey-q-group">
                        <label style="font-size: 15pt">Questions</label>
                        <div class="form-group question-group">
                            <label>Question 1</label>
                            <div class="row">
                                <div class="col-md-10">
                                    <input class="form-control" path="question"/>
                                </div>
                                <div class="col-md-2">
                                    <button type="button" class="btn btn-default btn-sm" id="btn-survey-add-question">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="row answer-row">
                                <div class="col-md-1">
                                    <label>Answer</label>
                                </div>
                                <div class="col-md-4">
                                    <input class="input-answer"/>
                                    <%--<form:input class="form-control input-answer" path="question"/>--%>
                                </div>
                                <div class="col-md-1">
                                    <label>Score</label>
                                </div>
                                <div class="col-md-3">
                                    <input type="number" class="score"/>
                                </div>
                                <div class="col-md-2">
                                    <button type="button" class="btn btn-default btn-sm btn-survey-add-answer">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="form-group question-group">
                            <label>Question <span class="question-number">2</span></label>
                            <div class="row">
                                <div class="col-md-10">
                                    <input class="form-control" path="question1"/>
                                    <%--<form:input class="form-control" path="question1"/>--%>
                                </div>
                                <div class="col-md-2">
                                    <button type="button" class="btn btn-default btn-sm btn-survey-remove-question">
                                        <span class="glyphicon glyphicon-minus"></span>
                                    </button>
                                </div>
                            </div>
                            <div class="row answer-row">
                                <div class="col-md-1">
                                    <label>Answer</label>
                                </div>
                                <div class="col-md-4">
                                    <input class="input-answer"/>
                                    <%--<form:input class="form-control" path="question"/>--%>
                                </div>
                                <div class="col-md-1">
                                    <label>Score</label>
                                </div>
                                <div class="col-md-3">
                                    <input type="number" />
                                </div>
                                <div class="col-md-2">
                                    <button type="button" class="btn btn-default btn-sm btn-survey-add-answer">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="admin-btn" id="add-survey-submit">Create</button>
                <%--</form:form>--%>
                </form>
            </div>
        </div>
        </div>
    </div>
</div>
</body>
</html>

