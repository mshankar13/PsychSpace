$(document).ready(function(){
    $("#instructor").on("click", ".btn-instructor-approve", instructorApprovalModalShow);
    $("#instructor").on("click", ".btn-instructor-deny", instructorDenyModalShow);
    $("#admin").on("click", ".btn-admin-approve", adminApprovalModalShow);
    $("#admin").on("click", ".btn-admin-deny", adminDenyModalShow);
});

function instructorApprovalModalShow() {
    var $row = $(this).closest("tr");

    var userName = $row.find(".user-name").text();
    var userKey = $row.find(".user-key").val();

    $("#approve-instructor-user-key").val(userKey);
    $("#approve-instructor-applicant-name").text(userName);

    $("#instructorApprovalModal").modal("show");
}

function instructorDenyModalShow() {
    var $row = $(this).closest("tr");

    var userName = $row.find(".user-name").text();
    var userKey = $row.find(".user-key").val();

    $("#deny-instructor-user-key").val(userKey);
    $("#deny-instructor-applicant-name").text(userName);

    $("#instructorDenyModal").modal("show");
}

function adminApprovalModalShow() {
    var $row = $(this).closest("tr");

    var userName = $row.find(".user-name").text();
    var userKey = $row.find(".user-key").val();

    $("#approve-admin-user-key").val(userKey);
    $("#approve-admin-applicant-name").text(userName);

    $("#adminApprovalModal").modal("show");
}

function adminDenyModalShow() {
    var $row = $(this).closest("tr");

    var userName = $row.find(".user-name").text();
    var userKey = $row.find(".user-key").val();

    $("#deny-admin-user-key").val(userKey);
    $("#deny-admin-applicant-name").text(userName);

    $("#adminDenyModal").modal("show");
}