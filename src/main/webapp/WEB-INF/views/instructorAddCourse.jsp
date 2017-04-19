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
    <link href="${contextPath}/resources/css/instructor.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">

    <%@include file="instructor-sidebar.html" %>

    <%--TODO: Complete this--%>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <h1>Add Course</h1>
            <form:form class="form-horizontal" method="post"
                       modelAttribute="course" action="addCourse">
                <div class="form-group">
                    <label for="course-name">Course Title</label>
                    <div class="col-sm-10">
                        <form:input type="text" class="form-control" id="course-name" path="name" placeholder="Course Title" />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="date" class="col-2 col-form-label">Enroll Date</label>
                    <div class="col-10">
                        <form:input class="form-control" type="date" path="date"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="date" class="col-2 col-form-label">Start Date</label>
                    <div class="col-10">
                        <form:input class="form-control" type="date" path="startDate" />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="date" class="col-2 col-form-label">End Date</label>
                    <div class="col-10">
                        <form:input class="form-control" type="date" path="endDate" />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="date" class="col-2 col-form-label">Drop Deadline</label>
                    <div class="col-10">
                        <form:input class="form-control" type="date" path="deadline" />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="date" class="col-2 col-form-label">Course Size</label>
                    <div class="col-10">
                        <form:input class="form-control" type="date" path="size" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="course-description">Course Description</label>
                    <form:textarea class="form-control" id="course-description" rows="10" path="description" />
                </div>
                <form:hidden path="courseKey" value="null"/>
                <form:hidden path="instructor" value="null"/>
                <button type="submit" class="btn btn-primary">Create</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
