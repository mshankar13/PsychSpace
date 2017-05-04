$(document).ready(function () {
    // Hide submit evaluation by default
    $('#btn-submit-evaluation').hide();

    $("#evaluation-rawScore").on("change", markNumberQuestionAsDone);
    $("#evaluation-content").on("change", markTextQuestionAsDone);

});

// Add isAnswered class to the completed question when answered
function markNumberQuestionAsDone() {
    console.log("number changed");
    if ($(this).val() == "") {
        $(this).closest(".ps-question").removeClass("isAnswered");
        $('#btn-submit-evaluation').hide();
    }
    else {
        $(this).closest(".ps-question").addClass("isAnswered");
        $('#btn-submit-evaluation').show();
    }
}

function markTextQuestionAsDone() {
    console.log("text changed");
    if ($(this).val() == "") {
        $(this).closest(".ps-question").removeClass("isAnswered");
        $(this).closest(".ps-question").addClass("ps-optional-response");
    }
    else {
        $(this).closest(".ps-question").removeClass("ps-optional-response");
        $(this).closest(".ps-question").addClass("isAnswered");
    }
}