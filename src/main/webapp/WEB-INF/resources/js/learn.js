/**
 * javascript for the contents within of a course
 */


$(document).ready(function () {
    // check if the user has set a goal
    var goal = $("#goal").val();
    if (goal != "")
        loadGoal(goal);

    setProgress();

    $("#btn-example-goal").on("click", exampleGoalModalShow);
});

//load the user goal
function loadGoal(goal) {
    // get the goal values
    var action = $("#action").val();
    var value = $("#value").val();
    var unit = $("#unit").val();
    var goalDuedate = $("#goal-due-date").val();
    var today = $.datepicker.formatDate('mm/dd/yy', new Date());

    // set the goal values
    $("#action").val(action);
    $("#value").val(value);
    $("#unit").val(unit);

    // if the set goal deadline has passed, disable the fields
    if (Date.parse(goalDuedate) < Date.parse(today)) {
        $("#goal-reminder").hide();
        $("#action").prop("disabled", true);
        $("#value").prop("disabled", true);
        $("#unit").prop("disabled", true);
    }
}

// prop a modal of example goals
function exampleGoalModalShow() {
    $("#exampleGoalsModal").modal("show");
}

// set the progress bar on learn/progress page
function setProgress() {
    var progress = $("#course-progress").val() + "%";
    $(".progress-bar").css("width", progress);
}