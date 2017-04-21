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
    <script src="${contextPath}/resources/js/scrollreveal.js"></script>
    <script src="${contextPath}/resources/js/navbar.js"></script>
    <script src="${contextPath}/resources/js/instructorCourse.js"></script>
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
                <a href="${contextPath}/editCourse" class="list-group-item active">Edit Course</a>
                <a href="${contextPath}/addSurvey" class="list-group-item">Create Survey</a>
                <a href="${contextPath}/editSurvey" class="list-group-item">Edit Survey</a>
                <a href="${contextPath}/addEvaluation" class="list-group-item">Create Evaluation</a>
                <a href="${contextPath}/editEvaluation" class="list-group-item">Edit Evaluation</a>
            </div>
        </div>
        <div class="col-md-9">
            <div >
                <h1>Edit Course</h1>

                <table id="edit-article-table" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Instructor</th>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${courses}" var="course">
                        <tr>
                            <input type="hidden" value="${course.userKey}" id="userKey"/>
                            <input type="hidden" value="${course.courseKey}" id="courseKey"/>
                            <input type="hidden" value="${course.title}" id="title"/>
                            <input type="hidden" value="${course.instructor}" id="instructor"/>
                            <input type="hidden" value="${course.description}" id="description"/>
                            <input type="hidden" value="${course.startDate}" id="startDate"/>
                            <input type="hidden" value="${course.endDate}" id="endDate"/>
                            <input type="hidden" value="${course.enrollDate}" id="enrollDate"/>
                            <input type="hidden" value="${course.dropDate}" id="dropDate"/>
                            <input type="hidden" value="${course.status}" id="status"/>
                            <input type="hidden" value="${course.currSize}" id="currSize"/>
                            <input type="hidden" value="${course.capacity}" id="capacity"/>
                            <td>${course.title}</td>
                            <td>${course.instructor}</td>
                            <td><button id="btn-edit-course" data-toggle="modal" data-target="editCourseModal">Edit</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


                <div class="modal fade" id="editCourseModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <%--<div class="modal-content">--%>
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
                            <form:form class="form-horizontal" method="post"
                                       modelAttribute="course" action="editCourse">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="edit-course-enroll-date" class="col-sm-2 control-label">Enroll Date</label>
                                        <div class="col-sm-6">
                                            <form:input class="form-control" type="text" path="enrollDate" id="edit-course-enroll-date"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-course-start-date" class="col-sm-2 control-label">Start Date</label>
                                        <div class="col-sm-6">
                                            <form:input class="form-control" type="text" path="startDate" id="edit-course-start-date"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-course-end-date" class="col-sm-2 control-label">End Date</label>
                                        <div class="col-sm-6">
                                            <form:input class="form-control" type="text" path="endDate" id="edit-course-end-date"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-course-drop-date" class="col-sm-2 control-label">Drop Deadline</label>
                                        <div class="col-sm-6">
                                            <form:input class="form-control" type="text" path="dropDate" id="edit-course-drop-date"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-course-capacity" class="col-sm-2 control-label">Capacity</label>
                                        <div class="col-sm-6">
                                            <form:input class="form-control" type="number" path="capacity" id="edit-course-capacity"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-course-status" class="col-sm-2 control-label">Status</label>
                                        <div class="col-sm-6">
                                            <form:select class="form-control" id="edit-course-status" path="status">
                                                <form:option value="open">Open</form:option>
                                                <form:option value="closed">Closed</form:option>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="edit-course-description" class="col-sm-2 control-label">Description</label>
                                        <div class="col-sm-8">
                                            <form:textarea class="form-control" id="edit-course-description" rows="10" path="description" />
                                        </div>
                                    </div>
                                    <form:hidden path="title" value="null" id="edit-course-title"/>
                                    <form:hidden path="userKey" value="null" id="edit-course-user-key"/>
                                    <form:hidden path="courseKey" value="null" id="edit-course-key"/>
                                    <form:hidden path="instructor" value="null" id="edit-course-instructor"/>
                                    <form:hidden path="currSize" value="" id="edit-course-currSize"/>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn-secondary" data-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Save</button>
                                </div>
                            </form:form>
                        </div>
                    <%--</div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
