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
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <link href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <%--Bootstrap--%>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet"/>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <%--Customized--%>
    <link href="${contextPath}/resources/css/admin.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/adminEditArticle.js"></script>
</head>
<body>

<div id="wrapper">

    <%@include file="admin-sidebar.html" %>

    <div id="page-content-wrapper">
        <h1>Edit Article</h1>
        <form:form method="get" action="admin_editArticle" modelAttribute="news">
            <table id="edit-article-table" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Date</th>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${newsList}" var="news">
                    <tr>
                        <form:hidden value="${news.newsKey}" path="newsKey"/>
                        <form:hidden value="${news.title}" path="title"/>
                        <form:hidden value="${news.date}" path="date"/>
                        <form:hidden value="${news.content}" path="content"/>
                        <form:hidden value="${news.author}" path="author"/>
                        <form:hidden value="${news.likesCount}" path="likesCount"/>
                        <td>${news.title}</td>
                        <td>${news.date}</td>
                        <td><div class="admin-btn btn-edit-article">Edit</div></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form:form>

    </div>
</div>

<%--edit modal--%>
<div class="modal fade" id="editArticleModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header row">
                <div class="col-sm-10">
                    <h3 class="modal-title">Edit <strong><span id="edit-article-title"></span></strong></h3>
                </div>
                <div class="col-sm-2">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
            <form:form class="form-horizontal" method="post"
                       modelAttribute="news" action="admin_editArticle">
            <div class="modal-body">

                    <div class="form-group">
                        <label for="news-title">News Title</label>
                        <form:input type="text" class="form-control" id="news-title" path="title" />
                    </div>
                    <div class="form-group">
                        <label for="author">Author</label>
                        <form:input type="text" class="form-control" id="edit-author" path="author" />
                    </div>
                    <div class="form-group row">
                        <label for="date" class="col-2 col-form-label">Date</label>
                        <div class="col-10">
                            <form:input class="form-control" type="text" path="date" id="edit-date" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-content">News Content</label>
                        <form:textarea class="form-control" id="edit-content" rows="10" path="content" />
                    </div>
                    <form:hidden path="likesCount" id="likesCount"/>
                    <form:hidden path="newsKey" id="key"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="admin-btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" class="admin-btn" id="btn-edit-article-save">Save</button>
            </div>
            </form:form>
        </div>
    </div>
</div>

<%--edit error modal--%>
<div class="modal fade" id="deleteArticleErrorModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header row">
                <div class="col-sm-8">
                    <h3 class="modal-title">Oops</h3>
                </div>
                <div class="col-sm-4">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
            <div class="modal-body">
                Something went wrong :/
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>

