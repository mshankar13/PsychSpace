$(document).ready(function () {
    $("#add-survey-submit").on("click", addSurvey);
});


function addSurvey() {
    var survey = {};
    survey["title"] = $("#add-survey-title");
    survey["questions"] = {};
    $.each($(".question-group"), function(index, value) {
        var qArray = survey["questions"][index];
        $.each($(this).find(".row"), function(i, v) {
            qArray["answers"][i] = v;
        })
    })
}