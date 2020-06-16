$(window).load(function ()
{
    randomBgColor ();
});

function randomBgColor () {
    var wordbookBtn = document.getElementsByClassName("wordbookBtn");
    var colorArray = ["#0018A8","#0283F4","#0158A6","#3B59FF"];

    for(var i = 0;i<wordbookBtn.length;i++)
    {
        var colorCode = colorArray[Math.round(Math.random() * (colorArray.length-1))];
        wordbookBtn[i].style.background = colorCode;
    }
}