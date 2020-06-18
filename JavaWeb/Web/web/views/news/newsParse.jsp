<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News</title>
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/static/js/news/newsSearchAjaxJS.js"></script>
    <script src="<%=request.getContextPath()%>/resources/static/js/news/newsSearchJS.js"></script>
    <link href="<%=request.getContextPath()%>/resources/static/css/news/mainNewsStyle.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/resources/static/css/news/searchNewsStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/resources/templates/topTemplate.jsp"/>``
<jsp:include page="/resources/templates/sideMenuTemplate.jsp"/>
<div id="popup">
    <p><a>Y</a><a>A</a><a>M</a><a>U</a></p>
</div>
<div id="body-view">
    <div id="title">
        <p>News</p>
    </div>
    <div id="searchForm">
        <form id="search">
            <input hidden="hidden">
            <input type="text" autocomplete="off" name="searchNews" id="searchNews" placeholder="뉴스 URL을 입력해주세요.">
        </form>
    </div>
    <div id="searchNewsBtn"><button>검색</button></div>
</div>
<div id="searchMyNews-view">
</div>
</body>
</html>