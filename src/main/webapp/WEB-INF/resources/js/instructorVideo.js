/**
 * Javascript for the course video page for
 * an instructor view
 */

$(document).ready(function() {
    // select course link onclick
    $("#select-course").change(changeCourse);

    // set the tab links
    var url = window.location.pathname;
    var len = "/videos".length;
    var courseBaseUrl = url.slice(0, url.length-len);
    $("#course-a").attr("href", courseBaseUrl);
    $("#survey-a").attr("href", courseBaseUrl + "/survey");
    $("#videos-a").attr("href", url);
    $("#evaluation-a").attr("href", courseBaseUrl + "/dueDates");

    // enable the button onclick functions
    $("#btn-add-course").on("click", addCourseModalShow);
    $("#btn-add-video").on("click", addVideoModalShow);
    $("#videos-div").on("click", ".edit-video-btn", editVideoModalShow);
    $("#videos-div").on("click", ".delete-video-btn", deleteVideoModalShow);
});

/**
 * Select option to view by course
 */
function changeCourse() {
    var currentUrl = window.location.pathname;
    var urlArr = currentUrl.split("/");
    var newCourseKey = $(this).val();
    var newUrl = "/" + urlArr[1] + "/" + newCourseKey + "/videos";

    $.ajax({
        url: newUrl,
        type: "GET",
        timeout : 15000,
        success: function() {
            window.location.href = newUrl;
        },
        error: function() {
            console.log("ERROR");
        }
    });
}

/**
 * Display the modal for adding a video
 */
function addVideoModalShow() {
    $("#addVideoModal").modal("show");
}

/**
 * Display the modal for editing a video
 */
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

/**
 * Display the modal for delete a video
 */
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

/**
 * Display a modal for adding a course
 */
function addCourseModalShow() {
    $("#addCourseModal").modal("show");
}