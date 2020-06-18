<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/static/js/openWordbook/openWordbookJS.js"></script>
    <script src="<%=request.getContextPath()%>/resources/static/js/openWordbook/openWordbookListAjaxJS.js"></script>
    <link href="<%=request.getContextPath()%>/resources/static/css/openWordbook/openWordbookStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/resources/templates/topTemplate.jsp"/>
<jsp:include page="/resources/templates/sideMenuTemplate.jsp"/>
<div id="body-view">
    <div id="title">
        <p>Open Wordbook</p>
    </div>
    <div id="searchForm">
        <form>
            <input hidden="hidden">
            <input type="text" name="searchWordbook" id="searchWordbook" placeholder="단어장 검색">
        </form>
    </div>
    <div id="wordbookList">
        <ul id="wordbookItems">
        </ul>
    </div>
    <div id="moveListPage">
        <button id="moreBtn">more</button>
    </div>
</div>
</body>
</html>
