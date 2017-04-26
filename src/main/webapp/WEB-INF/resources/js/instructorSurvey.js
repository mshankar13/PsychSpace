//TODO: 1. no course, no survey

var questionCounter = 2;
$(document).ready(function(){
    $("#btn-survey-add-question").on("click", addQuestion);
    $("#add-survey-q-group").on("click", ".btn-survey-remove-question", deleteQuestion);
    $("#add-survey-q-group").on("click", ".btn-survey-add-answer", addAnswer);
    $("#add-survey-q-group").on("click", ".btn-survey-remove-answer", removeAnswer);
    $("#add-survey-due-date").datepicker();
    $("#add-survey-submit").on("click", addSurveySubmit);


    $("edit-survey-btn").on("click", editSurveyModalShow);
});

//--------------------------- Add Survey --------------------------
function addQuestion() {
    var div = '<div class="question-group">\
                <div class="form-group">\
                    <label class="col-sm-2 control-label">Question \
                        <span class="question-number"></span> \
                    </label> \
                    <div class="col-md-6"> \
                        <input class="form-control input-question"/>\
                    </div>\
                    <label class="col-sm-1 control-label">Type</label> \
                    <div class="col-md-2"> \
                        <input class="form-control input-type"/> \
                    </div> \
                    <div class="col-md-1">\
                        <button type="button" class="btn btn-default btn-sm btn-survey-remove-question">\
                            <span class="glyphicon glyphicon-minus"></span>\
                        </button>\
                    </div>\
                </div>\
                <div class="form-group answer-row">\
                    <label class="col-sm-2 control-label">Answer</label>\
                    <div class="col-md-4">\
                        <input class="input-answer"/>\
                    </div>\
                    <label class="col-sm-1 control-label">Score</label>\
                    <div class="col-md-3">\
                        <input type="number" class="input-score"/>\
                    </div>\
                    <div class="col-md-2">\
                        <button type="button" class="btn btn-default btn-sm btn-survey-add-answer">\
                            <span class="glyphicon glyphicon-plus"></span>\
                        </button>\
                    </div>\
                </div>\
            </div>';

    $("#add-survey-q-group").append(div);

    questionCounter++;
    $("#add-survey-q-group div:last-child").find("span.question-number").text(questionCounter);
}

function addAnswer() {
    var div = '<div class="form-group answer-row">\
                    <label class="col-sm-2 control-label">Answer</label>\
                    <div class="col-md-4">\
                        <input class="input-answer"/>\
                    </div>\
                    <label class="col-sm-1 control-label">Score</label>\
                    <div class="col-md-3">\
                        <input type="number" class="input-score"/>\
                    </div>\
                    <div class="col-md-2">\
                        <button type="button" class="btn btn-default btn-sm btn-survey-remove-answer">\
                            <span class="glyphicon glyphicon-minus"></span>\
                        </button>\
                    </div>\
                </div>';

    $(this).parents().closest(".question-group").append(div);
}

function deleteQuestion() {
    var questionDiv = $(this).closest(".question-group");
    questionDiv.remove();

    questionCounter--;

    var counter = 2;
    $(".question-number").each(function() {
        $(this).text(counter);
        counter++;
    });
}

function removeAnswer() {
    $(this).parent().closest(".answer-row").remove();
}

function addSurveySubmit() {
    var survey = {};
    survey["courseKey"] = $('#select-course').find(":selected").val();
    survey["courseTitle"] = $("#select-course").find(":selected").text();

    survey["title"] = $("#add-survey-title").val();
    survey["date"] = $('#add-survey-due-date').val();
    var questions = {};

    survey["questions"] = {};
    var qCount = 0;
    $.each($(".question-group"), function(index, value) {
        var question = {};
        var answers = {};
        var aCount = 0;
        question["question"] = $(value).find(".input-question").val();
        question["type"] = $(value).find(".input-type").val();
        $.each($(this).find(".answer-row"), function(i, v) {
            var answer = {};
            answer["answer"] = $(v).find(".input-answer").val();
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

    console.log("Survey", survey);

    $.ajax({
        url: "/addSurvey",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(survey),
        success: function() {
            var successAlert = '<div class="alert alert-success alert-dismissible" role="alert"> \
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">\
                                        <span aria-hidden="true">&times;</span></button> \
                                        <strong>All set!</strong> Survey Created!\
                                </div>';
            $(".col-md-9").prepend(successAlert);
        },
        error: function(e) {
            console.log("ERROR", e);
        }
    });
}

//--------------------------- Edit Survey --------------------------

function editSurveyModalShow() {

}

function editSurveySubmit() {

}