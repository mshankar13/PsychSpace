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
            <%--Side bar--%>
            <h3>Profile</h3>
            <div class="list-group">
                <a href="${contextPath}/application" class="list-group-item active">My Application</a>
                <a href="${contextPath}/settings" class="list-group-item">Account Settings</a>
            </div>
        </div>
        <div class="col-md-9">

            <c:set var="userRole" value="${user.role}" />
            <c:set var="instructorApplicant" value="applyingInstructor" />
            <c:choose>
                <c:when test="${userRole == instructorApplicant}">
                    <p class="ps-notice">You instructor application is waiting to be viewed.</p>
                </c:when>
                <c:when test="${userRole == 'instructor'}">
                    <p class="ps-notice">Congratulations! You are currently an instructor.</p>
                </c:when>
                <c:otherwise>
                    <form:form method="post" action="/application/submit" modelAttribute="user">
                        <form:hidden path="userKey" value="${user.userKey}" />
                        <form:hidden path="email" value="${user.email}" />
                        <form:hidden path="firstName" value="${user.firstName}" />
                        <form:hidden path="lastName" value="${user.lastName}" />
                        <h2>I want to apply to be </h2>
                        <form:select path="role">
                            <form:option value="instructorApplicant">an instructor</form:option>
                            <form:option value="instructorApplicant">an admin</form:option>
                        </form:select>
                        <div class="right">
                            <button type = "submit" class="ps-btn-primary">Apply</button>
                        </div>
                    </form:form>
                </c:otherwise>
            </c:choose>
            <br>
        </div>

    </div>
</div>
</body>
</html>
