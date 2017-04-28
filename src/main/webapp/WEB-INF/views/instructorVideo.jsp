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
                <a href="${contextPath}/instructor" class="list-group-item">Instructor home</a>
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
                    <li class="active"><a href="#" id="videos-a">Videos</a></li>
                    <li><a href="#" id="evaluation-a">Evaluations</a></li>
                </ul>
            </div>

            <div>
                <div class="right">
                    <button class="instructor-btn" id="btn-add-video">Add Video</button>
                </div>
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
                            <td><button class="btn-edit-video instructor-btn-primary" data-toggle="modal" data-target="editVideoModal">Edit</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <%--< Add video modal --%>
                <div class="modal fade" id="addVideoModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog instructor-modal" role="document">
                        <div class="modal-header row">
                            <div class="col-sm-10">
                                <h3 class="modal-title">Add Video</h3>
                            </div>
                            <div class="col-sm-2">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </div>
                        <form:form class="form-horizontal" method="post"
                                   modelAttribute="video" action="/instructor/${courseKey}/videos/add">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="edit-video-title" class="col-sm-2 control-label">Video Name</label>
                                    <div class="col-sm-6">
                                        <form:input class="form-control" type="text" path="title" id="edit-video-title"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="edit-video-link" class="col-sm-2 control-label">Video Link</label>
                                    <div class="col-sm-8">
                                        <form:input class="form-control" type="text" path="url" id="edit-video-link"/>
                                    </div>
                                </div>
                                <form:hidden path="videoKey" value="null" />
                                <form:hidden path="courseKey" value="${courseKey}" />
                                <form:hidden path="courseTitle" value="null" />
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="instructor-btn-secondary" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="instructor-btn-primary">Add</button>
                            </div>
                        </form:form>
                    </div>
                </div>

                <%--< Edit video modal --%>
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
                                    <label for="edit-video-link" class="col-sm-2 control-label">Video Link</label>
                                    <div class="col-sm-8">
                                        <form:input class="form-control" type="text" path="url" id="edit-video-link"/>
                                    </div>
                                </div>
                                <form:hidden path="videoKey" value="null" id="edit-video-key"/>
                                <form:hidden path="courseKey" value="${courseKey}" />
                                <form:hidden path="courseTitle" value="" />
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="instructor-btn-secondary" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="instructor-btn-primary">Save</button>
                            </div>
                        </form:form>
                    </div>
                </div>

                <%--Delete video modal--%>
                <div class="modal fade" id="deleteVideoModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog instructor-modal" role="document">
                        <div class="modal-header row">
                            <div class="col-sm-10">
                                <h3 class="modal-title">Delete Video <strong><span id="delete-video-span"></span></strong>?</h3>
                            </div>
                            <div class="col-sm-2">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </div>
                        <form:form class="form-horizontal" method="post"
                                   modelAttribute="video" action="deleteVideo">
                            <div class="modal-body">
                                <p>Are you sure you want to delete?</p>
                                <form:hidden path="videoKey" value="null" id="delete-video-key"/>
                                <form:hidden path="courseKey" value="null" id="delete-video-course-key"/>
                                <form:hidden path="courseTitle" value="null" id="delete-video-course-title"/>
                                <form:hidden path="url" value="null" id="delete-video-link"/>
                                <form:hidden path="title" value="null" id="delete-video-title"/>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="instructor-btn-secondary" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="instructor-btn-primary">Delete</button>
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
