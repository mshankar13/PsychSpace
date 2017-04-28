$(document).ready(function(){

    // set the tab links
    var url = window.location.pathname;
    var len = "/evaluations".length;
    var courseBaseUrl = url.slice(0, url.length - len);

    $("#course-a").attr("href", courseBaseUrl);
    $("#survey-a").attr("href", courseBaseUrl + "/survey");
    $("#videos-a").attr("href", courseBaseUrl + "/videos");
    $("#evaluation-a").attr("href",url);

    // enable date input
    $("#goal-due-date").datepicker();
    $("#cues-due-date").datepicker();
    $("#habit-due-date").datepicker();
});
