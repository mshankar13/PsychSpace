$(document).ready(function () {
    // Ready conditions
    $(".ps-modal-type-comment").addClass("pushToBack");
    if ($("#ps-input-liked").val() == "true") {
        $("#btn-ps-feature-like").addClass("liked");
    } else {
        $("#btn-ps-feature-like").addClass("unliked");
    }

    // On Click functions
    $(".btn-comment-edit").on("click", editCommentModalShow);
    $(".btn-comment-delete").on("click", deleteCommentModalShow);
    $("#btn-ps-feature-like").on("click", editLikeShow);
    // click close button
    $(".ps-type-close").on("click", closeModal);
});

function closeModal() {
    $(".ps-modal-type-comment").removeClass("pushToFront");
    $(".ps-modal-type-comment").addClass("pushToBack");
}

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
    var commentKey = $(this).siblings()[1].value;
    var content = $(this).parent().closest(".ps-comment").find("p").text();

    $("#edit-comment-modal-key").val(commentKey);
    $("#edit-comment-content").val(content);

    $(".ps-modal-type-comment").removeClass("pushToBack");
    $(".ps-modal-type-comment").addClass("pushToFront");
    $("#edit-comment-modal").modal("show");

}

function deleteCommentModalShow() {
    var commentKey = $(this).siblings()[1].value;
    var content = $(this).parent().closest(".ps-comment").find("p").text();

    $("#delete-comment-modal-key").val(commentKey);
    $("#delete-comment-modal-span").text(content);

    $(".ps-modal-type-comment").removeClass("pushToBack");
    $(".ps-modal-type-comment").addClass("pushToFront");
    $("#delete-comment-modal").modal("show");

}