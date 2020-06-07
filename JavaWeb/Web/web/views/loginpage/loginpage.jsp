<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String id = null;
    if (session.getAttribute("id") != null)
        id = (String) session.getAttribute("id");
%>

<html>
<head>
    <title>Loing Success</title>
</head>
<body>
로그인 완료 <br>
아이디: <%=id%> <br>
</body>
</html>
