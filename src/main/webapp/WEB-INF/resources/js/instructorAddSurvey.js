//TODO: 1. no course, no survey
//      2. Question type
//      3. Add survey


var questionCounter = 2;
$(document).ready(function(){
    $("#btn-survey-add-question").on("click", addQuestion);
    $("#add-survey-q-group").on("click", ".btn-survey-remove-question", deleteQuestion);
    $("#add-survey-q-group").on("click", ".btn-survey-add-answer", addAnswer);
    $("#add-survey-q-group").on("click", ".btn-survey-remove-answer", removeAnswer);
    $("#add-survey-due-date").datepicker();

    $("#add-survey-submit").on("click", addSurvey);
});

function addQuestion() {
    var div = '<div class="form-group question-group"> \
                    <label>Question <span class="question-number"></span></label> \
                    <div class="row"> \
                         <div class="col-md-10"> \
                            <input class="form-control"/> \
                        </div> \
                        <div class="col-md-2"> \
                            <button type="button" class="btn btn-default btn-sm btn-survey-remove-question"> \
                                <span class="glyphicon glyphicon-minus"></span> \
                            </button> \
                        </div> \
                    </div> \
                    <div class="row answer-row"> \
                        <div class="col-md-1"> \
                            <label>Answer</label> \
                        </div> \
                        <div class="col-md-4"> \
                            <input class="input-answer"/> \
                        </div> \
                        <div class="col-md-1"> \
                            <label>Score</label> \
                        </div> \
                        <div class="col-md-3"> \
                            <input type="number" class="input-score"/> \
                        </div> \
                        <div class="col-md-2"> \
                            <button type="button" class="btn btn-default btn-sm btn-survey-add-answer"> \
                                <span class="glyphicon glyphicon-plus"></span> \
                            </button> \
                        </div> \
                    </div>\
                </div>';

    $("#add-survey-q-group").append(div);

    questionCounter++;
    $("#add-survey-q-group div:last-child").find("span.question-number").text(questionCounter);

}

function addAnswer() {
    var div = '<div class="row"> \
                    <div class="col-md-1"> \
                        <label>Answer</label> \
                    </div> \
                    <div class="col-md-4"> \
                        <input class="input-answer"/> \
                    </div> \
                    <div class="col-md-1"> \
                        <label>Score</label> \
                    </div> \
                    <div class="col-md-3"> \
                        <input type="number" class="input-score"/> \
                    </div> \
                    <div class="col-md-2"> \
                        <button type="button" class="btn btn-default btn-sm btn-survey-remove-answer"> \
                        <span class="glyphicon glyphicon-minus"></span> \
                        </button> \
                    </div>\
                </div>\
                ';

    $(this).parents().closest(".form-group").append(div);
}

function deleteQuestion() {
    var questionDiv = $(this).closest(".row").parent();
    questionDiv.remove();

    questionCounter--;

    var counter = 2;
    $(".question-number").each(function() {
        $(this).text(counter);
        counter++;
    });
}

function removeAnswer() {
    $(this).parent().closest(".row").remove();
}

function addSurvey() {
    var survey = {};
    survey["title"] = $("#add-survey-title").val();
    var questions = {};

    survey["questions"] = {};
    $.each($(".question-group"), function(index, value) {
        var question = {};
        var answers = {};
        question["question"] = $(value).find(".input-question").val();
        $.each($(this).find(".answer-row"), function(i, v) {
            var answer = {};
            answer["answer"] = $(v).find(".input-answer").val();
            answer["score"] = $(v).find(".input-score").val();
            answers[i] = answer;
            question["answer"] = answer;
        });
        questions[index] = question;
    });

    survey["questions"] = questions;

    console.log("Survey", survey);

    $.ajax({
        url: "/addSurvey",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(survey),
        success: function() {},
        error: function(e) {
            console.log("ERROR", e);
        }
    });
}