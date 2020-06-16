<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.news.db.vo.NewsBean" %>
<%
    List newsList = (List) request.getAttribute("newsList");
%>
<html>
<head>
    <title>News List</title>
</head>
<body>
<table border="1" width="300">
    <%
        for (int i = 0; i < newsList.size(); i++)
        {
            NewsBean news = (NewsBean) newsList.get(i);
    %>
    <tr align="center">
        <td>hash: <%=news.getHash()%>
        </td>
        <td>json: <%=news.getJson_data()%>
        </td> &nbsp;
        <td>visit: <%=news.getVisit_count()%>
        </td> &nbsp;
    </tr>
    <%
        }
    %>
</table>
<form action="/newsinsert.news" method="post">
    URL 입력 : <input type="text" name="URL">
    <input type="submit" value="추가">
</form>
</body>
</html>
