/**
 * Created by Andrea Cerini on 5/10/2017.
 */
$(document).ready(function () {
    $(".ps-modal-type-comment").addClass("pushToBack");

    // On click responses / functions
    $("#ps-comment-section").on("click", ".btn-comment-edit", editCommentModalShow);
    $("#ps-comment-section").on("click", ".btn-comment-delete", deleteCommentModalShow);

    $(".btn-thread-edit").on("click", editThreadModalShow);
    $(".btn-thread-delete").on("click", deleteThreadModalShow);

    $("#displayNameFull").on("click", setInThreadNameFull);
    $("#displayNameHidden").on("click", setInThreadNameHidden);



});

function editCommentModalShow() {
    var commentKey = $(this).parents().closest(".ps-comment").find("input").val();
    var content = $(this).parent().siblings(".comment-text").text();

    $("#edit-comment-modal-key").val(commentKey);
    $("#edit-comment-content").val(content);

    if($("#edit-thread-display-name").val() == "Anonymous"){
        $("#displayNameFull").removeClass("ps-btn-primary-active");
        $("#displayNameHidden").addClass("ps-btn-primary-active");
    }
    else{
        $("#displayNameHidden").removeClass("ps-btn-primary-active");
        $("#displayNameFull").addClass("ps-btn-primary-active");
    }

    $(".ps-modal-type-comment").removeClass("pushToBack");
    $("#edit-comment-modal").addClass("pushToFront");
    $("#edit-comment-modal").modal("show");
}

function deleteCommentModalShow() {
    var commentKey = $(this).parents().closest(".ps-comment").find("input").val();
    var content = $(this).parent().siblings(".comment-text").text();

    $("#delete-comment-modal-key").val(commentKey);
    $("#delete-comment-modal-span").text(content);

    $(".ps-modal-type-comment").removeClass("pushToBack");
    $("#delete-comment-modal").addClass("pushToFront");
    $("#delete-comment-modal").modal("show");

}

function editThreadModalShow() {
    var threadKey = $(".currentThreadKey").val();
    var content = $(".currentThreadContent").text();

    $("#edit-thread-modal-key").val(threadKey);
    $("#edit-thread-modal-span").text(content);

    $(".ps-modal-type-comment").removeClass("pushToBack");
    $("#edit-thread-modal").addClass("pushToFront");
    $("#edit-thread-modal").modal("show");
}

function deleteThreadModalShow() {
    var threadKey = $(".currentThreadKey").val();
    var content = $(".currentThreadContent").text();

    $("#edit-thread-modal-key").val(threadKey);
    $("#edit-thread-modal-span").text(content);

    $(".ps-modal-type-comment").removeClass("pushToBack");
    $("#delete-thread-modal").addClass("pushToFront");
    $("#delete-thread-modal").modal("show");
}


function setInThreadNameFull() {
    $("#displayNameHidden").removeClass("ps-btn-primary-active");
    $("#displayNameFull").addClass("ps-btn-primary-active");
    var displayFirstName = $("#displayFirstName").val();
    var displayLastName = $("#displayLastName").val();

    $("#edit-thread-display-name").val(displayFirstName + " " + displayLastName);

}

function setInThreadNameHidden() {
    $("#displayNameFull").removeClass("ps-btn-primary-active");
    $("#displayNameHidden").addClass("ps-btn-primary-active");

    $("#edit-thread-display-name").val("Anonymous");

}
