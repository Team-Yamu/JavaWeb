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
    String str = (String) request.getAttribute("testData");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%=str%>
</body>
</html>
