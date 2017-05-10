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
    <link href='${contextPath}/resources/css/animations.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/ps-row-col.css' rel='stylesheet'>
    <link href='${contextPath}/resources/css/style.css' rel='stylesheet'>
</head>

<body>
<div class="navbar-wrapper">
    <%@include file="navbar.html" %>
</div>

<header class="ps-feature-header">
    <div class="center page-banner">
        <img class="img-responsive" src="http://placehold.it/2000x500" alt="">
        <!-- Course Title -->
        <h1 class="absolute-text">[Course Title]</h1>
    </div>
    <div class="ps-feature-content">
        <div class="row ps-feature">
            <div class="row ps-text-content">
                <div class="col-lg-2 ps-col-left">
                    <div class="ps-well">
                        <!-- Learn Sidebar for Todos-->
                        <h2>To Do:<hr></h2>
                        <c:choose>
                            <c:when test="${hasEvaluation == true and hasSurvey == true and hasHabit == true}">
                                <h3>Good Job! You currently have no todos.</h3>
                            </c:when>
                            <c:otherwise>
                                <!-- Set Goal -->
                                <c:if test="${hasGoal == false}">
                                    <div class="center">
                                        <a class="button-enroll button fadein" id="button-goal"
                                           href="${contextPath}/learn/${courseKey}/survey"><span>Complete Survey</span>
                                        </a>
                                    </div>
                                </c:if>
                                <!-- Do Daily Evaluation -->
                                <c:if test="${hasEvaluation == false}">
                                    <div class="center">
                                        <a class="button-enroll button fadein" id="button-evaluation"
                                           href="${contextPath}/learn/${courseKey}/evaluation"><span>Daily Evaluation</span>
                                        </a>
                                    </div>
                                </c:if>
                                <!-- Do Survey -->
                                <c:if test="${hasEvaluation == false}">
                                    <div class="center">
                                        <a class="button-enroll button fadein" id="button-survey"
                                           href="${contextPath}/learn/${courseKey}/survey"><span>Complete Survey</span>
                                        </a>
                                    </div>
                                </c:if>
                                <%--Do Habit--%>
                                <c:if test="${hasHabit == false}">
                                    <div class="center">
                                        <a class="button-enroll button fadein" id="button-habit"
                                           href="${contextPath}/learn/${courseKey}/habit"><span>Set Habit!</span>
                                        </a>
                                    </div>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- Learn Navbar -->--%>
                    <%@include file="learn-navbar.html" %>
                </div>
                <div class="col-lg-10 ps-col-right">
                    <%--<!-- Content -->--%>
                    <div class="col-lg">
                        <div class="col-lg-9 ps-col-left">
                            <%-- Start Thread Content --%>
                            <div class="ps-well">
                                <h1>
                                    <%--${threadName}--%>
                                    <hr>
                                </h1>
                                <div>
                                    <td class="ps-table-row">
                                        <%--${dateCreated}--%>
                                    </td>
                                    <td class="ps-table-row">
                                        <%--${threadAuthor}--%>
                                    </td>
                                </div>
                                <br>
                                <div>
                                    <p>
                                        <%--${threadContent} --%>
                                        Lorem ipsum dolor sit amet, ferri adhuc dolore sea an, sonet iuvaret ad eos.
                                        Necessitatibus concludaturque cu vis, eum sumo dolor munere cu. Dicta nostrum
                                        vim in, ei soleat apeirian pro, legendos deseruisse constituam in vel. Ad illum
                                        ullum pro, vel et torquatos gloriatur, vim eius audire officiis ea. Eu his solet
                                        labores. Populo detraxit vix ea. Mei te lucilius complectitur, et mea ornatus
                                        dissentias, vis ea augue integre. Ea partiendo consulatu torquatos mel. Nonumy
                                        invidunt ea pri, magna persius docendi ea nam. Vix eu elitr laoreet, ex pri
                                        etiam tation maiestatis. Sea an timeam impetus propriae, persius labores nam ut.
                                        Sit ex timeam aliquip regione. Nec ornatus reprehendunt in, iudico nostro
                                        tractatos id sed. Per aperiri fabellas dissentiunt an. Has omnesque aliquando
                                        te. Ne usu etiam laboramus vituperatoribus, cu lorem cetero utroque est. Mea
                                        meis minim erroribus cu. Id usu graece iriure temporibus, nec ea labores
                                        molestiae constituam, ius illum tractatos intellegebat eu. Mei congue aliquip
                                        ei, id qui eros malis.</p>
                                </div>
                            </div>
                            <%-- End Thread Content --%>

                            <br>
                            <%--<!-- Start Comments -->--%>
                            <%--<div class="ps-well">--%>
                            <%--<h2>Comments--%>
                            <%--<hr>--%>
                            <%--</h2>--%>
                            <%--<br>--%>
                            <%--&lt;%&ndash;<!-- Leaving Comments -->&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<c:choose>&ndash;%&gt;--%>
                            <%--<div id="psLeaveComment" class="col-lg ps-well">--%>
                            <%--<div class="left">--%>
                            <%--<h4>Leave a Comment:</h4>--%>
                            <%--<form:form class="form-horizontal" method="post" modelAttribute="comment" action="/learn/${courseKey}/forum/">--%>
                            <%--<form:hidden path="commentKey" value="0" />--%>
                            <%--<form:hidden path="username" value="0" />--%>
                            <%--<form:hidden path="newsKey" value="0" />--%>
                            <%--<form:hidden path="date" value="0" />--%>
                            <%--<form:hidden path="state" value="add" />--%>
                            <%--<form:textarea class="form-control" id="article-comment-create" rows="3" path="content" />--%>
                            <%--<input type="hidden" id="psCommentCreator" value="${comment.username}">--%>
                            <%--<div class="right">--%>
                            <%--<button id="btn-comment-post" type="submit" class="btn-comment btn btn-primary">Submit--%>
                            <%--</button>--%>
                            <%--</div>--%>
                            <%--</form:form>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--&lt;%&ndash;</c:choose>&ndash;%&gt;--%>

                            <%--&lt;%&ndash;<!-- Loading Comments -->&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<!-- Comment -->&ndash;%&gt;--%>
                            <%--<div id="ps-comment-section">--%>
                            <%--<c:forEach items="${commentList}" var="articleComment">--%>
                            <%--<c:set var="commentorUserKey" value="${articleComment.userKey}" />--%>
                            <%--<div class="media ps-comment">--%>
                            <%--<!-- Comment creator key -->--%>
                            <%--<input type="hidden" value="${articleComment.commentKey}">--%>
                            <%--<a class="pull-left" href="#"> <img class="media-object" src="http://placehold.it/64x64" alt=""></a>--%>
                            <%--<div class="media-body">--%>
                            <%--<!-- Comment Author and Date Posted -->--%>
                            <%--<h4>${threadComment.username}--%>
                            <%--<small>${threadComment.date}</small>--%>
                            <%--</h4>--%>
                            <%--<hr>--%>
                            <%--<!-- Comment Content -->--%>
                            <%--<p class="comment-text">${threadComment.content}</p>--%>
                            <%--<!-- Comment Buttons (Edit/Delete OR Like) -->--%>
                            <%--<c:choose>--%>
                            <%--<c:when test="${currentUserKey == commentorUserKey}">--%>
                            <%--<div class="right">--%>
                            <%--<button type="button" class="btn-comment btn btn-primary btn-comment-delete">Delete</button>--%>
                            <%--<button type="button" class="btn-comment btn btn-primary btn-comment-edit">Edit</button>--%>
                            <%--</div>--%>
                            <%--</c:when>--%>
                            <%--</c:choose>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--</c:forEach>--%>
                            <%--</div>--%>
                            <%--<!-- End Comment -->--%>
                            <%--</div>--%>
                        </div>
                        <%--<!-- End Comments -->--%>
                        <%--List Threads Start--%>
                        <div class="col-lg-3 ps-col-right">
                            <%--<!--<c:forEach items="${forumList}" var="singleForum">-->--%>
                            <%-- If thread was created by the current user--%>
                            <%-- My Thread Start --%>
                            <div class="col-lg">
                                <div class="ps-well">
                                    <div>
                                        <h2>My Threads
                                            <hr>
                                        </h2>
                                        <button type="button" class="btn-comment btn btn-primary btn-thread-add">+
                                        </button>
                                    </div>
                                    <ul class="ps-ul">
                                        <%-- Link to thread page --%>
                                        <li class="ps-li ps-li-active" href=${threadLink}>
                                            <%--${threadName}--%>
                                        </li>
                                    </ul>

                                </div>
                            </div>
                            <%-- My Threads End--%>
                            <br>
                            <%-- Else thread was not created by the user--%>
                            <%-- Community Thread Start --%>
                            <div class="col-lg">
                                <div class="ps-well">
                                    <div>
                                        <h2>Community Threads
                                            <hr>
                                        </h2>
                                    </div>
                                    <ul class="ps-ul">
                                        <%-- Link to thread page --%>
                                        <li class="ps-li" href=${threadLink}>
                                            <%--${threadName}--%>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <%-- Community Threads End--%>
                            <%--<!--</c:forEach>-->--%>
                        </div>
                        <%-- List Threads End--%>

                    </div>
                    <br>
                    <!-- Add Forum Modal-->
                    <div class="modal ps-modal-type-comment fade" id="add-thread-modal" role="dialog">
                        <div class="ps-modal-dialog">
                            <!-- Modal content-->
                            <div class="ps-modal-content">
                                <div class="ps-modal-header">
                                    <button type="button" class="ps-type-close close ps-modal-close"
                                            data-dismiss="modal">
                                        <h1>X</h1></button>
                                    <div class="left">
                                        <h2 class="ps-feature-info-header"> New Thread
                                            <hr>
                                        </h2>
                                    </div>
                                </div>
                                <div class="ps-modal-body">
                                    <div class="form-group">
                                        <!-- Start Content -->
                                        <div class="left">
                                            <!-- Form -->
                                            <div class="left">
                                                <h4>Thread Name:</h4>
                                                <textarea class="form-control" id="forum-thread-name"
                                                          rows="1"></textarea>
                                            </div>
                                            <br>
                                            <div class="left">
                                                <h4>Thread Content</h4>
                                                <textarea class="form-control" id="forum-thread-content"
                                                          rows="5"></textarea>
                                            </div>
                                            <br>
                                            <div class="left">
                                                <%-- Hidden input for anonymuity --%>
                                                <h4>Display Name or Stay Anonymous?</h4>
                                                <button type="button" class="btn-comment btn btn-primary">Display Name
                                                </button>
                                                <button type="button" class="btn-comment btn btn-primary">Stay
                                                    Anonymous
                                                </button>
                                            </div>
                                            <br>
                                            <div class="right">
                                                <button id="btn-cue-post" type="submit"
                                                        class="btn-comment btn btn-primary">Submit
                                                </button>
                                            </div>
                                        </div>
                                        <!-- End Content -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Edit Modal -->
                    <!-- Content End-->
                </div>
            </div>
            <!-- End Learn Page -->
        </div>
    </div>
</header>

<%@include file="footer.html" %>
<script src="${contextPath}/resources/js/scrollreveal.js"></script>
<script src="${contextPath}/resources/js/navbar.js"></script>
<script src="${contextPath}/resources/js/learnNavbar.js"></script>
<script src="${contextPath}/resources/js/forum.js"></script>
<script src="${contextPath}/resources/js/animations.js"></script>
</body>
</html>