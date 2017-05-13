$(document).ready(function() {

    $("#delete-account-btn").on("click", deleteAccountModalShow);

});

function deleteAccountModalShow() {
    $("#set-user-key").val($("#user-key").val());

    $("#deleteAccountModal").modal("show");
}
