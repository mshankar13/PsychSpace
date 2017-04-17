var articles = [];

$(document).ready(function(){

    $("#deleteArticleErrorModal").hide();
    $("#selectAll").on("click", deleteArticleSelectAllOnClick);
    $("#btn-delete-article").on("click", showDeleteArticleModal);
    $("#btn-delete-article-confirm").on("click", deleteArticles);
});

function deleteArticleSelectAllOnClick() {
    $("#delete-article-table").find('input:checkbox').prop('checked', this.checked);
}

function showDeleteArticleModal() {
    $("#deleteArticleModal").modal("show");

    var i = 0;
    if (("#selectAll").checked) {
        $.each($("input:checkbox"),function() {
            articles[i] = $(this).attr("id");
            i++;
        });
    }
    else {
        $.each($("input:checkbox"),function(key, value) {

            if (value.checked) {
                articles[i] = $(this).attr("id");
                i++;
            }

        })
    }

    $("#modal-delete-article-span").text(i + " articles?");
    console.log(i);
}

function deleteArticles() {
    $("#deleteArticleModal").modal("hide");

    $.ajax({
        url: "/admin_deleteArticle",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(articles),
        success: function () {
            $.each($("input:checkbox"), function (index) {
                if ($(this).attr("id") == articles[index]) {
                    $(this).closest('tr').remove();
                }
            })
        },
        error: function (error) {
            console.log(error);
            $("#deleteArticleErrorModal").show();
        }
    });
}