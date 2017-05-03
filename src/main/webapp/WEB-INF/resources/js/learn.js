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
    var value = $("#value").val();
    var unit = $("#unit").val();

}

function exampleGoalModalShow() {
    $("#exampleGoalsModal").modal("show");
}
