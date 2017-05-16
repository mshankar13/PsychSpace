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
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <%--Bootstrap--%>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css"
          rel="stylesheet"/>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <%--Customized--%>
    <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/ps-row-col.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
</head>

<body>
<div class="navbar-wrapper">
    <jsp:include page="navbar.jsp"/>
</div>

<header class="ps-feature-header">
    <div class="center page-banner">
        <img class="img-responsive" src="http://placehold.it/2000x500" alt="">
        <!-- Course Title -->
        <h1 class="absolute-text">${course.title}</h1>
    </div>
    <div class="ps-feature-content">
        <div class="row ps-feature">
            <div class="row ps-text-content">
                <div class="col-lg-2 ps-col-left">
                    <div class="ps-well">
                        <!-- Learn Sidebar -->
                        <%@include file="learnSidebar.jsp" %>
                    </div>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- Learn Navbar for Todos-->--%>
                    <jsp:include page="learn-navbar.html"/>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- Content -->--%>
                    <%-- Start Thread Content --%>
                    <input type="hidden" id="currentThreadKey" value="${thread.threadKey}">
                    <div class="ps-well">
                        <h1 id="currentThreadTitle">
                            ${thread.title}
                            <hr>
                        </h1>
                        <%--<c:choose>--%>
                        <%--<c:when test="${currUserKey == thread.userKey}">--%>
                        <div class="right">
                            <button type="button"
                                    class="btn-comment btn btn-primary btn-thread-delete">
                                Delete
                            </button>
                            <button type="button"
                                    class="btn-comment btn btn-primary btn-thread-edit">
                                Edit
                            </button>
                        </div>
                        <%--</c:when>--%>
                        <%--</c:choose>--%>
                        <div>
                            <td class="ps-table-row">
                                ${thread.date}
                            </td>
                            <td class="ps-table-row">
                                ${thread.inThreadName}
                            </td>
                        </div>
                        <br>
                        <div>
                            <p id="currentThreadContent">
                                ${thread.content}
                            </p>
                        </div>
                    </div>
                    <%-- End Thread Content --%>
                    <br>
                    <%--<!-- Start Comments -->--%>
                    <div class="ps-well">
                        <h2>Comments
                            <hr>
                        </h2>
                        <br>
                        <%--<!-- Leaving Comments -->--%>
                        <div id="psLeaveComment" class="col-lg ps-well">
                            <div id="psLeaveComment" class="col-lg ps-well">
                                <div class="left">
                                    <h4>Leave a Comment:</h4>
                                    <form:form class="form-horizontal" method="post" modelAttribute="comment"
                                               action="/learn/${courseKey}/forum/${thread.threadKey}/comment">
                                        <form:hidden path="commentKey" value="0"/>
                                        <form:hidden path="username" value="0"/>
                                        <form:hidden path="newsKey" value="${thread.courseKey}"/>
                                        <form:hidden path="date" value="0"/>
                                        <form:hidden path="state" value="add"/>
                                        <form:textarea class="form-control" id="thread-comment-create" rows="3"
                                                       path="content"/>
                                        <input type="hidden" id="psCommentCreator" value="${comment.username}">
                                        <div class="right">
                                            <button id="btn-comment-post" type="submit"
                                                    class="btn-comment btn btn-primary">Submit
                                            </button>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                            <%--<!-- Loading Comments -->--%>
                            <%--<!-- Comment -->--%>
                            <div id="ps-comment-section">
                                <c:forEach items="${comments}" var="threadComment">
                                    <c:set var="commentorUserKey" value="${threadComment.userKey}"/>
                                    <div class="media ps-comment">
                                        <!-- Comment creator key -->
                                        <input type="hidden" value="${threadComment.commentKey}">
                                        <a class="pull-left" href="#"> <img class="media-object"
                                                                            src="http://placehold.it/64x64"
                                                                            alt=""></a>
                                        <div class="media-body">
                                            <!-- Comment Author and Date Posted -->
                                            <h4>${threadComment.username}
                                                <small>${threadComment.date}</small>
                                            </h4>
                                            <hr>
                                            <!-- Comment Content -->
                                            <p class="comment-text">${threadComment.content}</p>
                                            <!-- Comment Buttons (Edit/Delete OR Like) -->
                                            <c:choose>
                                                <c:when test="${currUserKey == commentorUserKey}">
                                                    <div class="right">
                                                        <button type="button"
                                                                class="btn-comment btn btn-primary btn-comment-delete">
                                                            Delete
                                                        </button>
                                                        <button type="button"
                                                                class="btn-comment btn btn-primary btn-comment-edit">
                                                            Edit
                                                        </button>
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- End Comment -->
                            <%--<!-- End Comments -->--%>
                        </div>
                        <br>

                        <!-- Content End-->
                    </div>
                </div>
                <!-- End Learn Page -->
            </div>
        </div>

        <!-- Edit Comment Modal-->
        <div class="modal ps-modal-type-comment fade" id="edit-comment-modal" role="dialog">
            <div class="ps-modal-dialog">
                <!-- Modal content-->
                <div class="ps-modal-content">
                    <div class="ps-modal-header">
                        <button type="button" class="ps-type-close close ps-modal-close"
                                data-dismiss="modal">
                            <h1>X</h1></button>
                        <div class="left">
                            <h2 class="ps-feature-info-header"> Edit Comment
                                <hr>
                            </h2>
                        </div>
                    </div>
                    <form:form method="post"
                               action="/learn/${courseKey}/forum/${thread.threadKey}/comment"
                               modelAttribute="comment">
                        <div class="ps-modal-body">
                            <div class="form-group">
                                <form:hidden path="state" value="edit"/>
                                <form:hidden path="commentKey" value="0" id="edit-comment-modal-key"/>
                                <form:textarea id="edit-comment-content" path="content"
                                               class="form-control"
                                               rows="3"
                                               autofocus="true"/>
                                <form:hidden path="username" value="0"/>
                                <form:hidden path="newsKey" value="0"/>
                                <form:hidden path="userKey" value="0"/>
                                <form:hidden path="date" value="0"/>
                            </div>
                        </div>
                        <div class="ps-modal-footer right">
                            <button type="button" class="ps-type-close btn btn-default"
                                    data-dismiss="modal">Close
                            </button>
                            <button type="submit"
                                    class="ps-type-close btn-comment btn btn-primary">Save
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <br>
        <!-- End Edit Comment Modal -->

        <!-- Delete Comment Modal -->
        <div class="modal ps-modal-type-comment fade" id="delete-comment-modal" role="dialog">
            <div class="ps-modal-dialog">
                <!-- Modal content-->
                <div class="ps-modal-content">
                    <div class="ps-modal-header">
                        <button type="button" class="ps-type-close close ps-modal-close"
                                data-dismiss="modal">
                            <h1>X</h1></button>
                        <div class="left">
                            <h2 class="ps-feature-info-header"> Delete Comment
                                <hr>
                            </h2>
                        </div>
                    </div>

                    <form:form method="post"
                               action="/learn/${courseKey}/forum/${thread.threadKey}/comment"
                               modelAttribute="comment">
                        <div class="ps-modal-body">
                            <form:hidden path="state" value="delete"/>
                            <form:hidden path="commentKey" value="0" id="delete-comment-modal-key"/>
                            <form:hidden path="username" value="0"/>
                            <form:hidden path="newsKey" value="0"/>
                            <form:hidden path="userKey" value="0"/>
                            <form:hidden path="date" value="0"/>
                            <form:hidden path="content" value=""/>
                            <h3>Are you sure you want to delete comment: <span
                                    id="delete-comment-modal-span"></span>
                            </h3>
                        </div>
                        <div class="ps-modal-footer right">
                            <button type="button" class="ps-type-close btn-comment btn btn-primary"
                                    data-dismiss="modal">No
                            </button>
                            <button type="submit"
                                    class="ps-type-close btn-comment btn btn-primary">Delete
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <br>
        <!-- End Delete Comment Modal

        <!-- Delete Thread Modal -->
        <div class="modal ps-modal-type-comment fade" id="delete-thread-modal" role="dialog">
            <div class="ps-modal-dialog">
                <!-- Modal content-->
                <div class="ps-modal-content">
                    <div class="ps-modal-header">
                        <button type="button" class="ps-type-close close ps-modal-close"
                                data-dismiss="modal">
                            <h1>X</h1></button>
                        <div class="left">
                            <h2 class="ps-feature-info-header"> Delete Thread
                                <hr>
                            </h2>
                        </div>
                    </div>
                    <form:form method="post"
                               action="/learn/${courseKey}/forum/${thread.threadKey}/deleteThread"
                               modelAttribute="thread">
                        <div class="ps-modal-body">
                            <form:hidden path="threadKey" value="0" id="delete-thread-modal-key"/>
                            <form:hidden path="courseKey" value="${thread.courseKey}"/>
                            <form:hidden path="userKey" value="0"/>
                            <form:hidden path="inThreadName" value=""/>
                            <form:hidden path="date" value="0"/>
                            <form:hidden path="title" value="${thread.title}"/>
                            <form:hidden path="content" value=""/>

                            <h3>Are you sure you want to delete this thread: <span
                                    id="delete-thread-modal-span"></span>
                            </h3>
                        </div>
                        <div class="ps-modal-footer right">
                            <button type="button" class="ps-type-close btn-comment btn btn-primary"
                                    data-dismiss="modal">No
                            </button>
                            <button type="submit"
                                    class="ps-type-close btn-comment btn btn-primary">Delete
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <br>
        <!-- End Delete Thread Modal -->

        <!-- Edit Thread Modal-->
        <div class="modal ps-modal-type-comment fade" id="edit-thread-modal" role="dialog">
            <div class="ps-modal-dialog">
                <!-- Modal content-->
                <div class="ps-modal-content">
                    <div class="ps-modal-header">
                        <button type="button" class="ps-type-close close ps-modal-close"
                                data-dismiss="modal">
                            <h1>X</h1></button>
                        <div class="left">
                            <h2 class="ps-feature-info-header"> Edit Thread
                                <hr>
                            </h2>
                        </div>
                    </div>
                    <form:form method="post"
                               action="/learn/${courseKey}/forum/${thread.threadKey}/editThread"
                               modelAttribute="thread">
                        <div class="ps-modal-body">
                            <form:hidden path="threadKey" value="0" id="edit-thread-modal-key"/>
                            <form:hidden path="courseKey" value="${thread.courseKey}"/>
                            <form:hidden path="title" value="${thread.title}"/>
                            <form:hidden path="userKey" value="0"/>
                            <form:hidden path="date" value="0"/>

                            <div class="left">
                                <h4>Thread Content</h4>
                                <form:textarea id="forum-thread-content" path="content"
                                               class="form-control"
                                               rows="5"
                                               autofocus="true"/>
                            </div>
                            <br>
                            <div class="left">
                                    <%-- Hidden input for anonymity --%>
                                <input type="hidden" id="displayFirstName" value=${currUser.firstName}>
                                <input type="hidden" id="displayLastName" value=${currUser.lastName}>
                                <h4>Display Name or Stay Anonymous?</h4>
                                <button type="button" id="displayNameFull"
                                        class="btn-comment btn btn-primary">Display
                                    Name
                                </button>
                                <button type="button" id="displayNameHidden"
                                        class="btn-comment btn btn-primary">Stay
                                    Anonymous
                                </button>
                                <form:hidden path="inThreadName" id="edit-thread-display-name"
                                             value="${survey.inThreadName}"/>
                            </div>
                        </div>
                        <div class="ps-modal-footer right">
                            <button type="button" class="ps-type-close btn btn-default"
                                    data-dismiss="modal">Close
                            </button>
                            <button type="submit"
                                    class="ps-type-close btn-comment btn btn-primary">Save
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <!-- End Edit Thread Modal -->


    </div>

</header>

<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/learnNavbar.js"></script>
<script src="${contextPath}/resources/js/thread.js"></script>
<script src="${contextPath}/resources/js/animations.js"></script>
<script src="${contextPath}/resources/js/scrollreveal.js"></script>
</body>
</html>