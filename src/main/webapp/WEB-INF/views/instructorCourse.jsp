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
    <script type="text/javascript" src="${contextPath}/resources/js/jquery.ui.datepicker.validation.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
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
        <%--Left side bar--%>
        <div class="col-md-2">
            <div class="list-group">
                <a href="${contextPath}/instructor" class="list-group-item">Instructor Home</a>
            </div>
        </div>

        <%--Main Content--%>
        <div class="col-md-10">
            <%--Top: select course option and create course buttn--%>
            <div class="row div-top">
                <input type="hidden" value="${currentCourse.courseKey}" id="current-course-key">
                <input type="hidden" value="${currentCourse.title}" id="current-course-title">
                <input type="hidden" value="${currentCourse.description}" id="current-course-description">
                <input type="hidden" value="${currentCourse.startDate}" id="current-course-start-date">
                <input type="hidden" value="${currentCourse.endDate}" id="current-course-end-date">
                <input type="hidden" value="${currentCourse.enrollDate}" id="current-course-enroll-date">
                <input type="hidden" value="${currentCourse.dropDate}" id="current-course-drop-date">
                <input type="hidden" value="${currentCourse.status}" id="current-course-status">
                <input type="hidden" value="${currentCourse.capacity}" id="current-course-capacity">
                <div class="col-md-9">
                    <label for="select-course" class="col-md-3 control-label">Select Course</label>
                    <select class="col-md-6" id="select-course">
                        <c:forEach items="${courses}" var="course">
                            <c:choose>
                                <c:when test="${course.courseKey ==  currentCourse.courseKey}">
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

            <%--Tabs--%>
            <div class="tab">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#" id="course-a">Course</a></li>
                    <li><a href="#" id="survey-a">Survey</a></li>
                    <li><a href="#" id="videos-a">Videos</a></li>
                    <li><a href="#" id="evaluation-a">Due Dates</a></li>
                </ul>
            </div>

            <%--Course Content--%>
            <div id="edit-course-div">
                <h1 id="course-title"></h1>
                <form:form class="form-horizontal" id="edit-course-form" method="post"
                           modelAttribute="course" action="/instructor/${currentCourse.courseKey}">
                    <div class="form-group">
                        <label for="edit-course-enroll-date" class="col-sm-2 control-label">Enroll Date</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="text" path="enrollDate" id="edit-course-enroll-date"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-course-start-date" class="col-sm-2 control-label">Start Date</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="text" path="startDate" id="edit-course-start-date" name="startDate"/>
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
                    <div class="form-group row">
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
                    <form:hidden path="userKey" value="null"/>
                    <form:hidden path="courseKey" value="null"/>
                    <form:hidden path="instructor" value="null"/>
                    <form:hidden path="currSize" value="0" />
                    <button type="submit" class="instructor-btn-primary">Save</button>
                </form:form>
            </div>

            <%--Add Course Modal--%>
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
                               modelAttribute="course" action="/instructor/addCourse">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="add-course-title" class="col-sm-2 control-label">Title</label>
                                <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="add-course-title" path="title" placeholder="Course Title" />
                                </div>
                            </div>
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
                            <button type="submit" class="instructor-btn-primary">Create</button>
                        </div>

                    </form:form>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/instructorCourse.js"></script>
</html>
