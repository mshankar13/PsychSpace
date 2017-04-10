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
    <link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet"/>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <%--Customized--%>
</head>
<body>
    <h2>Add News</h2>
    <%--TODO: check form--%>
    <form:form class="form-horizontal" method="post"
               modelAttribute="news" action="${addNews}">
        <div class="form-group">
            <label for="news-title">News Title</label>
            <form:input type="text" class="form-control" id="news-title" value="${news.title}" placeholder="News Title" />
        </div>
        <div class="form-group">
            <label for="author">Author</label>
            <form:input type="text" class="form-control" id="author" value="${news.title}" placeholder="Author" />
        </div>
        <div class="form-group row">
            <label for="date" class="col-2 col-form-label">Date</label>
            <div class="col-10">
                <form:input class="form-control" type="date" value="${news.date}" id="date" />
            </div>
        </div>
        <div class="form-group">
            <label for="news-content">News Content</label>
            <form:textarea class="form-control" id="news-content" rows="10" value="${news.content}" />
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
    </form:form>
</body>
</html>
