$(document).ready(function () {
    // For habit js

    $("#ps-cue").show();
    $("#ps-resp").hide();
    $("#ps-rew").hide();

    var piemenu = new wheelnav('piemenu');
    piemenu.wheelRadius = piemenu.wheelRadius * 0.83;
    piemenu.createWheel();
    piemenu.navItems[0].navSlice.mouseup(function () {
        $("#ps-cue").show();
        $("#ps-resp").hide();
        $("#ps-rew").hide();
    });
    piemenu.navItems[1].navSlice.mouseup(function () {
        $("#ps-cue").hide();
        $("#ps-resp").show();
        $("#ps-rew").hide();
    });
    piemenu.navItems[2].navSlice.mouseup(function () {
        $("#ps-cue").hide();
        $("#ps-resp").hide();
        $("#ps-rew").show();
    });
});