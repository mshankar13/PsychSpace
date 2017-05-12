<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
        <link href='${contextPath}/resources/css/navbar.css' rel='stylesheet'>
        <link href='${contextPath}/resources/css/about.css' rel='stylesheet'><script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/navbar.js"></script>
    </head>

    <body>
    <jsp:include page="navbar.jsp" />
        <div id="about-html">
            <div class="about-site">
                <h1>About PsychSpace</h1>
                <h3>Develop healthy habits and achieve personal goals through self-discipline</h3>
                <div class="content-ct about">
                    <div class="feature">
                        <div class="container-logo">
                            <img src="${contextPath}/resources/img/logo.png" class="logo-img">
                        </div>
                        <p>PsychSpace provides an online self-learning tool for adults to develop healthy habits. This
                            tool will enable users to understand the science behind self-motivation, turning ambitions
                            into accomplishments, correcting bad habits, and maintaining new routines. Users can sign up
                            as students and select courses that would help them create or correct specific habits. As
                            part of an effort to promote cyberlearning, PsychSpace helps students manage their
                            self-enrolled topics, keep track of their progress during the learning period, communicate
                            with peers, and receive personalized feedback from instructors proficient in psychology
                            and self-help techniques.
                        </p>
                    </div>

                </div>
            </div>
            <div class="about-company">
                <h1>Team Space Cadet</h1>
                <h3>Space Cadet was founded by Celeste, Angela, Marlene, and Andrea in January 2017</h3>
                <div class="content-ct speaker" id="speakers">
                    <div class="feature">
                        <img src="img/speaker-1.png" class="speaker-img">
                        <h2>Celesta Ma</h2>
                        <h3>Lead Programmer</h3>
                        <p>She is a Computer Science major with HCI specialization. Celeste has worked with startup
                            companies and Research Foundation for SUNY for full stack development. She is trained at
                            developing user friendly interfaces and web development.</p>
                        <ul class="speaker-social">
                            <li><a href="#"><span class="ti-linkedin"></span></a></li>
                            <li><a href="#"><span class="ti-github"></span></a></li>
                        </ul>
                    </div>
                    <div class="feature">
                        <img src="img/speaker-1.png" class="speaker-img">
                        <h2>Angela Liao</h2>
                        <h3>Project Manager</h3>
                        <p>Pursuing a Bachelor’s in Computer Science and Applied Math and Statistics at Stony Brook
                            University. Angela is a former intern and a prospective employee at Amazon.com. She is
                            proficient in team organization and project specification development.</p>
                        <ul class="speaker-social">
                            <li><a href="#"><span class="ti-linkedin"></span></a></li>
                            <li><a href="#"><span class="ti-github"></span></a></li>
                        </ul>
                    </div>
                    <div class="feature">
                        <img src="img/speaker-1.png" class="speaker-img">
                        <h2>Marlene Shankar</h2>
                        <h3>Data Designer</h3>
                        <p>Pursuing a Bachelor’s in Computer Science and Biochemistry at Stony Brook University.
                            Marlene is a former game programming and Chemistry storyline intern for the Smithsonian.
                            She is proficient in relational databases and backend development.</p>
                        <ul class="speaker-social">
                            <li><a href="#"><span class="ti-linkedin"></span></a></li>
                            <li><a href="#"><span class="ti-github"></span></a></li>
                        </ul>
                    </div>
                    <div class="feature">
                        <img src="img/speaker-1.png" class="speaker-img">
                        <h2>Andrea Cerini</h2>
                        <h3>Lead Designer</h3>
                        <p>A Computer Science student with a focus in Digital Arts at Stony Brook University.
                            Andrea has over 10 years of experience both professionally and personally in digital arts,
                            having worked as a graphics designer and her art has been featured in many exhibitions.
                            She is proficient in many design applications such as Adobe Photoshop and Illustrator.</p>
                        <ul class="speaker-social">
                            <li><a href="#"><span class="ti-linkedin"></span></a></li>
                            <li><a href="#"><span class="ti-github"></span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <%@include  file="footer.html" %>
    </body>

    </html>