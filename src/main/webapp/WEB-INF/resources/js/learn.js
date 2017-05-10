$(document).ready(function () {
    var goal = $("#goal").val();
    if (goal != "")
        loadGoal(goal);

    $("#btn-example-goal").on("click", exampleGoalModalShow);
});

function setLearnSidebar() {

}



function loadGoal(goal) {
    var action = $("#action").val();
    var value = $("#value").val();
    var unit = $("#unit").val();
    var goalDuedate = $("#goal-due-date").val();
    var today = $.datepicker.formatDate('mm/dd/yy', new Date());

    $("#action").val(action);
    $("#value").val(value);
    $("#unit").val(unit);

    if (Date.parse(goalDuedate) < Date.parse(today)) {
        $("#action").prop("disabled", true);
        $("#value").prop("disabled", true);
        $("#unit").prop("disabled", true);
    }
}

function exampleGoalModalShow() {
    $("#exampleGoalsModal").modal("show");
}
