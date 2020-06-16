<%--
  Created by IntelliJ IDEA.
  User: murag
  Date: 2020-06-06
  Time: 오후 5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mainpage.db.vo.WordbookBean" %>
<%
    List wordbookList = (List)request.getAttribute("wordbookList");
%>
<html>
<head>
    <title>YAMU</title>
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="/resources/static/js/mainPage/mainJS.js"></script>
    <link href="/resources/static/css/mainPage/mainContentStyle.css" rel="stylesheet" type="text/css">
    <link href="/resources/static/css/mainPage/mainStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/resources/templates/topTemplate.jsp" flush="false"/>
<jsp:include page="/resources/templates/sideMenuTemplate.jsp" flush="false"/>
<div id="body-view">
    <div>
        <div class="menu-title">
            <p>Open Wordbook</p>
        </div>
        <div class="buttons-middle more-button">
            <button onclick="location.href='/openWordbook.ob'">More</button>
        </div>
        <div id="wordbooks" class="y-scroll-view">
            <div id="myGrid-container">
                <%
                    for(Object item : wordbookList)
                    {
                %>
                        <div class="myGrid-item">
                            <button onclick="location.href='./wordList.wl?wbId=<%=((WordbookBean) item).getId()%>&wbName=<%=((WordbookBean) item).getName()%>'" class="wordbookBtn">
                                <div class="wbName"><%=((WordbookBean) item).getName()%></div>
                                <div class="wbinfo">
                                    <%
                                        if(((WordbookBean) item).getInfo() == null)
                                        {
                                            ((WordbookBean) item).setInfo("");
                                        }
                                    %>
                                    <%=((WordbookBean) item).getInfo()%>
                                </div>
                            </button>
                        </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
</body>
</html>

