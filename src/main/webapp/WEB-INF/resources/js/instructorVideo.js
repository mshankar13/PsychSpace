$(document).ready(function() {
    // set the tab links
    var url = window.location.pathname;
    var courseBaseUrl = url.slice(0, url.length-7);
    $("#course-a").attr("href", courseBaseUrl);
    $("#survey-a").attr("href", courseBaseUrl + "/survey");
    $("#videos-a").attr("href", url);
    $("#evaluation-a").attr("href", courseBaseUrl + "/evaluations");

    $("#btn-add-video").on("click", addVideoModalShow);
    $("#edit-video-table").on("click", ".btn-edit-video", editVideoModalShow);
    $("#delete-video-table").on("click", ".btn-delete-video", deleteVideoModalShow);
});

function addVideoModalShow() {
    $("#addVideoModal").modal("show");
}

function editVideoModalShow() {
    var row = $(this).closest("tr");
    var title = row.find(".video-title").val();
    var link = row.find(".video-link").val();
    var videoKey = row.find(".video-key").val();
    var courseKey = row.find(".video-course-key").val();
    var courseTitle = row.find(".video-course-title").val();

    // set form data
    $("#edit-video-span").text(title);
    $("#edit-video-title").val(title);
    $("#edit-video-link").val(link);
    $("#edit-video-key").val(videoKey);

    $("#edit-video-course-key select").val(courseKey);

    $("#editVideoModal").modal("show");
}

function deleteVideoModalShow() {
    var row = $(this).closest("tr");
    var title = row.find(".video-title").val();
    var link = row.find(".video-link").val();
    var videoKey = row.find(".video-key").val();
    var courseKey = row.find(".course-key").val();
    var courseTitle = row.find(".course-title").val();

    $("#delete-video-span").text(title);
    $("#delete-video-title").val(title);
    $("#delete-video-link").val(link);
    $("#delete-video-key").val(videoKey);
    $("#delete-video-course-key").val(courseKey);
    $("#delete-video-course-title").val(courseTitle);

    $("#deleteVideoModal").modal("show");
}