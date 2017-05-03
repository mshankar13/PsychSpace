$(document).ready(function(){
    // select course link onclick
    $("#select-course").change(changeCourse);

    // set the tab links
    var url = window.location.pathname;
    var len = "/dueDates".length;
    var courseBaseUrl = url.slice(0, url.length - len);
    $("#course-a").attr("href", courseBaseUrl);
    $("#survey-a").attr("href", courseBaseUrl + "/survey");
    $("#videos-a").attr("href", courseBaseUrl + "/videos");
    $("#evaluation-a").attr("href",url);

    // enable date input
    $("#goal-due-date").datepicker();
    $("#cues-due-date").datepicker();
    $("#habit-due-date").datepicker();

    loadDates();

    $("#btn-add-course").on("click", addCourseModalShow);
});

function addCourseModalShow() {
    $("#addCourseModal").modal("show");
}

function changeCourse() {
    var currentUrl = window.location.pathname;
    var urlArr = currentUrl.split("/");
    var newCourseKey = $(this).val();
    var newUrl = "/" + urlArr[1] + "/" + newCourseKey + "/evaluations";

    $.ajax({
        url: newUrl,
        type: "GET",
        timeout : 15000,
        success: function() {
            window.location.href = newUrl;
        },
        error: function() {
            console.log("ERROR");
        }
    });
}

function loadDates() {
    var goalDueDate = $("#goal-due-date").val();
    var cuesDueDate = $("#cues-due-date").val();
    var habitDueDate = $("#habit-due-date").val();

    $("#goal-due-date").val(goalDueDate);
    $("#cues-due-date").val(cuesDueDate);
    $("#habit-due-date").val(habitDueDate);
}