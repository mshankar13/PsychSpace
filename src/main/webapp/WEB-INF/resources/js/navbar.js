$(document).ready(function(){

    window.onbeforeunload = function(e){
        gapi.auth2.getAuthInstance().signOut();
    };

    $("#btn-signin").on("click", onSignIn);
});

function google_signin_callback(authResult){
    gapi.client.load('plus', 'v1', apiClientLoaded);

    if(authResult.status.method == 'AUTO'){
        console.log("auto" + authResult);
    } else if(authResult.status.method == 'PROMPT') {
        console.log("prompt" + authResult);
    }
}

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();

    console.log('Name: ' + profile.getName());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.

    var user = {};
    user["email"] = profile.getEmail();
    user['firstName'] = profile.getName();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/home",
        data: JSON.stringify(user),
        dataType: 'json',
        timeout: 600000,
        success: function (response) {
            console.log("SUCCESS: ", response);
        },
        error: function (e) {
           console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        //    enable buttons after log in
        }
    });


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