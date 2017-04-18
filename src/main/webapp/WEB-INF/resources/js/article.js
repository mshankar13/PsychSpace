$(document).ready(function () {
    $(".btn-comment-edit").on("click", editCommentModalShow);
    $(".btn-comment-delete").on("click", deleteCommentModalShow);
});


function editCommentModalShow() {
    var commentKey = $(this).siblings()[1].value;
    var content = $(this).parent().closest(".ps-comment").find("p").text();

    $("#edit-comment-modal-key").val(commentKey);
    $("#edit-comment-content").val(content);

    $("#edit-comment-modal").modal("show");
}

function deleteCommentModalShow() {
    var commentKey = $(this).siblings()[1].value;
    var content = $(this).parent().closest(".ps-comment").find("p").text();

    $("#delete-comment-modal-key").val(commentKey);
    $("#delete-comment-modal-span").text(content);

    $("#delete-comment-modal").modal("show");
}