function sideMenu(x) {
    x.classList.toggle("change");
    if($("#sideMenu").css("display")=="block")
    {
        $("#sideMenu").fadeOut(500);
        $("#backgroundFade").fadeOut(500);
    }
    else
    {
        $("#sideMenu").fadeIn(500);
        $("#backgroundFade").fadeIn(500);
    }
}