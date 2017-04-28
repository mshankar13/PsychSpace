//TODO: check dates with today


$(document).ready(function(){
    var courses = $("#courses").val();
    if (courses == "") {
        $("#edit-course-div").hide();
    }

    // set the tab links
    var url = window.location.pathname;
    $("#course-a").attr("href", url );
    $("#survey-a").attr("href", url + "/survey");
    $("#videos-a").attr("href", url + "/videos");
    $("#evaluation-a").attr("href", url + "/evaluations");


    // enable datepicker function
    $("#add-course-enroll-date").datepicker();
    $("#add-course-start-date").datepicker();
    $("#add-course-end-date").datepicker();
    $("#add-course-drop-date").datepicker();

    // enable onclick function calls
    $("#edit-course-table").on("click", ".btn-edit-course", editCourseModalShow);
    $("#btn-add-course").on("click", addCourseModalShow);
});

function addCourseModalShow() {
    $("#addCourseModal").modal("show");
}

function editCourseModalShow() {
    var row = $(this).closest("tr");

    var userKey = row.find("#userKey").val();
    var courseKey = row.find("#courseKey").val();
    var instructor = row.find("#instructor").val();
    var title = row.find("#title").val();
    var description = row.find("#description").val();
    var startDate = row.find("#startDate").val();
    var endDate = row.find("#endDate").val();
    var enrollDate = row.find("#enrollDate").val();
    var dropDate = row.find("#dropDate").val();
    var status = row.find("#status").val();
    var currSize = row.find("#currSize").val();
    var capacity = row.find("#capacity").val();

    // set form data
    $("#edit-user-key").val(userKey);
    $("#edit-course-key").val(courseKey);
    $("#edit-course-title").val(title);
    $("#edit-course-instructor").val(instructor);

    $("#edit-course-description").val(description);
    $("#edit-course-start-date").datepicker();
    $("#edit-course-start-date").datepicker("setDate", startDate);
    $("#edit-course-end-date").datepicker();
    $("#edit-course-end-date").datepicker("setDate", endDate);
    $("#edit-course-enroll-date").datepicker();
    $("#edit-course-enroll-date").datepicker("setDate", enrollDate);
    $("#edit-course-drop-date").datepicker();
    $("#edit-course-drop-date").datepicker("setDate", dropDate);
    $("#edit-course-status").val(status);
    $("#edit-course-currSize").val(currSize);
    $("#edit-course-capacity").val(capacity);

    $("#editCourseModal").modal("show");
}