<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String id = null;
    if (session.getAttribute("id") != null)
    {
        id = (String) session.getAttribute("id");
    }
    // 로그인이 미상태로 로그인 완료 페이지에 접속 시
    else
    {
%>
        <script>
            alert("로그인이 되어있지 않습니다");
            location.href="/login.login";
        </script>
<%
    }
%>

<html>
<head>
    <title>Loing Success</title>
</head>
<body>
로그인 완료 <br>
아이디: <%=id%> <br>
<a href="/logoutProcess.login">로그아웃</a>
</body>
</html>
