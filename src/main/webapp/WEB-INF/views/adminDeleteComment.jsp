<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
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
    <link href="${contextPath}/resources/css/admin.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>

<div id="wrapper">

    <%@include file="admin-sidebar.html" %>
    <div id="page-content-wrapper">
        <h1>Delete Comment</h1>
        <form:form method="get" action="admin_deleteArticle" modelAttribute="articleList">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Article </th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${articleList}" var="article">
                    <tr>
                        <td>${article.title}</td>
                        <td><button type="submit">See Comments</button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form:form>
    </div>
</div>
</body>
</html>

