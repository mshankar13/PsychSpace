var profile;

$(document).ready(function(){

    setActiveNav();

    if (checkIfSignedIn()) {
        userSignedInUI();
    }
    else {
        userSignedOutUI();
    }

    $("#btn-sign-in").on("click", onSignIn);
    $("#btn-sign-out").on("click", signOut);

});

function onSignIn(googleUser, authResult) {
    var url = window.location.pathname;
    console.log(url);
    if (url == "/")
        url = url + "login";
    else
        url = url + "/login";

    profile = googleUser.getBasicProfile();

    if(!url.includes("learn") && !url.includes("home")
        && !url.includes("Course") && !url.includes("Survey")
       && !url.includes("Video") && !url.includes("instructor")) {
        var user = {};
        user["email"] = profile.getEmail();
        user["firstName"] = profile.getGivenName();
        user["lastName"] = profile.getFamilyName();

        $.ajax(url, {
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(user)
        });

        //Store the entity object in sessionStorage where it will be accessible from all pages of your site.
        sessionStorage.setItem('user',JSON.stringify(user));
    }

    // hide signin button, show the user icon
    userSignedInUI();
}

function google_signin_callback(authResult){
    if(authResult.status.method == 'AUTO'){
        // handle auto sign-in scenario
    }
}

function checkIfSignedIn()
{
    if(sessionStorage.getItem('user') == null){

        return false;

    } else {
        //User already logged in
        var userEntity = {};
        userEntity = JSON.parse(sessionStorage.getItem('myUserEntity'));
        return true;
    }
}


function signOut() {
    var url = window.location.href;
    url = "/" + url.substring(url.lastIndexOf("/") + 1, url.length);
    if (url == "/")
        url = url + "logout";
    else
        url = url + "/logout";

    var auth2 = gapi.auth2.getAuthInstance();

    var user = {};
    user["email"] = profile.getEmail();
    user["firstName"] = profile.getGivenName();
    user["lastName"] = profile.getFamilyName();

    $.ajax(url, {
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(user)
    });

    auth2.signOut().then(function () {
        sessionStorage.removeItem("user");
        console.log('User signed out.');
    });

    userSignedOutUI();
    window.location.href = "http://psychspace-160921.appspot.com/";
}

function userSignedInUI() {
    $("#nav-sign-in").hide();
    $("#nav-profile").show();
    $("#nav-home").show();
    $("#nav-learn").show();
}

function userSignedOutUI() {
    $("#nav-sign-in").show();
    $("#nav-profile").hide();
    $("#nav-home").hide();
    $("#nav-learn").hide();
}

function setActiveNav() {
    var url = window.location.href;
    url = url.substring(url.lastIndexOf("/") + 1, url.length);

    if (url == "home") {
        $("#nav-home-a").addClass("active");
        $("#nav-learn-a").removeClass("active");
        $("#nav-catalogue-a").removeClass("active");
        $("#nav-news-a").removeClass("active");
    }
    else if (url == "learn") {
        $("#nav-learn-a").addClass("active");
        $("#nav-home-a").removeClass("active");
        $("#nav-catalogue-a").removeClass("active");
        $("#nav-news-a").removeClass("active");
    }
    else if (url == "catalogue") {
        $("#nav-catalogue-a").addClass("active");
        $("#nav-learn-a").removeClass("active");
        $("#nav-home-a").removeClass("active");
        $("#nav-news-a").removeClass("active");
    }
    else if (url == "news") {
        console.log("news!");
        $("#nav-news-a").addClass("active");
        $("#nav-learn-a").removeClass("active");
        $("#nav-catalogue-a").removeClass("active");
        $("#nav-home-a").removeClass("active");
    }
}
