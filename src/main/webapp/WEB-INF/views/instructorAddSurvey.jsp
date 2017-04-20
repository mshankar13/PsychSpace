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
    <link href="${contextPath}/resources/css/admin.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/adminAddSurvey.js"></script>
</head>
<body>
<div id="wrapper">

    <%@include file="instructor-sidebar.html" %>

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <h1>Create Survey</h1>
        <form>
            <%--<form:form class="form-horizontal" method="post"--%>
                       <%--modelAttribute="survey" action="admin_addSurvey">--%>
                <div class="form-group">
                    <label for="select-course">Course</label>
                    <select class="form-control" id="select-course">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="survey-title">Title</label>
                    <input type="text" class="form-control" id="survey-title" placeholder="Title" />
                    <%--<form:input type="text" class="form-control" id="survey-title" path="title" placeholder="Title" />--%>
                </div>
                <div id="add-survey-q-group">
                    <label style="font-size: 15pt">Questions</label>
                    <div class="form-group question-group">
                        <label>Question 1</label>
                        <div class="row">
                            <div class="col-md-10">
                                <input class="form-control" path="question"/>
                                <%--<form:input class="form-control" path="question"/>--%>
                            </div>
                            <div class="col-md-2">
                                <button type="button" class="btn btn-default btn-sm" id="btn-survey-add-question">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </button>
                            </div>
                        </div>
                        <div class="row">
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
                        <div class="row">
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
                <button type="submit" class="admin-btn">Create</button>
            <%--</form:form>--%>
            </form>
        </div>
    </div>
</div>
</body>
</html>

