$(function()
{
    $("#addWord-btn").on("click",function (e)
    {
        $("#popup").fadeIn(150); e.preventDefault();
    })
    $("#closePopupBtn").on("click",function (e)
    {
        $("#popup").fadeOut(150); e.preventDefault();
    })
    $(document).on("click",".sampleWord",function()
    {
        $("#searchWord").val($(this).text());
    });
});