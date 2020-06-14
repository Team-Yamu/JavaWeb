window.onload = function ()
{
    initStyleSetting ();
}

$(window).resize(function ()
{
    initStyleSetting ();
});

function initStyleSetting () {
    var wordRdColor = document.getElementsByClassName("wordRdColor");
    var wordMean = document.getElementsByClassName("wordMean");
    var colorArray = ["#F2C12E","#F28F16","#F25C05","#1B65A6","#F2C53D","#F2274C"];

    for(var i = 0;i<wordRdColor.length;i++)
    {
        var colorCode = colorArray[Math.round(Math.random() * (colorArray.length-1))];
        wordRdColor[i].style.background = colorCode;
        if($( window ).width()>450)
        {
            wordRdColor[i].style.width = "60px";
            wordMean[i].style.width = $("#wordList ul li").width() - wordRdColor[i].clientWidth - 10 + "px";
            if((wordRdColor[i].textContent).length>=7)
            {
                wordRdColor[i].style.width = 80+ (((wordRdColor[i].textContent).length-7)*10)+"px";
                wordMean[i].style.width = $("#wordList ul li").width() - (90+ (((wordRdColor[i].textContent).length-7)*10)) + "px";
            }
            wordMean[i].style.display = "inline-block";
        }
        else
        {
            wordMean[i].style.display = "none";
            wordRdColor[i].style.width = "100%";
        }
    }
}
