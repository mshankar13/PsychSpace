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
    <%--Bootstrap--%>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <%--Customized--%>
    <script src="${contextPath}/resources/js/scrollreveal.js"></script>
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
            <%@include file="instructor-sidebar.html" %>
        </div>
        <div class="col-md-9">
            <div >
                <h1>Create Course</h1>
                <form:form class="form-horizontal" method="post"
                           modelAttribute="course" action="addCourse">
                    <div class="form-group">
                        <label for="course-title" class="col-sm-2 control-label">Course Title</label>
                        <div class="col-sm-6">
                            <form:input type="text" class="form-control" id="course-title" path="name" placeholder="Course Title" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-course-enroll-date" class="col-sm-2 control-label">Enroll Date</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="date" path="enrollDate" id="add-course-enroll-date"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-course-start-date" class="col-sm-2 control-label">Start Date</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="date" path="startDate" id="add-course-start-date"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-course-end-date" class="col-sm-2 control-label">End Date</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="date" path="endDate" id="add-course-end-date"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-course-drop-date" class="col-sm-2 control-label">Drop Deadline</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="date" path="dropDate" id="add-course-drop-date"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="add-course-capacity" class="col-sm-2 control-label">Course Capacity</label>
                        <div class="col-sm-6">
                            <form:input class="form-control" type="date" path="capacity" id="add-course-capacity"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-course-description" class="col-sm-3">Course Description</label>
                        <form:textarea class="form-control" id="add-course-description" rows="10" path="description" />
                    </div>
                    <form:hidden path="userKey" value="null"/>
                    <form:hidden path="courseKey" value="null"/>
                    <form:hidden path="instructor" value="null"/>
                    <form:hidden path="status" value="open" />
                    <form:hidden path="currSize" value="0" />
                    <button type="submit" class="btn btn-primary">Create</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
