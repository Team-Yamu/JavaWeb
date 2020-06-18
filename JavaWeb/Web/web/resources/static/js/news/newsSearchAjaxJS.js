function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};

$(function()
{
    $("#searchNewsBtn").on("click",function ()
    {
        $.ajax
        ({
            type:"post",
            url:getContextPath()+"/newsParse.nw",
            //key : value 방식으로 보냄
            data:$("#search").serialize(),
            dataType : 'json',
            success: whenSuccess,
            error: notExistData,
            beforeSend: loadingPopup
        })
    })
});

function loadingPopup()
{
    $('#popup').fadeIn(200);
    StartClock();
}

function notExistData(error)
{
   $("#searchMyNews-view").css("display","none");
    $('#popup').fadeOut(200);
    StopClock();
}

//값을 가져왔을때 실행
function whenSuccess(result)
{

    $('#popup').fadeOut(200);
    $("#searchMyNews-view").css("display","block");
    StopClock();
    var html = '';
    $.each(result, function(index,entry){
        html += '<div id="newsTitle">'+entry.title+'</div>';
        html+= '<div id="newsImage"><img src="data:image/png;base64,'+entry.base64_top_img+'"></div>';
        html += '<div id="newsSummaryTitle">요약</div>';
        html += '<div id="newsSummary">'+entry.summary+'</div>';
        html += '<div id="newsMain"><div id="newsMainTitle">전문</div>';
        html += entry.text;
        html += '<div id="moreNews"><button>전문보기</button></div>';
    });
    $("#searchMyNews-view").html(html);
    $('#search').each(function(){
        this.reset();
    });
}

function StartClock() {
    loading();
    timerId = setInterval(loading, 1000);
}
function loading() {
    $('#popup p a').animate({marginLeft: '15px'}, 700);
    $('#popup p a').animate({marginLeft: '0px'}, 700);
}
function StopClock() {
    if(timerId != null) {

        clearInterval(timerId);
    }
}