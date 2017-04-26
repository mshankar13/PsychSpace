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
                <a href="${contextPath}/addSurvey" class="list-group-item">Create Survey</a>
                <a href="${contextPath}/editSurvey" class="list-group-item">Edit Survey</a>
                <a href="${contextPath}/deleteSurvey" class="list-group-item">Delete Survey</a>
                <a href="${contextPath}/addVideo" class="list-group-item active">Add Video</a>
                <a href="${contextPath}/editVideo" class="list-group-item">Edit Video</a>
                <a href="${contextPath}/deleteVideo" class="list-group-item">Delete Video</a>
                <a href="${contextPath}/addEvaluation" class="list-group-item">Create Evaluation</a>
                <a href="${contextPath}/editEvaluation" class="list-group-item">Edit Evaluation</a>
            </div>
        </div>
        <div class="col-md-9">
            <div>
                <h1>Add Video</h1>
                <form:form class="form-horizontal" method="post"
                           modelAttribute="video" action="addVideo">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Select Course</label>
                        <div class="col-sm-6">
                            <form:select class="form-control" path="courseKey" >
                                <c:forEach items="${courses}" var="course">
                                    <form:option value="${course.courseKey}" label="${course.title}"/>
                                    <form:hidden value="${course.title}" path="courseTitle"/>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-video-title" class="col-sm-2 control-label">Video Name</label>
                        <div class="col-sm-6">
                            <form:input type="text" class="form-control" id="add-video-title" path="title" placeholder="Video Name" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-video-link" class="col-sm-2 control-label">Video Link</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="text" id="add-video-link" path="url" placeholder="Video Link"/>
                        </div>
                    </div>
                    <button type="submit" class="instructor-btn" id="add-video-submit">Add</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

