$(document).ready(function () {
    $(window).resize();
});

$(window).resize('resize', function (event) {
    //  if ($(window).width() > 720) {
    scaleBoxes();
    ///  }
});

function scaleBoxes() {
    var largest_col = 0;
    var largest_well = 0;

    $(document).find(".col-lg-4").each(function () { //find each .titlebox within its parent (rest is the same)
        var findHeight = $(this).height();
        if (findHeight > largest_col) {
            largest_col = findHeight;
        }
    });

    $(document).find(".col-lg-4").css({
        "height": largest_col + "px",
        "margin-bottom": "1em"
    }); //update all .titlebox inside of this ul

    $(document).find(".ps-well").css({
        "height": largest_col + "px",
        "margin-bottom": "1em"
    }); //update all .titlebox inside of this ul

    $(document).find("h2").each(function () { //find each .titlebox within its parent (rest is the same)
        var findHeight = $(this).height();
        if (findHeight > largest_well) {
            largest_well = findHeight;
        }
    });

    $(document).find("h2").css({
        "height": largest_well + "px",
        "display": "table-cell",
        "vertical-align": "bottom",
        "width": "100vw"
    });

}