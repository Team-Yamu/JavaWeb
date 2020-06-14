$(function()
{
    $("#addWordbookBtn").on("click",function ()
    {
        $.ajax
        ({
            type:"post",
            url:"/wordbookAdd.wb",
            //key : value 방식으로 보냄
            data:$("#addWordbook").serialize(),
            dataType : 'json',
            success: whenSuccess,
            error: notExistData
        })
    })
});

function  notExistData(error)
{
    alert("해당 단어장이 이미 존재합니다.");
}

//값을 가져왔을때 실행
function whenSuccess(result)
{
    var html = '';
    var count = 0;
    $.each(result, function(index,entry){
        html += '<div class="grid-item">';
        html += '<button onclick="location.href=\'./wordList.wl?wbId='+entry.id+'&wbName='+entry.wordbookName+'\'"';
        html += ' class="wordbookBtn" id="randomColor'+(count++)+'">';
        html += entry.wordbookName + '</button>';
        html += '</div>';
    });
    $("#wordbookList").html(html);
    randomBgColor ();
    $("#popup").fadeOut(150);
    $('#addWordbook').each(function(){
        this.reset();
    });
}

//랜덤색상 칠하기
function randomBgColor () {
    var wordbookBtn = document.getElementsByClassName("wordbookBtn");
    var colorArray = ["#0018A8","#0283F4","#0158A6","#3B59FF"];

    for(var i = 0;i<wordbookBtn.length;i++)
    {
        var colorCode = colorArray[Math.round(Math.random() * (colorArray.length-1))];
        wordbookBtn[i].style.background = colorCode;
    }
}

$(function()
{
    $("#addBtn").click(function ()
    {
        $("#popup").fadeIn(150);
    })

    $("#closePopupBtn").on("click",function ()
    {
        $("#popup").fadeOut(150);
        $('#addWordbook').each(function(){
            this.reset();
        });
    })
});