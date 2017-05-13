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
    <script src="${contextPath}/resources/js/settings.js"></script>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
    <link href="${contextPath}/resources/css/instructor.css" rel="stylesheet">
</head>
<body>
<div class="navbar-wrapper">
    <jsp:include page="navbar.jsp" />
</div>
<div class="wrapper">
    <div class="row">
        <div class="col-md-3">
            <%--Side bar--%>
            <h3>Profile</h3>
            <div class="list-group">
                <a href="${contextPath}/application" class="list-group-item">My Application</a>
                <a href="${contextPath}/settings" class="list-group-item active">Account Settings</a>
            </div>
        </div>
        <div class="col-md-9">
            <input type="hidden" value="${currUser.userKey}" id="user-key">
            <button type="submit" class="ps-btn-primary" id="delete-account-btn">Delete My Account</button>
        </div>

        <%--Delete account modal--%>
        <div class="modal fade" id="deleteAccountModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-header row">
                    <div class="col-sm-10">
                        <h3 class="modal-title">Delete Confirmation</h3>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
                <form:form class="form-horizontal" method="POST"
                           modelAttribute="user" action="/settings/deleteAccount">
                    <div class="modal-body">
                        <p>Are you sure you want to delete your account?</p>
                        <form:hidden path="userKey" value="" id="set-user-key"/>
                        <form:hidden path="email" value="" />
                        <form:hidden path="firstName" value="" />
                        <form:hidden path="lastName" value="" />
                        <form:hidden path="role" value="" />
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="ps-btn" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="ps-btn-primary">Delete</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
