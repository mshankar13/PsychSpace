/**
 * Javascript for the course navigation
 * set the active tab
 */

$(document).ready(function(){
    setActiveLearnNav();
});

// Set the active tab
function setActiveLearnNav() {
    var today = $.datepicker.formatDate('mm/dd/yy', new Date());
    var startDate = $('#course-start-date').val();

    if (Date.parse(startDate) > Date.parse(today)) {
        $("#nav-learn-evaluations").hide();
    }

    var url = window.location.href;
    url = url.substring(url.lastIndexOf("/") + 1, url.length);
    console.log(url);
    if (url == "habit") {
        $("#nav-learn-progress").removeClass("active");
        $("#nav-learn-habit").addClass("active");
        $("#nav-learn-evaluations").removeClass("active");
        $("#nav-learn-videos").removeClass("active");
        $("#nav-learn-forum").removeClass("active");
    }
    else if (url == "evaluation") {
        $("#nav-learn-progress").removeClass("active");
        $("#nav-learn-habit").removeClass("active");
        $("#nav-learn-evaluations").addClass("active");
        $("#nav-learn-videos").removeClass("active");
        $("#nav-learn-forum").removeClass("active");
    }
    else if (url == "videos") {
        $("#nav-learn-progress").removeClass("active");
        $("#nav-learn-habit").removeClass("active");
        $("#nav-learn-evaluations").removeClass("active");
        $("#nav-learn-videos").addClass("active");
        $("#nav-learn-forum").removeClass("active");
    }
    else if (url == "forum") {
        $("#nav-learn-progress").removeClass("active");
        $("#nav-learn-habit").removeClass("active");
        $("#nav-learn-evaluations").removeClass("active");
        $("#nav-learn-videos").removeClass("active");
        $("#nav-learn-forum").addClass("active");
    }
    else if (url == "survey") {
        $("#nav-learn-progress").removeClass("active");
        $("#nav-learn-habit").removeClass("active");
        $("#nav-learn-evaluations").removeClass("active");
        $("#nav-learn-videos").removeClass("active");
        $("#nav-learn-forum").removeClass("active");
    }
    else{
        $("#nav-learn-progress").addClass("active");
        $("#nav-learn-habit").removeClass("active");
        $("#nav-learn-evaluations").removeClass("active");
        $("#nav-learn-videos").removeClass("active");
        $("#nav-learn-forum").removeClass("active");
    }

}