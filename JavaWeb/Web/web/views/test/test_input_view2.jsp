<%--
  Created by IntelliJ IDEA.
  User: murag
  Date: 2020-06-03
  Time: 오전 2:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%
    List wordList = (List)request.getAttribute("wordList");
%>
<html>
<head>
    <title>test 단어 입력 폼</title>
</head>
<body>
<table style="width:400px; height: 500px; margin: auto;position: relative; border: 2px solid rgb(224, 224, 224); border-radius: 10px; padding : 30px;">
    <tr style="text-align: center"><td>
        <form action="../../Test.ts3" method="post">
            단어 입력 : <input type="text" name="word">
            <input type="submit" value="입력">
        </form>
    </td></tr>
    <tr style="text-align: center; border-bottom: black;"><td>
        <h2>단어 리스트</h2>
    </td></tr>
        <%
            if(wordList != null)
            {
                for (Object item: wordList)
                {
        %>
                    <tr style="text-align: center"><td>
                        <%=((String)item).replaceAll("\"","")%>
                    </td></tr>
        <%
                }
            }
        %>
</table>
</body>
</html>
