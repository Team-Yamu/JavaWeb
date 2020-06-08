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
            success: whenSuccess
        })
    })
});

function whenSuccess(result){
    var html = '<div class="grid-item"><button id="addBtn">+</button></div>';
    var count = 0;
    $.each(result, function(index,entry){
        html += '<div class="grid-item">';
        html += '<button onclick="location.href=\'./wordList.wl?wbid='+entry.id+'"';
        html += ' class="wordbookBtn" id="randomColor'+(count++)+'">';
        html += entry.wordbookName + '</button>';
        html += '</div>';
    });
    $("#wordbookList").html(html);
}