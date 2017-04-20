$(document).ready(function () {
    $("input").on("click", markQuestionAsDone);
    $(".ps-answer").on("click", markAnswerAsChecked)
});

// Add isAnswered class to the completed question when one of its answers are checked
function markQuestionAsDone() {
    console.log("clicked");
    $(this).closest(".ps-question").addClass("isAnswered");
    checkAllAnswers();
}

// Add isChecked class to the checked answer and make sure the class is removed for the previous checked answer
function markAnswerAsChecked() {
    console.log("label clicked");
    $(this).closest(".ps-question").find(".ps-answer").removeClass('isChecked');
    $(this).addClass("isChecked");
}


// Check that all questions have been answered before making the submit button clickable
function checkAllAnswers() {
    var isAllAnswered = true;
    $('.ps-question').each(function () {
        if (!$(this).is(".isAnswered")) {
            console.print("All answers are not checked");
            isAllAnswered = false;
        }
    });
    if(isAllAnswered == true){
        $('#btn-submit-survey').disable(false)
    }
}


/*
function handleQuestions() {
    var answerID, answerName;
    var q, a = 0;
    $(this).find(".ps-question").each(function () {
        console.print(this);
        $(this).find(".ps-answer").each(function () {
            answerName = "r-group-" + q;
            answerID = "ps-a-" + a;
            $(this).find("input").attr('id', answerID);
            $(this).find("input").attr('for', answerID);
            a = a + 1;
        });
        a = 0;
        q = q + 1;
    });

    
//    int q = 0
//    int a = 0
//    For each ps-question of ps-all-questions
//        Give id to ps-question: id = ps-q[q]
//        a = 0
//        For each ps-answer of ps-question
//            Give id to input: radio-[a]
//            Give for to label: radio-[a]
//            Give name to input: r-group-[q]
//            a = a + 1
    
}
*/