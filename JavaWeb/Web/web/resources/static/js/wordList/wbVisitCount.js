function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};
function visitCountAjax(wbId)
{
    $.ajax
    ({
        type:"post",
        url:getContextPath()+"/visitCoUnt.wl",
        data:"wbId="+wbId
    });
}