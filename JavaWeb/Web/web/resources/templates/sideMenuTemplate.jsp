<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="/resources/static/js/sideMenuTemplateJS.js"></script>
<link href="/resources/static/css/sideMenuTemplateStyle.css" rel="stylesheet" type="text/css">
<div id="backgroundFade"></div>
<div id="sideBar">
    <span id="sideMenuBtn" onclick="sideMenu(this)">
        <div class="bar1"></div>
        <div class="bar2"></div>
        <div class="bar3"></div>
    </span>
    <div id="sideMenu">
        <a href="/" id="home-menu">홈</a>
        <a href="/wordbookList.wb" id="wordbook-menu">단어장</a>
        <a href="#" id="news-menu">뉴스</a>
        <a href="#" id="openwb-menu">오픈 단어장</a>
    </div>
</div>