$(function()
{
    $("#addWordBtn").on("click",function ()
    {
        $.ajax
        ({
            type:"post",
            url:"/addWord.wl",
            //key : value 방식으로 보냄
            data:$("#addWordForm").serialize(),
            dataType : 'json',
            success: whenSuccess2,
            error: notExistData2,
            beforeSend: loadingSkeleton,
            complete: function () {
                initStyleSetting ();
            }
        })
    })
});

function loadingSkeleton()
{
    var widthSize = $('#wordListItem').width()-28;
    var wordRdColor = document.getElementsByClassName("wordRdColor");
    var listItemSize = wordRdColor.length;
    $("#popup").fadeOut(150);
    $("#wordListItem").css("display","none");
    $("#wordListItem").fadeIn(500);
    var html = "";
    for(var i = 0; i<listItemSize+1; i++)
    {
        html += "<div class=\"skeleton-screen\" style='width:"+widthSize+"px;'></div>"
    }
    $("#wordListItem").html(html);
}

function  notExistData2(error)
{
    alert("단어가 이미 존재하거나 추가할 수 없는 단어 입니다.");
}

//값을 가져왔을때 실행
function whenSuccess2(result)
{
    $("#wordListItem").css("display","none");
    $("#wordListItem").fadeIn(500);
    var html = '';
    $.each(result, function(index,entry){
        html += '<li onclick="location.href=\'./word.w?wName='+entry.wordName+'\';">';
        html += '<span class="wordRdColor">'+entry.wordName+'</span>';
        html += '<span class="wordMean">'+entry.wordMean+'</span>';
        html += '</li>';
    });
    $("#wordListItem").html(html);
    $('#addWordForm').each(function(){
        this.reset();
    });
}