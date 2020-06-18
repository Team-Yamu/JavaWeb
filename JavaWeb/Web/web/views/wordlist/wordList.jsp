<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wordlistpage.db.vo.WordBean" %>
<%
    List wordList = (List)request.getAttribute("wordList");
    String wordbookName = (String)request.getAttribute("wordbookName");
    String wordbookUserId = (String)request.getAttribute("user_id");
    int wordbookId = (int) request.getAttribute("wbId");
%>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/static/js/wordList/wordListJS.js"></script>
    <script src="<%=request.getContextPath()%>/resources/static/js/wordList/wordAddPopupJS.js"></script>
    <script src="<%=request.getContextPath()%>/resources/static/js/wordList/searchWordListJS.js"></script>
    <script src="<%=request.getContextPath()%>/resources/static/js/wordList/addWordJS.js"></script>
    <script src="<%=request.getContextPath()%>/resources/static/js/wordList/wbVisitCount.js"></script>
    <link href="<%=request.getContextPath()%>/resources/static/css/wordList/wordListSkeletonStyle.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/resources/static/css/wordList/wordListStyle.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/resources/static/css/wordList/wordAddPopupStyle.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/resources/static/css/wordList/searchWordListStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/resources/templates/topTemplate.jsp"/>
<jsp:include page="/resources/templates/sideMenuTemplate.jsp"/>
<div id="loading" style="background-color: black; width: 100%;height: 100%; display: none"></div>
<div id="popup">
    <div id="popupForm">
        <button id="closePopupBtn">닫기</button>
        <form id="addWordForm">
            <p id="wordName">검색</p>
            <input hidden="hidden" name="wbId" value="<%=wordbookId%>">
            <input autocomplete="off" type="text" name="wordName" id="searchWord">
            <ul id="wordSampleList">
            </ul>
            <input type="button" value="추가" id="addWordBtn">
        </form>
    </div>
</div>
<div class="section">
    <div id="wordListTitle"><%=wordbookName%></div>
    <div id="wordListHeader">
        <div id="right-btns">
            <%
                if(wordbookUserId.equals(session.getAttribute("id")))
                {
            %>
                    <button class="button-size2 buttons" id="addWord-btn">단어 추가</button>
            <%
                }
                else
                {
                    if(session.getAttribute("id")!=null)
                    {
            %>
                        <script>
                            visitCountAjax(<%=wordbookId%>);
                        </script>
            <%
                    }
                }
            %>
            <button onclick="location.href='<%=request.getContextPath()%>';" class="button-size2 buttons" id="wordTest-btn">단어 시험</button>
        </div>
    </div>
    <hr>
    <div id="wordList">
        <ul id="wordListItem">
        <%
            for(int i = 0; i<wordList.size();i++)
            {
        %>
                <li onclick="location.href='./word.w?wName=<%=((WordBean) wordList.get(i)).getWordName()%>';">
                    <span class="wordRdColor"><%=((WordBean) wordList.get(i)).getWordName()%></span>
                    <span class="wordMean">
                    <%
                        List item = ((WordBean) wordList.get(i)).getWordMeanList();
                        for(int j = 0; j<item.size();j++)
                        {
                            if(j!= item.size()-1)
                            {
                    %>
                                <%=item.get(j)+", "%>
                    <%
                            }
                            else
                            {
                    %>
                                <%=item.get(j)%>
                    <%
                            }
                        }
                    %>
                    </span>
                </li>
        <%
            }
        %>
        </ul>
    </div>
</div>
</body>
</html>
