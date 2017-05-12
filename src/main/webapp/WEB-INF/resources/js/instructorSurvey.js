/**
 * Javascript for instructor to add or edir
 * a survey to a course
 * @type {number}
 */

var questionCounter = 2;
var hasSurvey = false;
$(document).ready(function(){
    // select course link onclick
    $("#select-course").change(changeCourse);

    console.log($("#survey").val());

    // set the tab links
    var url = window.location.pathname;
    var courseBaseUrl = url.slice(0, url.length-7);
    $("#course-a").attr("href", courseBaseUrl);
    $("#survey-a").attr("href", url);
    $("#videos-a").attr("href", courseBaseUrl + "/videos");
    $("#evaluation-a").attr("href", courseBaseUrl + "/dueDates");

    // Set selected course
    var currentCourseKey = courseBaseUrl.slice("/");
    $("#current-course-key").val(currentCourseKey);

    // check if the course has a survey
    if ($("#survey").val() != "") {
        loadSurvey();
        hasSurvey = true;
    }

    // Enable onclick functions
    $("#btn-add-course").on("click", addCourseModalShow);
    $("#btn-survey-add-question").on("click", addQuestion);
    $("#survey-q-group").on("click", ".btn-survey-remove-question", deleteQuestion);
    $("#survey-q-group").on("click", ".btn-survey-add-answer", addAnswer);
    $("#survey-q-group").on("click", ".btn-survey-remove-answer", removeAnswer);
    $("#survey-submit").on("click", surveySubmit);

    $("#survey-due-date").datepicker();
});

/**
 * Select option to view by course
 */
function changeCourse() {
    var currentUrl = window.location.pathname;
    var urlArr = currentUrl.split("/");
    var newCourseKey = $(this).val();
    var newUrl = "/" + urlArr[1] + "/" + newCourseKey + "/survey";

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

/**
 * Display modal for adding a course
 */
function addCourseModalShow() {
    $("#addCourseModal").modal("show");
}

/**
 * load the survey of the course
 */
function loadSurvey() {
    $("#create-survey-h1").hide();

    var survey = JSON.parse($("#survey").val());
    var properties = survey["Properties"];
    var questions = survey["Questions"];
    var qTotal = survey["QuestionTotal"];
    var $questionGroups = $(".question-group");
    var qGroupLen = $questionGroups.length;

    // set title and date
    $("#survey-title").val(properties["title"]);
    $("#survey-due-date")
        .datepicker()
        .val(properties["dueDate"]);

    // set questions and answers
    var qCounter = 0;
    var qGroupCounter = 0;
    while (qCounter < qTotal) {
        var question = questions[qCounter];
        var $qGroup;
        // question ui is available, set question, type, check answers
        if (qGroupCounter < qGroupLen) {
            $qGroup = $questionGroups.eq(qGroupCounter);
        }
        // question ui isn't available, add ui, set fields
        else {
            addQuestion();
            // update the groups
            $questionGroups = $(".question-group");
            $qGroup = $questionGroups.last();
        }
        // get question and type
        var qContent = question["QuestionProperties"]["content"];
        var qType = question["QuestionProperties"]["type"];
        // find input fields
        var $inputQuestion = $qGroup.find(".input-question");
        var $inputType = $qGroup.find(".input-type");
        // set inputs
        $inputQuestion.val(qContent);
        $inputType.val(qType);

        // set answer inputs
        var $aGroups = $qGroup.find(".answer-row");
        var aGroupLen = $aGroups.length;
        var aCounter = 0;
        var answers = question["Answers"];
        var aTotal = question["AnswerTotal"];
        while (aCounter < aTotal) {
            var aAnswer = answers[aCounter];
            var $aRow;
            // answer ui available
            if (aCounter < aGroupLen) {
                $aRow = $aGroups.eq(aCounter);
            }
            // add answer ui
            else {
                addAnswer($qGroup);
                // update the groups
                $questionGroups = $(".question-group");
                $aGroups = $qGroup.find(".answer-row");
                $aRow = $aGroups.last();
            }

            // get answer and score
            var answer = aAnswer["answer"];
            var score = aAnswer["score"];
            // find input fields
            var $inputAnswer = $aRow.find(".input-answer");
            var $inputScore = $aRow.find(".input-score");
            // set input fields
            $inputAnswer.val(answer);
            $inputScore.val(score);

            aCounter++;
        }

        qCounter++;
        qGroupCounter++;
    }
    if (qGroupLen < qTotal) {
        for (var i=qGroupLen-1; i<qTotal; i++) {
            addQuestion();
        }
    }

    // change create button to save
    $("#survey-submit").text("Save");
}

/**
 * Add a question div to the page
 */
function addQuestion() {
    var div = '<div class="question-group">\
                <div class="form-group">\
                    <label class="col-sm-2 control-label">Question \
                        <span class="question-number"></span> \
                    </label> \
                    <div class="col-md-8"> \
                        <input class="form-control input-question"/>\
                    </div>\
                    <div class="col-md-1">\
                        <button type="button" class="btn btn-default btn-sm btn-survey-remove-question">\
                            <span class="glyphicon glyphicon-minus"></span>\
                        </button>\
                    </div>\
                </div>\
                <div class="form-group">\
                    <label class="col-sm-1 control-label">Type</label> \
                    <div class="col-md-2"> \
                        <input class="form-control input-type"/> \
                    </div> \
                </div>\
                <div class="form-group answer-row">\
                    <label class="col-sm-2 control-label">Answer</label>\
                    <div class="col-md-3">\
                        <input type="text" class="input-answer"/>\
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

    $("#survey-q-group").append(div);

    questionCounter++;
    $("#survey-q-group div:last-child").find("span.question-number").text(questionCounter);
}

/**
 * Add an answer div to a question
 * @param $qGroup
 */
function addAnswer($qGroup) {
    var div = '<div class="form-group answer-row">\
                    <label class="col-sm-2 control-label">Answer</label>\
                    <div class="col-md-3">\
                        <input type="text" class="input-answer"/>\
                    </div>\
                    <label class="col-sm-1 control-label">Score</label>\
                    <div class="col-md-2">\
                        <input type="number" class="input-score"/>\
                    </div>\
                    <div class="col-md-2">\
                        <button type="button" class="btn btn-default btn-sm btn-survey-remove-answer">\
                            <span class="glyphicon glyphicon-minus"></span>\
                        </button>\
                    </div>\
                </div>';

    if ($(this).is("button"))
        $(this).parents().closest(".question-group").append(div);
    else
        $qGroup.append(div);

}

/**
 * Delete a question div
 */
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

/**
 * Delete an answer div
 */
function removeAnswer() {
    $(this).parent().closest(".answer-row").remove();
}

/**
 * Instructor submits a survey
 */
function surveySubmit() {
    var courseKey = $("#course-key").val();
    var survey = {};
    survey["courseKey"] = $('#select-course').find(":selected").val();
    survey["courseTitle"] = $("#select-course").find(":selected").text();

    survey["title"] = $("#survey-title").val();
    survey["date"] = $('#survey-due-date').val();
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

    var link = "/instructor/" + courseKey;
    if (hasSurvey)
        link = link + "/editSurvey";
    else
        link += "/addSurvey";

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
                                        <strong>All set!</strong> Survey Created!\
                                </div>';
            $(".col-md-9").prepend(successAlert);
        },
        error: function(e) {
            console.log("ERROR", e);
        }
    });
    
    location.reload();
}
