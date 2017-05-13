<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="navbar navbar-default" id="nav-top">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse in">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="${contextPath}/">
                        <img src="${contextPath}/resources/img/logo.png" alt="logo" class="img-logo">
                    </a>
                    <a class="navbar-brand" href="${contextPath}/">
                        <img src="${contextPath}/resources/img/logo_text.png" alt="PsychSpace" id="img-logo-text">
                    </a>
                </div>

                <ul class="nav navbar-nav" id="uib-overrides-nav">
                    <li id="nav-home" class="nav-li nav-user-only" ><a href="${contextPath}/home" id="nav-home-a" class="uib-overrides-nav-a">Home</a></li>
                    <li id="nav-learn" class="nav-li nav-user-only" ><a href="${contextPath}/learn" id="nav-learn-a" class="uib-overrides-nav-a">Learn</a></li>
                    <li class="nav-li"><a href="${contextPath}/catalogue" id="nav-catalogue-a" class="uib-overrides-nav-a">Catalogue</a></li>
                    <li class="nav-li"><a href="${contextPath}/news" id="nav-news-a" class="uib-overrides-nav-a">News</a></li>
                </ul>

                <ul class="nav navbar-nav" id="nav-sign-in">
                    <li class="nav-li">
                        <div class="g-signin2" id="div-sign-in" data-onsuccess="onSignIn"></div>
                    </li>
                </ul>
                <ul class="nav navbar-nav" id="nav-profile">
                    <li class="dropdown">
                        <a href="#" id="uib-overrides-profile-a" class="dropdown-toggle" data-toggle="dropdown">
                            <button type="button" class="btn btn-default btn-profile" aria-label="Left Align">
                                <%--Hi ${currUser.firstName}  ${currUser.lastName}!--%>
                                <!--<span class="glyphicon glyphicon-user" aria-hidden="true"></span>-->
                                <img id="profile-img">
                            </button>
                            <b id="uib-overrides-caret" class="caret btn-profile"></b>
                        </a>
                        <ul class="dropdown-menu user-dropdown" role="menu">
                            <input type="hidden"  id="user-role" value="${currUser.role}">
                            <c:set var="userRole" value="${currUser.role}" />
                            <li class="menu-item" id="welcome-message"></li>
                            <c:choose>
                                <c:when test="${userRole == 'Instructor'}" >
                                    <li class="menu-item"> <a href="/instructor" class="menu-item-a">Instructor Page </a></li>
                                </c:when>
                                <c:when test="${userRole == 'Admin'}" >
                                    <li class="menu-item"> <a href="/admin_addArticle" class="menu-item-a">Admin Page </a></li>
                                </c:when>
                            </c:choose>
                            <li class="menu-item"> <a href="${contextPath}/settings" class="menu-item-a"> Settings </a></li>
                            <li id="btn-sign-out"  class="menu-item"> <a href="" class="menu-item-a">Sign out </a></li>
                            <li class="menu-item"> <a href="#" class="menu-item-a">Help </a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</header>
