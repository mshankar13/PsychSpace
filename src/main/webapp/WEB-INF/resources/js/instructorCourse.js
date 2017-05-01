//TODO: check dates with today

var today;
$(document).ready(function(){
    // select course link onclick
    $("#select-course").change(changeCourse);

    loadCourseContent();

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
    $("#btn-add-course").on("click", addCourseModalShow);

    today = $.datepicker.formatDate('mm/dd/yy', new Date());
});

function changeCourse() {
    var currentUrl = window.location.pathname;
    var urlArr = currentUrl.split("/");
    var newCourseKey = $(this).val();
    var newUrl = "/" + urlArr[1] + "/" + newCourseKey;

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

function loadCourseContent() {
    // get current course values
    var title = $("#current-course-title").val();
    var description = $("#current-course-description").val();
    var startDate = $("#current-course-start-date").val();
    var endDate = $("#current-course-end-date").val();
    var enrollDate = $("#current-course-enroll-date").val();
    var dropDate = $("#current-course-drop-date").val();
    var capacity = $("#current-course-capacity").val();
    var status = $("#current-course-status").val();

    // set the values to the form for editing
    $("#course-title").text(title);
    $("#edit-course-description").val(description);
    $("#edit-course-start-date").datepicker();
    $("#edit-course-start-date").datepicker("setDate", startDate);
    $("#edit-course-start-date").datepicker({ minDate: "+1w" });
    $("#edit-course-end-date").datepicker();
    $("#edit-course-end-date").datepicker("setDate", endDate);
    $("#edit-course-end-date").datepicker({ minDate: startDate + "+1w" });
    $("#edit-course-enroll-date").datepicker();
    $("#edit-course-enroll-date").datepicker("setDate", enrollDate);
    $("#edit-course-drop-date").datepicker();
    $("#edit-course-drop-date").datepicker("setDate", dropDate);
    $("#edit-course-status").val(status);
    $("#edit-course-currSize").val(currSize);
    $("#edit-course-capacity").val(capacity);

    var today = new Date().getDate();
    $("edit-course-form").validate({
        fields: {
            startDate: {
                validators: {
                    notEmpty: {
                        message: 'The start date is required'
                    },
                    date: {
                        greaterThan: today + 7,
                        message: 'The start date needs to be at least a week from today'
                    }
                }
            }
        }
    });
}

function dateValidator() {
    from = $( "#edit-course-start-date" )
        .on( "change", function() {
            to.datepicker( "option", "minDate", getDate( this ) );
        }),
        to = $( "#to" ).datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 3
        })
            .on( "change", function() {
                from.datepicker( "option", "maxDate", getDate( this ) );
            });
}

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