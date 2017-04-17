$(document).ready(function(){
    //$('#edit-date').datepicker({ dateFormat: 'yyyy-mm-dd' });
    $(".btn-edit-article").on("click", editArticleModalShow);
});

function editArticleModalShow() {
    var row = $(this).closest("tr");

    var key = row.find("#newsKey").val();
    var title = row.find("#title").val();
    var author = row.find("#author").val();
    var date = row.find("#date").val();
    var content = row.find("#content").val();
    var likesCount = row.find("#likesCount").val();

    // set form data
    $("#edit-article-title").text(title);
    $("#news-title").val(title);
    $("#edit-author").val(author);
    $("#edit-date").datepicker();
    $("#edit-date").datepicker("setDate", date);
    //$("#edit-date").text(date);
    $("#edit-content").val(content);
    $("#linkedCount").val(likesCount);
    $("#key").val(key);

    $("#editArticleModal").modal("show");

}

