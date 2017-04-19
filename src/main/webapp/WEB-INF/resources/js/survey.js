$(document).ready(function () {
    console.log("ready");
    $("input").on("click", markQuestionAsDone);
    $(".ps-answer").on("click", markAnswerAsChecked)
    checkAllAnswers();
});

function markQuestionAsDone() {
    console.log("clicked");
    $(this).closest(".ps-question").addClass("isAnswered");
}

function markAnswerAsChecked() {
    console.log("label clicked");
    $(this).closest(".ps-question").find(".ps-answer").removeClass('isChecked');
    $(this).addClass("isChecked");
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
function checkAllAnswers() {
    $('.ps-answer').each(function () {
        //if statement here 
        // use $(this) to reference the current div in the loop
        //you can try something like...


        if (condition) {
            console.print("All answers are checked");
        }


    });
}