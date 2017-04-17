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
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <%--Bootstrap--%>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <%--Customized--%>
    <link href="${contextPath}/resources/css/admin.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">

</head>
<body>
<div id="wrapper">

    <%@include file="admin-sidebar.html" %>

    <div id="page-content-wrapper">
        <div class="container-fluid">
        <h1>Add Article</h1>
        <form:form class="form-horizontal" method="post"
                   modelAttribute="news" action="admin_addArticle">
            <div class="form-group">
                <label for="news-title">News Title</label>
                <form:input type="text" class="form-control" id="news-title" path="title" placeholder="News Title" />
            </div>
            <div class="form-group">
                <label for="author">Author</label>
                <form:input type="text" class="form-control" id="author" path="author" placeholder="Author" />
            </div>
            <div class="form-group row">
                <label for="date" class="col-2 col-form-label">Date</label>
                <div class="col-10">
                    <form:input class="form-control" type="text" path="date" id="add-article-date"/>
                </div>
            </div>
            <div class="form-group">
                <label for="news-content">News Content</label>
                <form:textarea class="form-control" id="news-content" rows="10" path="content" />
            </div>
            <form:hidden path="likesCount" value="0"/>
            <form:hidden path="newsKey" value="null"/>
            <button type="submit" class="btn btn-primary btn-add-article">Add</button>
        </form:form>
        </div>
    </div>
</div>
<script>
    $("#add-article-date").datepicker();
    //        $("#add-article-date").datepicker("setDate", new Date());
</script>
</body>
</html>
