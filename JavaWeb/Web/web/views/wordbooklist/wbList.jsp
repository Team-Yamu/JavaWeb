<%@ page import="java.util.List" %>
<%@ page import="com.wordbookpage.db.vo.WordbookBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("id") == null)
    {
%>
        <script>
            location.href="./login.login";
        </script>
<%
    }
    List wordbookList = (List)request.getAttribute("wordbookList");
%>
<html>
<head>
    <title>YAMU</title>
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="/resources/static/js/wordbookList/wbListJS.js"></script>
    <link href="/resources/static/css/topTemplateStyle.css" rel="stylesheet" type="text/css">
    <link href="/resources/static/css/wordbookList/wbListStyle.css" rel="stylesheet" type="text/css">
    <link href="/resources/static/css/wordbookList/wbListFormStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/resources/templates/topTemplate.jsp" flush="false"/>
<jsp:include page="/resources/templates/sideMenuTemplate.jsp" flush="false"/>
<div id="section">
    <div id="popup">
        <div id="popupForm">
            <button id="closePopupBtn">닫기</button>
            <form id="addWordbook">
                <table>
                    <tr>
                        <td>단어장 이름 : </td>
                        <td><input type="text" name="wordbookName"></td>
                    </tr>
                    <tr>
                        <td>설명 : </td>
                        <td><input type="text" name="info"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="button" value="추가" id="addWordbookBtn"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="title">
        <a>단어장</a>
        <button id="addBtn">단어장 만들기</button>
        <hr>
    </div>
    <div id="wordbookList" class="grid-container">
        <%
            for(int i = 0; i<wordbookList.size();i++)
            {
        %>
                <div class="grid-item">
                    <button onclick="location.href='./wordList.wl?wbId=<%=((WordbookBean) wordbookList.get(i)).getId()%>&wbName=<%=((WordbookBean) wordbookList.get(i)).getName()%>'" class="wordbookBtn">
                        <%=((WordbookBean) wordbookList.get(i)).getName()%>
                    </button>
                </div>
        <%
            }
        %>
        <script>
            randomBgColor ();
        </script>
    </div>
</div>
</body>
</html>