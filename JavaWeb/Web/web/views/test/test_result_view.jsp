<%--
  Created by IntelliJ IDEA.
  User: murag
  Date: 2020-06-02
  Time: 오후 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String testData = (String) request.getAttribute("testData");
    List testList = (List)request.getAttribute("testList");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%=testData%><br><br>
<%
    if(testList != null)
    {
        for (Object item: testList)
        {
%>
            <%=item%><br>
<%
        }
    }
%>
</body>
</html>
