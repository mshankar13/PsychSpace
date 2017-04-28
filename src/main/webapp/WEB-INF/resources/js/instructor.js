$(document).ready(function(){
    $("#add-course-enroll-date").datepicker();
    $("#add-course-start-date").datepicker();
    $("#add-course-end-date").datepicker();
    $("#add-course-drop-date").datepicker();

    $("#btn-add-course").on("click", addCourseModalShow);
});


function addCourseModalShow() {
    $("#addCourseModal").modal("show");
}