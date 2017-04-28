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
    <script src="${contextPath}/resources/js/instructor.js"></script>
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
                <a href="${contextPath}/instructor" class="list-group-item active">Instructor Home</a>
            </div>
        </div>
        <div class="col-md-9">
            <div class="ps-feature-content">
                <div class="row ps-feature">
                    <div class="ps-feature-info row">
                        <div class="col-md-9">
                        <h1>Courses Created By Me<hr></h1>
                        </div>
                        <div class="col-md-3 right">
                            <button class="instructor-btn-primary" id="btn-add-course">Create Course</button>
                        </div>
                        <br>
                    </div>

                    <div class="row ps-text-content">
                        <input type="hidden" value="${courses}">
                        <c:forEach items="${courses}" var="course">
                            <!-- Start Course -->
                            <div class="col-lg-4">
                                <div class="ps-well">
                                    <!-- Course Title -->
                                    <h2 class="ps-feature-info-header"> ${course.title}<hr> </h2>
                                    <img class="img-responsive course-img" src="http://placehold.it/900x300" alt="">
                                    <h3 class="ps-feature-info-header"> Course Description <hr></h3>
                                    <!-- Course Description-->
                                    <p class="ps-feature-preview">${course.description}</p>
                                    <div class="center">
                                        <a class="instructor-btn" href="${contextPath}/instructor/${course.courseKey}">Enter</a>
                                    </div>
                                </div>
                            </div>
                            <!-- End course -->
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <%--Add Course Modal--%>
        <div class="modal fade" id="addCourseModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog instructor-modal" role="document">
                <div class="modal-header row">
                    <div class="col-sm-10">
                        <h3 class="modal-title">Create Course</h3>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
                <form:form class="form-horizontal" method="post"
                           modelAttribute="course" action="instructor/addCourse">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="add-course-title" class="col-sm-2 control-label">Title</label>
                            <div class="col-sm-6">
                                <form:input type="text" class="form-control form-input" id="add-course-title" path="title" placeholder="Course Title" />
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
                        <button type="submit" class="instructor-btn-primary">Create</button>
                    </div>

                </form:form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
