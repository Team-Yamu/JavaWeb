function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};
$(function()
{
    $("#searchWord").keyup(function ()
    {
        $.ajax
        ({
            type:"post",
            url:getContextPath()+"/searchWordList.wl",
            //key : value 방식으로 보냄
            data:$("#addWordForm").serialize(),
            dataType : 'json',
            success: whenSuccess,
        });
    })
});

//값을 가져왔을때 실행
function whenSuccess(result)
{
    var html = '';
    $.each(result, function(index,entry){
        html += '<li class="sampleWord">'+entry.wordName+'</li>';
    });
    $("#wordSampleList").html(html);
}