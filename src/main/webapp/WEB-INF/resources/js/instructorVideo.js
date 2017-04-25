$(document).ready(function() {
    $("#edit-video-table").on("click", ".btn-edit-video", editVideoModalShow);
    $("#delete-video-table").on("click", ".btn-delete-video", deleteVideoModalShow);
});

function editVideoModalShow() {
    var row = $(this).closest("tr");
    var title = row.find(".video-title").val();
    var link = row.find(".video-link").val();
    var videoKey = row.find(".video-key").val();
    var courseKey = row.find(".video-course-key").val();

    // set form data
    $("#edit-video-span").text(title);
    $("#edit-video-title").val(title);
    $("#edit-video-link").val(link);
    $("#edit-video-key").val(videoKey);

    // $("#edit-video-course-key select option").filter(function() {
    //     return $(this).val() == courseKey;
    // }).prop('selected', true);

    $("#editVideoModal").modal("show");
}

function deleteVideoModalShow() {
    var row = $(this).closest("tr");
    var title = row.find(".video-title").val();
    var link = row.find(".video-link").val();
    var videoKey = row.find(".video-key").val();
    var courseKey = row.find(".course-key").val();

    $("#delete-video-span").text(title);
    $("#delete-video-title").val(title);
    $("#delete-video-link").val(link);
    $("#delete-video-key").val(videoKey);
    $("#delete-video-course-key").val(courseKey);

    $("#deleteVideoModal").modal("show");
}