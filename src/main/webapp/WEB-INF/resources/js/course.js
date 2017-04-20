$(document).ready(function () {
    setEnrollButton();
});

function setEnrollButton(){
    // If course is closed, hide and disable the enroll button
    if($("#button-enroll-status").val() == "closed"){
        $("#button-enroll").hide();
    }
}