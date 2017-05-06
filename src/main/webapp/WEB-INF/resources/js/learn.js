/**
 * Javascript for all pages /learn*
 */

$(document).ready(function () {
    var goal = $("#goal").val();
    if (goal != "")
        loadGoal(goal);

    $("#btn-example-goal").on("click", exampleGoalModalShow);
});

function loadGoal(goal) {
    var action = $("#action").val();
    var value = $("#value").val();
    var unit = $("#unit").val();

    $("#action").val(action);
    $("#value").val(value);
    $("#unit").val(unit);
}

function exampleGoalModalShow() {
    $("#exampleGoalsModal").modal("show");
}
