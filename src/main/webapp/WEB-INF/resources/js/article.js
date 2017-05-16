$(document).ready(function () {
    // Ready conditions
    $(".ps-modal-type-comment").addClass("pushToBack");

    // On Click functions
    $("#ps-comment-section").on("click", ".btn-comment-edit", editCommentModalShow);
    $("#ps-comment-section").on("click", ".btn-comment-delete", deleteCommentModalShow);

    $(".ps-type-close").on("click", onModalClose);
});

function editLikeShow() {
    if ($("#ps-input-liked").hasClass("liked")) {
        $("#btn-ps-feature-like").removeClass("liked");
        $("#btn-ps-feature-like").addClass("unliked");
    } else {
        $("#btn-ps-feature-like").addClass("liked");
        $("#btn-ps-feature-like").removeClass("unliked");
    }
}

function editCommentModalShow() {
    var commentKey = $(this).parents().closest(".ps-comment").find("input").val();
    var content = $(this).parent().siblings(".comment-text").text();

    $("#edit-comment-modal-key").val(commentKey);
    $("#edit-comment-content").val(content);

    $("#edit-comment-modal").removeClass("pushToBack");
    $("#edit-comment-modal").addClass("pushToFront");
    $("#edit-comment-modal").modal("show");
}

function deleteCommentModalShow() {
    var commentKey = $(this).parents().closest(".ps-comment").find("input").val();
    var content = $(this).parent().siblings(".comment-text").text();

    $("#delete-comment-modal-key").val(commentKey);
    $("#delete-comment-modal-span").text(content);

    $("#delete-comment-modal").removeClass("pushToBack");
    $("#delete-comment-modal").addClass("pushToFront");
    $("#delete-comment-modal").modal("show");
}

function onModalClose(){
    $(".ps-modal-type-comment").removeClass("pushToFront");
    $(".ps-modal-type-comment").addClass("pushToBack");
}
