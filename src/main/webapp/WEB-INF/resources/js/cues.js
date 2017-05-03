/**
 * Created by Andrea Cerini on 5/3/2017.
 */
$(document).ready(function () {
    $(".ps-modal-type-comment").addClass("pushToBack");
    placeholderPositive();
    $(".btn-cue-add").on("click", addCueModalShow);
    $(".btn-cue-positive").on("click", placeholderPositive);
    $(".btn-cue-negative").on("click", placeholderNegative);
});

function addCueModalShow() {
    $(".ps-modal-type-comment").removeClass("pushToBack");
    $(".ps-modal-type-comment").addClass("pushToFront");
    $("#add-cue-modal").modal("show");
}

function placeholderPositive() {
    $("#cues-cue-name").attr("placeholder","Example: Going to the library.");
    $("#cues-cue-location").attr("placeholder","Example: In the library.");
    $("#cues-cue-time").attr("placeholder","Example: 2:00pm.");
    $("#cues-cue-feelings").attr("placeholder","Example: I felt like I could focus and I was getting work done.");
    $("#cues-cue-environment").attr("placeholder"," Example: Other people who were studying.");
    $("#cues-cue-action").attr("placeholder","Example: I left my phone in my backpack.");
}

function placeholderNegative() {
    $("#cues-cue-name").attr("placeholder","Example: Being alone in my room.");
    $("#cues-cue-location").attr("placeholder","Example: In my room.");
    $("#cues-cue-time").attr("placeholder","Example: 1:00am.");
    $("#cues-cue-feelings").attr("placeholder","Example: I was stressed that I could not understand the material.");
    $("#cues-cue-environment").attr("placeholder"," Example: I was alone.");
    $("#cues-cue-action").attr("placeholder","Example: I received a text.");
}