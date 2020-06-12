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
</head>
<body>
<jsp:include page="/resources/templates/topTemplate.jsp" flush="false"/>
<jsp:include page="/resources/templates/sideMenuTemplate.jsp" flush="false"/>
<ul>
    <%
        for(Object item : wordbookList)
        {
    %>
            <li>
                <ol><%=((WordbookBean) item).getId()%></ol>
                <ol><%=((WordbookBean) item).getUser_id()%></ol>
                <ol><%=((WordbookBean) item).getName()%></ol>
                <ol><%=((WordbookBean) item).getVisit_count()%></ol>
            </li>
    <%
        }
    %>
</ul>
</body>
</html>

