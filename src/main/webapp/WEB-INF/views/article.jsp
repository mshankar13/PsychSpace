<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<head>
    <title>PsychSpace</title>
    <%--Google sign in--%>
    <meta name="google-signin-client_id"
          content="268071146674-5jjt494svk74rt4jb5mu4pik8503qbph.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <%--Google fonts--%>
    <link href='http://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Maven Pro' rel='stylesheet'>
    <%--jQuery--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <%--Bootstrap--%>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css"
          rel="stylesheet"/>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <%--Customized--%>
    <script src="${contextPath}/resources/js/scrollreveal.js"></script>
    <script src="${contextPath}/resources/js/navbar.js"></script>
    <script src="${contextPath}/resources/js/article.js"></script>
    <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/ps-row-col.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
</head>

<body>
<div classs="navbar-wrapper">
    <%@include file="navbar.html" %>
</div>

<header class="ps-feature-header">
    <input type="hidden" id="psIsLoggedIn" value="${isLoggedIn}"/>
    <input type="hidden" id="psCurrentUser" value="${currentUserKey}"/>
    <div class="ps-feature-content">
        <div class="row ps-feature">
            <div class="ps-feature-info">
                <!-- Date/Time -->
                <p><span class="glyphicon glyphicon-time"></span> Posted ${article.date}</p>
                <input type="hidden" id="ps-input-liked" value="${isLiked}">
                <form:form method="post" modelAttribute="like" action="/article/${article.newsKey}/+1" class="left">
                    <form:hidden path="userKey" value="0"/>
                    <form:hidden path="articleKey" value="${article.newsKey}"/>
                    <form:hidden path="status" value="" id="liked"/>
                    <form:hidden path="likeKey" value="0"/>
                    <button id="btn-ps-feature-like" type="submit" class="glyphicon glyphicon-star"></button>
                    ${article.likesCount}
                </form:form>

                <!-- Title -->
                <h1>${article.title}
                    <hr>
                </h1>
                <!-- Author -->
                <div class="media">
                    <a class="pull-left" href="#"> <img class="media-object" src="http://placehold.it/64x64" alt="">
                    </a>
                    <div class="media-body">
                        <a class="button-instructor button fadein" href=""
                           id="button-instructor"><span>${articleAuthor}</span></a>
                    </div>
                </div>
                <br>
                <hr>
                <br>
            </div>
        </div>

        <!-- end article info -->
        <div class="row ps-text-content">
            <div class="col-lg-8 ps-col-left">
                <div class="ps-well">
                    <div class="center">
                        <img class="ps-feature-img img-responsive" src="http://placehold.it/900x300" alt="">
                    </div>
                    <!-- Post Content -->
                    <p>${article.content}</p>
                </div>
            </div>
            <!--end article-text -->
            <div class="col-lg-4 ps-col-right">
                <div class="ps-well">
                    <div class="left">
                        <h2>Featured News
                            <hr>
                        </h2>
                    </div>
                    <br>
                    <!-- Start Featured Article -->
                    <div class="left">
                        <h3 class="ps-feature-info-header">${featured.title}
                            <hr>
                        </h3>
                        <div class="center">
                            <img class="ps-feature-img img-responsive" src="http://placehold.it/900x300" alt="">
                            <a class="button fadein btn-read-more" id="button-read" href="/article/${featured.newsKey}"><span>Read More</span></a>
                        </div>
                    </div>
                    <br>
                    <!--End Featured Article -->
                </div>
            </div>
        </div>
        <div class="row ps-text-content"></div>
        <!-- end article-content -->
        <!-- end row article -->
        <div class="row ps-text-content">
            <h2>Comments
                <hr>
            </h2>
            <br>
            <div id="psLeaveComment" class="col-lg ps-well">
                <div class="left">
                    <h4>Leave a Comment:</h4>
                    <form:form class="form-horizontal" method="post" modelAttribute="comment"
                               action="/article/${article.newsKey}">
                        <form:hidden path="commentKey" value="0"/>
                        <form:hidden path="username" value="0"/>
                        <form:hidden path="newsKey" value="0"/>
                        <form:hidden path="date" value="0"/>
                        <form:hidden path="state" value="add"/>
                        <form:textarea class="form-control" id="article-comment-create" rows="3" path="content"/>
                        <input type="hidden" id="psCommentCreator" value="${comment.username}">
                        <div class="right">
                            <button id="btn-comment-post" type="submit" class="btn-comment btn btn-primary">Submit
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
            <!-- Comment -->
            <c:forEach items="${commentList}" var="articleComment">
                <div class="media ps-comment">
                    <input type="hidden" id="commentCreator" value="${articleComment.username}">
                    <a class="pull-left" href="#"> <img class="media-object" src="http://placehold.it/64x64" alt=""></a>
                    <div class="media-body">
                        <!-- Comment Author and Date Posted -->
                        <h4>${articleComment.username}
                            <small>${articleComment.date}</small>
                        </h4>
                        <hr>
                        <!-- Comment Content -->
                        <p class="comment-text">${articleComment.content}</p>
                        <!-- Comment Buttons (Edit/Delete OR Like) -->
                        <div class="right">
                            <button type="button" class="btn-comment btn btn-primary btn-comment-delete">Delete</button>
                            <button type="button" class="btn-comment btn btn-primary btn-comment-edit">Edit</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <!-- End Comment -->
        </div>


        <!-- Social -->
        <!-- Edit Modal-->
        <div class="modal ps-modal-type-comment fade" id="edit-comment-modal" role="dialog">
            <div class="ps-modal-dialog">
                <!-- Modal content-->
                <div class="ps-modal-content">
                    <div class="ps-modal-header">
                        <button type="button" class="ps-type-close close ps-modal-close" data-dismiss="modal">
                            <h1>X</h1></button>
                        <div class="left">
                            <h2 class="ps-feature-info-header"> Edit Comment
                                <hr>
                            </h2>
                        </div>
                    </div>
                    <form:form method="post" action="/article/${article.newsKey}" modelAttribute="comment">
                        <div class="ps-modal-body">
                            <div class="form-group">
                                <form:hidden path="state" value="edit"/>
                                <form:hidden path="commentKey" value="0" id="edit-comment-modal-key"/>
                                <form:textarea id="edit-comment-content" path="content" class="form-control" rows="3"
                                               autofocus="true"/>
                                <form:hidden path="username" value="0"/>
                                <form:hidden path="newsKey" value="0"/>
                                <form:hidden path="date" value="0"/>
                            </div>
                        </div>
                        <div class="ps-modal-footer right">
                            <button type="button" class="ps-type-close btn btn-default" data-dismiss="modal">Close
                            </button>
                            <button id="btn-comment-edit" type="submit"
                                    class="ps-type-close btn-comment btn btn-primary">Save
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <!-- End Edit Modal -->


        <!-- Delete Modal -->
        <div class="modal ps-modal-type-comment fade" id="delete-comment-modal" role="dialog">
            <div class="ps-modal-dialog">
                <!-- Modal content-->
                <div class="ps-modal-content">
                    <div class="ps-modal-header">
                        <button type="button" class="ps-type-close close ps-modal-close" data-dismiss="modal">
                            <h1>X</h1></button>
                        <div class="left">
                            <h2 class="ps-feature-info-header"> Delete Comment
                                <hr>
                            </h2>
                        </div>
                    </div>

                    <form:form method="post" action="/article/${article.newsKey}" modelAttribute="comment">
                        <div class="ps-modal-body">
                            <form:hidden path="state" value="delete"/>
                            <form:hidden path="commentKey" value="0" id="delete-comment-modal-key"/>
                            <form:hidden path="username" value="0"/>
                            <form:hidden path="newsKey" value="0"/>
                            <form:hidden path="date" value="0"/>
                            <form:hidden path="content"/>
                            <h3>Are you sure you want to delete comment: <span id="delete-comment-modal-span"></span>
                            </h3>
                        </div>
                        <div class="ps-modal-footer right">
                            <button type="button" class="ps-type-close btn-comment btn btn-primary"
                                    data-dismiss="modal">No
                            </button>
                            <button id="btn-comment-update" type="submit"
                                    class="ps-type-close btn-comment btn btn-primary">Delete
                            </button>
                        </div>
                    </form:form>
                </div>

            </div>
        </div>
        <!-- End Delete Modal -->
    </div>
    <!-- article-content-->
</header>


<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/animations.js"></script>
</body>

</html>