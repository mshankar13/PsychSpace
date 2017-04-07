$(document).ready(function(){

    window.onbeforeunload = function(e){
        gapi.auth2.getAuthInstance().signOut();
    };

    $("#btn-signin").on("click", onSignIn);

    if (checkIfLoggedIn()) {
        $("#btn-sign-in").hide();
        $("#icon-user").show();
        $("#nav-home").show();
        $("#nav-learn").show();
    }


    else {
        $("#btn-sign-in").show();
        $("#icon-user").hide();
        $("#nav-home").hide();
        $("#nav-learn").hide();
    }
});

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();

    var user = {};
    user["email"] = profile.getEmail();
    user["firstName"] = profile.getGivenName();
    user["lastName"] = profile.getFamilyName();

    console.log(user);

    $.ajax("/home", {
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(user),
    });

    //Store the entity object in sessionStorage where it will be accessible from all pages of your site.
    sessionStorage.setItem('user',JSON.stringify(user));
}

function checkIfLoggedIn()
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
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}

function onSigninCallbackVanilla(authResponse){
    console.log(authResponse);
}