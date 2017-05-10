/**
 * Created by Andrea Cerini on 5/3/2017.
 */
$(document).ready(function () {
    $(".ps-modal-type-comment").addClass("pushToBack");
    // placeholderPositive();
    $(".btn-thread-add").on("click", addThreadModalShow);
    $("#ps-comment-section").on("click", ".btn-comment-edit", editCommentModalShow);
    $("#ps-comment-section").on("click", ".btn-comment-delete", deleteCommentModalShow);
    // $(".threadListTitle").on("click", displayThread);
})

//
// function displayThread() {
//     var threadKey = $(this).find(".threadListThreadKey").val();
//     var courseKey = $(this).find(".threadListCourseKey").val();
//     var userKey = $(this).find(".threadListUserKey").val();
//     var inThreadName = $(this).find(".threadListInThreadName").val();
//     var title = $(this).find(".threadListTitle").val();
//     var content = $(this).find(".threadListThreadKey").val();
//     var date = $(this).find(".threadListThreadKey").val();
// }

function addThreadModalShow() {
    console.log("New Thread");
    $(".ps-modal-type-comment").removeClass("pushToBack");
    $(".ps-modal-type-comment").addClass("pushToFront");
    $("#add-thread-modal").modal("show");
}

function editCommentModalShow() {
    var commentKey = $(this).parents().closest(".ps-comment").find("input").val();
    var content = $(this).parent().siblings(".comment-text").text();

    $("#edit-comment-modal-key").val(commentKey);
    $("#edit-comment-content").val(content);

    $(".ps-modal-type-comment").removeClass("pushToBack");
    $(".ps-modal-type-comment").addClass("pushToFront");
    $("#edit-comment-modal").modal("show");
}

function deleteCommentModalShow() {
    var commentKey = $(this).parents().closest(".ps-comment").find("input").val();
    var content = $(this).parent().siblings(".comment-text").text();

    $("#delete-comment-modal-key").val(commentKey);
    $("#delete-comment-modal-span").text(content);

    $(".ps-modal-type-comment").removeClass("pushToBack");
    $(".ps-modal-type-comment").addClass("pushToFront");
    $("#delete-comment-modal").modal("show");

}