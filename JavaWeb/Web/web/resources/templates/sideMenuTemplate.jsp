<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/static/js/sideMenuTemplateJS.js"></script>
<link href="<%=request.getContextPath()%>/resources/static/css/sideMenuTemplateStyle.css" rel="stylesheet" type="text/css">
<div id="backgroundFade"></div>
<div id="sideBar">
    <span id="sideMenuBtn" onclick="sideMenu(this)">
        <div class="bar1"></div>
        <div class="bar2"></div>
        <div class="bar3"></div>
    </span>
    <div id="sideMenu">
        <a href="<%=request.getContextPath()%>/" id="home-menu">홈</a>
        <a href="<%=request.getContextPath()%>/wordbookList.wb" id="wordbook-menu">단어장</a>
        <a href="<%=request.getContextPath()%>/news.nw" id="news-menu">뉴스</a>
        <a href="<%=request.getContextPath()%>/openWordbook.ob" id="openwb-menu">오픈 단어장</a>
    </div>
</div>