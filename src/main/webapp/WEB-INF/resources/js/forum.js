/**
 * Created by Andrea Cerini on 5/3/2017.
 */
$(document).ready(function (){
    $(".ps-modal-type-comment").addClass("pushToBack");

    // On click responses / functions
    $(".btn-thread-add").on("click", addThreadModalShow);
    $("#displayNameFull").on("click", setInThreadNameFull);
    $("#displayNameHidden").on("click", setInThreadNameHidden);

    $(".ps-type-close").on("click", onModalClose);

});


function addThreadModalShow() {
    console.log("New Thread");
    $("#add-thread-modal").removeClass("pushToBack");
    $("#add-thread-modal").addClass("pushToFront");
    $("#add-thread-modal").modal("show");

}

function setInThreadNameFull() {
    $("#displayNameHidden").removeClass("ps-btn-primary-active");
    $("#displayNameFull").addClass("ps-btn-primary-active");
    var displayFirstName = $("#displayFirstName").val();
    var displayLastName = $("#displayLastName").val();


    $("#add-thread-display-name").val(displayFirstName + " " + displayLastName);

}

function setInThreadNameHidden() {
    $("#displayNameFull").removeClass("ps-btn-primary-active");
    $("#displayNameHidden").addClass("ps-btn-primary-active");

    $("#add-thread-display-name").val("Anonymous");
}

function onModalClose(){
    $(".ps-modal-type-comment").removeClass("pushToFront");
    $(".ps-modal-type-comment").addClass("pushToBack");
}

