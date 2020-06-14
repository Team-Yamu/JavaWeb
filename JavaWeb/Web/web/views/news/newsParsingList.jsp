<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.news.db.vo.NewsBean" %>
<%@ page import="com.news.db.vo.NewsParserBean" %>
<%
    List newsList = (List) request.getAttribute("newsList");
%>
<html>
<head>
    <title>News List</title>
</head>
<body>
<form action="/newsinsert.news" method="post">
    URL 입력 : <input type="text" name="URL">
    <input type="submit" value="추가">
</form>

<%
    for (int i = 0; i < newsList.size(); i++)
    {
        NewsParserBean news = (NewsParserBean) newsList.get(i);
%>

<img src=<%="data:image/png;base64," + news.getImage()%> width="300" height="200">
</br>
<%=news.getTitle()%>
</br></br>
<%=news.getKeyword()%>
</br></br>
<%=news.getSummary()%>
</br></br>
<%=news.getText()%>
</br></br>
<hr width=80% color="black" align="left" size=30/>
<%
    }
%>
</body>
</html>