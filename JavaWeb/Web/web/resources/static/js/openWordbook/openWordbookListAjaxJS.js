var count = 0;
$(window).load(function ()
{
    wordbookListAjax("");
});

$(function()
{
    $("#moreBtn").on("click",function ()
    {
        if($('#searchWordbook').val() == "")
        {
            wordbookListAjax("");
        }
        else
        {
            wordbookListAjax($('#searchWordbook').val());
        }
    })

    $("#searchWordbook").keyup(function ()
    {
        count = 0;
        wordbookListAjax($('#searchWordbook').val());
    })
});

function wordbookListAjax(search)
{
    $.ajax
    ({
        type:"post",
        url:"/wordbookList.ob",
        //key : value 방식으로 보냄
        data:"count="+count+"&searchBook="+search,
        dataType : 'json',
        success: whenSuccess,
        complete: function ()
        {
            randomBgColor ();
            count++;
        }
    })
}

function whenSuccess(result)
{
    var i = 0;
    var html = '';
    $.each(result, function(index,entry)
    {
        html += '<li>';
        html += '<button onclick="location.href=\'./wordList.wl?wbId='+entry.id+'&wbName='+entry.name+'\'">';
        html += '<span class="wbName">'+entry.name+'</span>';
        html += '<span class="wbInfo">'+entry.info+'</span>';
        html += '</button>';
        html += '</li>';
        i++;
    });
    if(i != 10)
    {
        $('#moreBtn').css("display","none");
    }
    else
    {
        $('#moreBtn').css("display","block");
    }

    if(count == 0)
    {
        $("#wordbookItems").html(html);
    }
    else
    {
        $("#wordbookItems").append(html);
    }
}