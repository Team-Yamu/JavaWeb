<!--로그인 폼 페이지(로그인 정보를 입력하는 페이지)-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("id") != null)
    {
%>
        <script>
            location.href="/";
        </script>
<%
    }
%>
<html>
<head>
    <title>Login</title>
    <link href="/resources/static/css/loginPage/loginStyle.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
</head>
<body>
<jsp:include page="/resources/templates/topTemplate.jsp" flush="false"/>
<jsp:include page="/resources/templates/sideMenuTemplate.jsp" flush="false"/>
    <form name="loginform" action="/loginProcess.login" method="post" id="inputForm">
        <p id="loginTitle">로그인</p>
        <p>아이디</p>
        <input type="text" name="id" class="inputStyle"/>
        <p>비밀번호</p>
        <input type="password" name="password" class="inputStyle"/>
        <div id="inputButtons">
            <button href="/Insertpage.login">회원가입</button>
            <button href="javascript:loginform.submit()">로그인</button>
        </div>
    </form>
</body>
</html>