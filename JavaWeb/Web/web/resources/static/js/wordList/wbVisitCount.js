function visitCountAjax(wbId)
{
    $.ajax
    ({
        type:"post",
        url:"/visitCoUnt.wl",
        data:"wbId="+wbId
    });
}