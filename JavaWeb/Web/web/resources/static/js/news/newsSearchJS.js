var flg = true;
function moreBtn() {
    if(flg == true)
    {
        $('#newsMainTitle').css('display', 'block');
        $('#newsMain').fadeIn(200);
        flg = false;
        var str = this.innerHTML;
        str = str.replace('전문보기','닫기');
        this.innerHTML = str;
    }
    else
    {
        $('#newsMainTitle').css('display', 'none');
        $('#newsMain').fadeOut(200);
        flg = true;
        var str = this.innerHTML;
        str = str.replace('닫기','전문보기');
        this.innerHTML = str;
    }
}