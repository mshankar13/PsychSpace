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
                <a href="${contextPath}/addCourse" class="list-group-item active">Courses</a>
                <a href="${contextPath}/editCourse" class="list-group-item">Edit Course</a>
                <a href="${contextPath}/addSurvey" class="list-group-item">Create Survey</a>
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
            <div class="row div-top">
                <div class="col-md-9">
                    <label for="select-course" class="col-md-3 control-label">Select Course</label>
                    <select class="col-md-6" id="select-course">
                        <input type="hidden" value="${courses}" id="courses">
                        <%--<c:forEach items="${courses}" var="course">--%>
                            <%--<option class="select-course" value="${course.courseKey}">${course.title}</option>--%>
                        <%--</c:forEach>--%>
                    </select>
                </div>
                <div class="col-md-3">
                    <button class="instructor-btn" id="btn-add-course">Create Course</button>
                </div>
            </div>

            <div class="tab">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#">Course</a></li>
                    <li><a href="#">Survey</a></li>
                    <li><a href="#">Video</a></li>
                    <li><a href="#">Evaluation</a></li>
                </ul>
            </div>

            <div id="edit-course-div">
                <h1>Edit Course</h1>
                <form:form class="form-horizontal" method="post"
                           modelAttribute="course" action="addCourse">
                    <div class="form-group">
                        <label for="add-course-title" class="col-sm-2 control-label">Title</label>
                        <div class="col-sm-6">
                            <form:input type="text" class="form-control" id="add-course-title" path="title" placeholder="Course Title" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-course-enroll-date" class="col-sm-2 control-label">Enroll Date</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="text" path="enrollDate" id="add-course-enroll-date"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-course-start-date" class="col-sm-2 control-label">Start Date</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="text" path="startDate" id="add-course-start-date"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-course-end-date" class="col-sm-2 control-label">End Date</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="text" path="endDate" id="add-course-end-date"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-course-drop-date" class="col-sm-2 control-label">Drop Deadline</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="text" path="dropDate" id="add-course-drop-date"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="add-course-capacity" class="col-sm-2 control-label">Capacity</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="number" path="capacity" id="add-course-capacity"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-course-description" class="col-sm-2 control-label">Description</label>
                        <div class="col-sm-8">
                            <form:textarea class="form-control" id="add-course-description" rows="10" path="description" />
                        </div>
                    </div>
                    <form:hidden path="userKey" value="null"/>
                    <form:hidden path="courseKey" value="null"/>
                    <form:hidden path="instructor" value="null"/>
                    <form:hidden path="status" value="open" />
                    <form:hidden path="currSize" value="0" />
                    <button type="submit" class="instructor-btn">Create</button>
                </form:form>
            </div>


            <div class="modal fade" id="addCourseModal" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog instructor-modal" role="document">
                    <div class="modal-header row">
                        <div class="col-sm-10">
                            <h3 class="modal-title">Add Course</h3>
                        </div>
                        <div class="col-sm-2">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </div>
                    <form:form class="form-horizontal" method="post"
                               modelAttribute="course" action="addCourse">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="add-course-title" class="col-sm-2 control-label">Title</label>
                                <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="add-course-title" path="title" placeholder="Course Title" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-course-enroll-date" class="col-sm-2 control-label">Enroll Date</label>
                                <div class="col-sm-6">
                                    <form:input class="form-control" type="text" path="enrollDate" id="add-course-enroll-date"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-course-start-date" class="col-sm-2 control-label">Start Date</label>
                                <div class="col-sm-6">
                                    <form:input class="form-control" type="text" path="startDate" id="add-course-start-date"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-course-end-date" class="col-sm-2 control-label">End Date</label>
                                <div class="col-sm-6">
                                    <form:input class="form-control" type="text" path="endDate" id="add-course-end-date"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-course-drop-date" class="col-sm-2 control-label">Drop Deadline</label>
                                <div class="col-sm-6">
                                    <form:input class="form-control" type="text" path="dropDate" id="add-course-drop-date"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="add-course-capacity" class="col-sm-2 control-label">Capacity</label>
                                <div class="col-sm-6">
                                    <form:input class="form-control" type="number" path="capacity" id="add-course-capacity"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-course-description" class="col-sm-2 control-label">Description</label>
                                <div class="col-sm-8">
                                    <form:textarea class="form-control" id="add-course-description" rows="10" path="description" />
                                </div>
                            </div>
                            <form:hidden path="userKey" value="null"/>
                            <form:hidden path="courseKey" value="null"/>
                            <form:hidden path="instructor" value="null"/>
                            <form:hidden path="status" value="open" />
                            <form:hidden path="currSize" value="0" />
                        </div> <%--End of modal body--%>
                        <div class="modal-footer">
                            <button type="button" class="instructor-btn-secondary" data-dismiss="modal">Cancel</button>
                            <button type="submit" class="instructor-btn">Create</button>
                        </div>

                    </form:form>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
<script src="${contextPath}/resources/js/instructorCourse.js"></script>
<script>
    $("#add-course-enroll-date").datepicker();
    $("#add-course-start-date").datepicker();
    $("#add-course-end-date").datepicker();
    $("#add-course-drop-date").datepicker();
</script>
</html>
