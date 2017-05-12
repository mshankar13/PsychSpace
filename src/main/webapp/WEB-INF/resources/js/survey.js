$(document).ready(function () {
    // Hide the survey submit button on page load
    $('#btn-submit-survey').hide();

    // On Click Responses / Functions
    $("input").on("click", markQuestionAsDone);
    $(".ps-answer").on("click", markAnswerAsChecked);
    $('#btn-submit-survey').on("click", surveySubmit);
});

/**
 * Add isAnswered class to the completed question when one of its answers are checked
 */
function markQuestionAsDone() {
    console.log("clicked");
    $(this).closest(".ps-question").addClass("isAnswered");
    checkAllAnswers();
}

/**
 * Add isChecked class to the checked answer and make sure the class is removed for the previous checked answer
 */
function markAnswerAsChecked() {
    console.log("label clicked");
    $(this).closest(".ps-question").find(".ps-answer").removeClass('isChecked');
    $(this).addClass("isChecked");
}

/**
 * Checks that all questions have been answered before showing the submit button
 */
function checkAllAnswers() {
    var isAllAnswered = true;
    $('.ps-question').each(function () {
        if (!$(this).is(".isAnswered")) {
            console.log("All answers are not checked");
            isAllAnswered = false;
        }
    });
    if(isAllAnswered == true){
        $('#btn-submit-survey').show();
    }
    else{
        $('#btn-submit-survey').hide();
    }
}

/**
 * Student submits a survey
 */
function surveySubmit() {
    var courseKey = $("#course-key").val();
    var survey = {};
    survey["courseKey"] = courseKey;
    survey["courseTitle"] = $("#course-title").val();

    survey["title"] = $("#survey-title").val();
    survey["date"] = $('#survey-due-date').val();
    var questions = {};

    survey["questions"] = {};
    var qCount = 0;
    $.each($(".question-group"), function(index, value) {
        var question = {};
        var answers = {};
        var aCount = 0;
        question["question"] = $(value).find(".input-question").text();
        question["type"] = $(value).find(".input-type").val();

        // Find answer marked as checked
        $.each($(this).find(".isAnswered"), function(i, v) {
            var answer = {};
            answer["answer"] = $(v).find(".input-answer").text();
            answer["score"] = $(v).find(".input-score").val();
            answers[i] = answer;
            aCount++;
        });
        question["answers"] = answers;
        questions[index] = question;
        question["answerTotal"] = aCount;
        qCount++;
    });

    survey["questionTotal"] = qCount;
    survey["questions"] = questions;

    var link = "/learn/" + courseKey + "/survey/submitSurvey";

    console.log("Survey", survey);

    $.ajax({
        url: link,
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(survey),
        success: function() {
            var successAlert = '<div class="alert alert-success alert-dismissible" role="alert"> \
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">\
                                        <span aria-hidden="true">&times;</span></button> \
                                        <strong>All set!</strong> Survey Submitted!\
                                </div>';
            $(".col-md-9").prepend(successAlert);
        },
        error: function(e) {
            console.log("ERROR", e);
        }
    });
}