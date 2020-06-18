$(function()
{
    $('#moreNews button').on('click',function() {
        if ($(this).text() == "닫기") {
            $('#newsMainTitle').css('display', 'none');
            $('#newsMain').fadeOut(200);
            $(this).text('전문보기');
        } else {
            $('#newsMainTitle').css('display', 'block');
            $('#newsMain').fadeIn(200);
            $(this).text('닫기');
        }
    });
})