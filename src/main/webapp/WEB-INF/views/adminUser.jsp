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
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.js"></script>
    <%--Customized--%>
    <link href="${contextPath}/resources/css/admin.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">

</head>
<body>

<div id="wrapper">

    <%@include file="admin-sidebar.html" %>

    <div id="page-content-wrapper">
        <%--Tab--%>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#instructor" >Instructor</a></li>
            <li><a data-toggle="tab" href="#admin" >Admin</a></li>
        </ul>

        <c:set var="instructorApplicants" value="${instructorApplicants}"/>
        <c:set var="adminApplicants" value="${adminApplicants}"/>
        <div class="tab-content">
            <div id="instructor" class="tab-pane fade in active">
                <c:choose>
                    <c:when test="${empty instructorApplicants}">
                        <p>No one has applied to be an instructor.</p>
                    </c:when>
                    <c:otherwise>
                        <h1>Instructor Applications</h1>
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${instructorApplicants}" var="applicant">
                                <tr>
                                    <td class="user-name">${applicant.firstName} ${applicant.lastName}</td>
                                    <td><button class="ps-btn-primary btn-instructor-approve">Approve</button></td>
                                    <td><button class="ps-btn btn-instructor-deny">Deny</button></td>
                                    <input type="hidden" value="${applicant.userKey}" class="user-key">
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>

            </div>
            <div id="admin" class="tab-pane fade">
                <c:choose>
                    <c:when test="${empty adminApplicants}">
                        <p>No one has applied to be an admin.</p>
                    </c:when>
                    <c:otherwise>
                        <h1>Admin Applications</h1>
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th colspan="3">Name</th>
                                <th colspan="2"></th>
                                <th colspan="2"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${adminApplicants}" var="applicant">
                                <tr>
                                    <td class="user-name" colspan="3">${applicant.firstName} ${applicant.lastName}</td>
                                    <td colspan="2"><button class="ps-btn-primary btn-admin-approve">Approve</button></td>
                                    <td colspan="2"><button class="ps-btn btn-admin-deny">Deny</button></td>
                                    <input type="hidden" value="${applicant.email}" class="email">
                                    <input type="hidden" value="${applicant.userKey}" class="user-key">
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <%--Instructor Approve Modal Confirmation--%>
        <div class="modal fade" id="instructorApprovalModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog instructor-modal" role="document">
                <div class="modal-header row">
                    <div class="col-sm-10">
                        <h3 class="modal-title">Instructor Approval Confirmation</h3>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to approve <span id="approve-instructor-applicant-name"></span> to be an instructor?</p>
                    <form:form method="post" action="/admin_user/submit" modelAttribute="user">
                        <form:hidden path="userKey" value="" id="approve-instructor-user-key"/>
                        <form:hidden path="email" value=""/>
                        <form:hidden path="firstName" value=""/>
                        <form:hidden path="lastName" value=""/>
                        <form:hidden path="role" value="instructor"/>
                </div> <%--End of modal body--%>
                <div class="modal-footer">
                    <button type="button" class="ps-btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="ps-btn-primary">Approve</button>
                    </form:form>
                </div>
            </div>
        </div>

        <%--Instructor Approve Modal Confirmation--%>
        <div class="modal fade" id="instructorDenyModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog instructor-modal" role="document">
                <div class="modal-header row">
                    <div class="col-sm-10">
                        <h3 class="modal-title">Instructor Disapproval Confirmation</h3>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to disapprove <span id="deny-instructor-applicant-name"></span> to be an instructor?</p>
                    <form:form method="post" action="/admin_user/submit" modelAttribute="user">
                        <form:hidden path="userKey" value="" id="deny-instructor-user-key"/>
                        <form:hidden path="email" value=""/>
                        <form:hidden path="firstName" value=""/>
                        <form:hidden path="lastName" value=""/>
                        <form:hidden path="role" value="user"/>
                    </form:form>
                </div> <%--End of modal body--%>
                <div class="modal-footer">
                    <button type="button" class="ps-btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="ps-btn-primary">Deny</button>
                </div>
            </div>
        </div>
        <%--End of Instructor Approve Modal Confirmation--%>

        <%--Admin Approve Modal Confirmation--%>
        <div class="modal fade" id="adminApprovalModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog instructor-modal" role="document">
                <div class="modal-header row">
                    <div class="col-sm-10">
                        <h3 class="modal-title">Admin Approval Confirmation</h3>
                    </div>
                    <div class="col-sm-2">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to approve <span id="approve-admin-applicant-name"></span> to be an admin?</p>
                    <form:form method="post" action="/admin_user/submit" modelAttribute="user">
                        <form:hidden path="userKey" value="" id="approve-admin-user-key"/>
                        <form:hidden path="email" value=""/>
                        <form:hidden path="firstName" value=""/>
                        <form:hidden path="lastName" value=""/>
                        <form:hidden path="role" value="admin"/>
                    </div> <%--End of modal body--%>
                    <div class="modal-footer">
                        <button type="button" class="ps-btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="ps-btn-primary">Approve</button>
                    </div>
                    </form:form>
            </div>
        </div>
        <%--End of Instructor Approve Modal Confirmation--%>

            <%--Admin Deny Modal Confirmation--%>
            <div class="modal fade" id="adminDenyModal" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog instructor-modal" role="document">
                    <div class="modal-header row">
                        <div class="col-sm-10">
                            <h3 class="modal-title">Admin Approval Confirmation</h3>
                        </div>
                        <div class="col-sm-2">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to disapprove <span id="deny-admin-applicant-name"></span> to be an admin?</p>
                        <form:form method="post" action="/admin_user/submit" modelAttribute="user">
                            <form:hidden path="userKey" value="" id="deny-admin-user-key"/>
                            <form:hidden path="email" value=""/>
                            <form:hidden path="firstName" value=""/>
                            <form:hidden path="lastName" value=""/>
                            <form:hidden path="role" value="admin"/>
                    </div> <%--End of modal body--%>
                    <div class="modal-footer">
                        <button type="button" class="ps-btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="ps-btn-primary">Approve</button>
                        </form:form>
                    </div>
                </div>
            </div>
            <%--End of Admin Deny Modal Confirmation--%>

    </div>
</div>

<script src="${contextPath}/resources/js/adminUser.js"></script>

</body>
</html>

