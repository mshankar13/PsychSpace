$(document).ready(function () {
    var loggedIn = $('#psIsLoggedIn').val();
    if (loggedIn == true) {
        if (checkIfUserEnrolled() == true) {
            userEnrolledUI();
        }
        else if (checkIfUserEnrolled() == false) {
            if (checkCourseStatus() == true) {
                userNotEnrolledUI();
            }
            else {
                courseClosedUI();
            }
        }
    }
    else{
        $("#button-enroll").hide();
        $("#button-unenroll").hide();
    }
});

function checkIfUserEnrolled() {
    if ($("#button-user-enroll-status").val() == "true") {
        return true;
    }
    else {
        return false;
    }
}

function checkCourseStatus() {
    if ($("#button-enroll-status").val() == "open") {
        return true;
    }
    else {
        return false;
    }
}

function userEnrolledUI() {
    $("#button-enroll").hide();
    $("#button-unenroll").show();
}

function userNotEnrolledUI() {
    $("#button-enroll").show();
    $("#button-unenroll").hide();
}

function courseClosedUI() {
    $("#button-enroll").hide();
    $("#button-unenroll").hide();
}
