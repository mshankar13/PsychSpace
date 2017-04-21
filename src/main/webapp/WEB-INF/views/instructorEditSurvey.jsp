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
    <script src="${contextPath}/resources/js/navbar.js"></script>
    <script src="${contextPath}/resources/js/instructorSurveyjs"></script>
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
        <div class="col-md-3">
            <div class="list-group">
                <a href="${contextPath}/addCourse" class="list-group-item">Create Course</a>
                <a href="${contextPath}/editCourse" class="list-group-item">Edit Course</a>
                <a href="${contextPath}/addSurvey" class="list-group-item">Create Survey</a>
                <a href="${contextPath}/editSurvey" class="list-group-item active">Edit Survey</a>
                <a href="${contextPath}/deleteSurvey" class="list-group-item">Delete Survey</a>
                <a href="${contextPath}/addEvaluation" class="list-group-item">Create Evaluation</a>
                <a href="${contextPath}/editEvaluation" class="list-group-item">Edit Evaluation</a>
            </div>
        </div>
        <div class="col-md-9">
            <div >
                <h1>Edit Survey</h1>

                <table id="edit-article-table" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Course </th>
                        <th>Survey Name</th>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${surveys}" var="survey">
                        <tr>
                            <input type="hidden" value="${course.userKey}" id="userKey"/>
                            <input type="hidden" value="${course.courseKey}" id="courseKey"/>
                            <input type="hidden" value="${course.title}" id="title"/>
                            <input type="hidden" value="${course.surveyKey}" id="surveyKey"/>
                            <input type="hidden" value="${course.dueDate}" id="dueDate"/>
                            <td>${survey.title}</td>
                            <td>${survey.title}</td>
                            <td><button id="btn-edit-survey" class="instructor-btn" data-toggle="modal" data-target="editSurveyModal">Edit</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


                <div class="modal fade" id="editSurveyModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header row">
                                <div class="col-sm-10">
                                    <h3 class="modal-title">Edit <strong><span id="edit-course-title"></span></strong></h3>
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="edit-survey-title">Title</label>
                                    <input type="text" class="form-control" id="edit-survey-title" placeholder="Title" />
                                </div>
                                <form class="form-horizontal">
                                    <div class="form-group">
                                        <label for="select-course" class="col-sm-2 control-label">Select Course</label>
                                        <div class="col-sm-6">
                                            <select class="form-control" id="select-course">
                                                <c:forEach items="${courses}" var="course">
                                                    <option class="select-course" value="${course.courseKey}">${course.title}</option>
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
                                        <label class="label-questions">Questions</label>
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
                            <div class="modal-footer">
                                <button type="button" class="btn-secondary" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-primary" id="edit-survey-submit">Save</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

