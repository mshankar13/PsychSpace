$(document).ready(function() {
    // set the tab links
    var url = window.location.pathname;
    var courseBaseUrl = url.slice(0, url.length-7);
    $("#course-a").attr("href", courseBaseUrl);
    $("#survey-a").attr("href", courseBaseUrl + "/survey");
    $("#videos-a").attr("href", url);
    $("#evaluation-a").attr("href", courseBaseUrl + "/evaluations");

    $("#btn-add-video").on("click", addVideoModalShow);
    $("#videos-div").on("click", ".edit-video-btn", editVideoModalShow);
    $("#videos-div").on("click", ".delete-video-btn", deleteVideoModalShow);
});

function addVideoModalShow() {
    $("#addVideoModal").modal("show");
}

function editVideoModalShow() {
    var div = $(this).closest("div");
    var title = div.parent().find(".video-title").val();
    var link = div.parent().find(".video-link").val();
    var videoKey = div.parent().find(".video-key").val();
    var courseKey = div.parent().find(".video-course-key").val();
    var courseTitle = div.parent().find(".video-course-title").val();

    // set form data
    $("#edit-video-span").text(title);
    $("#edit-video-title").val(title);
    $("#edit-video-link").val(link);
    $("#edit-video-key").val(videoKey);
    $("#edit-course-key").val(courseKey);
    $("#edit-video-course-title").val(courseTitle);

    $("#editVideoModal").modal("show");
}

function deleteVideoModalShow() {
    var div = $(this).closest("div");
    var title = div.parent().find(".video-title").val();
    var link = div.parent().find(".video-link").val();
    var videoKey = div.parent().find(".video-key").val();
    var courseKey = div.parent().find(".video-course-key").val();
    var courseTitle = div.parent().find(".video-course-title").val();

    // set form data
    $("#delete-video-span").text(title);
    $("#delete-video-title").val(title);
    $("#delete-video-link").val(link);
    $("#delete-video-key").val(videoKey);
    $("#delete-course-key").val(courseKey);
    $("#delete-video-course-title").val(courseTitle);

    $("#deleteVideoModal").modal("show");
}