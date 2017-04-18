var articles = [];

$(document).ready(function(){

    $('#delete-article-table tr').on("click", rowClickCheckboxTrigger);
    $("#deleteArticleErrorModal").hide();
    $("#selectAll").on("click", deleteArticleSelectAllOnClick);
    $("#btn-delete-article").on("click", showDeleteArticleModal);
    $("#btn-delete-article-confirm").on("click", deleteArticles);
});

function rowClickCheckboxTrigger(event) {
    if (event.target.type !== 'checkbox') {
        $(':checkbox', this).trigger('click');
    }
}

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

    var article = " article?";
    if (i > 1)
        article = " articles?";
    $("#modal-delete-article-span").text(i + article);
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
            var i = 0;
            $.each($("input:checkbox"), function (index) {
                if ($(this).attr("id") == articles[i]) {
                    $(this).closest('tr').remove();
                    i++;
                }
            })
        },
        error: function (error) {
            console.log(error);
            $("#deleteArticleErrorModal").show();
        }
    });
}