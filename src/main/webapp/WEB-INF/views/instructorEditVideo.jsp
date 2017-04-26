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
        <div class="col-md-3">
            <div class="list-group">
                <a href="${contextPath}/addCourse" class="list-group-item">Create Course</a>
                <a href="${contextPath}/editCourse" class="list-group-item">Edit Course</a>
                <a href="${contextPath}/addSurvey" class="list-group-item">Create Survey</a>
                <a href="${contextPath}/editSurvey" class="list-group-item">Edit Survey</a>
                <a href="${contextPath}/deleteSurvey" class="list-group-item">Delete Survey</a>
                <a href="${contextPath}/addVideo" class="list-group-item">Add Video</a>
                <a href="${contextPath}/editVideo" class="list-group-item active">Edit Video</a>
                <a href="${contextPath}/deleteVideo" class="list-group-item">Delete Video</a>
                <a href="${contextPath}/addEvaluation" class="list-group-item">Create Evaluation</a>
                <a href="${contextPath}/editEvaluation" class="list-group-item">Edit Evaluation</a>
            </div>
        </div>
        <div class="col-md-9">
            <div >
                <h1>Edit Video</h1>

                <table id="edit-video-table" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Course Key</th>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${videos}" var="video">
                        <tr>
                            <input type="hidden" value="${video.title}" class="video-title"/>
                            <input type="hidden" value="${video.url}" class="video-link"/>
                            <input type="hidden" value="${video.videoKey}" class="video-key"/>
                            <input type="hidden" value="${video.courseKey}" class="video-course-key"/>
                            <input type="hidden" value="${video.courseTitle}" class="video-course-title"/>
                            <td>${video.title}</td>
                            <td>${video.courseTitle}</td>
                            <td><button class="btn-edit-video instructor-btn" data-toggle="modal" data-target="editVideoModal">Edit</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


                <div class="modal fade" id="editVideoModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog instructor-modal" role="document">
                        <div class="modal-header row">
                            <div class="col-sm-10">
                                <h3 class="modal-title">Edit Video <strong><span id="edit-video-span"></span></strong></h3>
                            </div>
                            <div class="col-sm-2">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </div>
                        <form:form class="form-horizontal" method="post"
                                   modelAttribute="video" action="editVideo">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="edit-video-title" class="col-sm-2 control-label">Video Name</label>
                                    <div class="col-sm-6">
                                        <form:input class="form-control" type="text" path="title" id="edit-video-title"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Select Course</label>
                                    <div class="col-sm-6" id="edit-video-course-key">
                                        <form:select class="form-control" path="courseKey" >
                                            <c:forEach items="${courses}" var="course">
                                                <form:option value="${course.courseKey}" label="${course.title}"/>
                                                <form:hidden value="${course.title}" path="courseTitle"/>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-video-link" class="col-sm-2 control-label">Video Link</label>
                                    <div class="col-sm-8">
                                        <form:input class="form-control" type="text" path="url" id="edit-video-link"/>
                                    </div>
                                </div>
                                <form:hidden path="videoKey" value="null" id="edit-video-key"/>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="instructor-btn-secondary" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="instructor-btn">Save</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/instructorVideo.js"></script>
</html>

