$(document).ready(function () {
    $(".ps-text-content").each(function () { //loop through each first

        var largest = 0;

        $(this).find(".ps-item").each(function () { //find each .titlebox within its parent (rest is the same)
            var findHeight = $(this).height();
            if (findHeight > largest) {
                largest = findHeight;
            }
        });

        $(this).find(".").css({
            "height": largest + "px"
        }); //update all .titlebox inside of this ul

    });
});