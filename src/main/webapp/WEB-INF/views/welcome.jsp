<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Maven Pro' rel='stylesheet'>
    <link href='${pageContext.request.contextPath}/resources/css/welcome.css' rel='stylesheet'>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
    <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-2.5.0.js"></script>
    <!--<script src="../resources/js/ui-bootstrap-tpls-2.5.0.min.js"></script>-->
</head>
<body>
        <header>
            <div class="header-content">
                <div class="header-content-inner">
                    <!-- <h1 id="homeHeading">PsychSpace</h1> -->
                    <div class="header-content-inner-logos">
                        <div class="logo-img">
                            <img id="logo" src="../resources/img/logo.png" alt="PsychSpace">
                        </div>
                        <div class="logo-text-img">
                            <img id="logo-text" src="../resources/img/logo_text.png" alt="PsychSpace">
                        </div>
                        <p>Develop healthy habits and achieve personal goals through self-discipline</p>
                    </div>
                    <div class="header-content-inner-buttons">
                        <!--<a href="#about" class="btn btn-primary btn-xl page-scroll">Enter Site</a>-->
                        <a class="button" id="button-about" href="">About</a>
                        <a class="button" id="button-enter" href="">Enter Site</a>
                       <!-- <button type="submit" class="button" id="button-about"><span>About</span></button>-->
                        <!--<button type="button" class="button" id="button-enter"><span>Sign In</span></button>-->
                    </div>
                </div>
                <footer>Space Cadet &copy; 2017</footer>
            </div>
        </header>
</body>

</html>