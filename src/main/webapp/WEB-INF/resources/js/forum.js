/**
 * Created by Andrea Cerini on 5/3/2017.
 */
$(document).ready(function () {
    $(".btn-thread-add").on("click", addThreadModalShow);
})


function addThreadModalShow() {
    console.log("New Thread");
    $(".ps-modal-type-comment").removeClass("pushToBack");
    $(".ps-modal-type-comment").addClass("pushToFront");
    $("#add-thread-modal").modal("show");
}