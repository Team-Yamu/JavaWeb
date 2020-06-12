<!--회원가입 폼 페이지(회원가입 정보를 입력하는 페이지)-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Join</title>
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <link href="/resources/static/css/loginPage/joinStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/resources/templates/topTemplate.jsp" flush="false"/>
<jsp:include page="/resources/templates/sideMenuTemplate.jsp" flush="false"/>
<form name="InsertUser" action="./Insertuser.login" method="post" id="inputForm">
    <p id="joinTitle">회원가입</p>
    <p>아이디</p>
    <input type="text" name="id" class="inputStyle"/>
    <p>비밀번호</p>
    <input type="password" name="password" class="inputStyle"/>
    <p>닉네임</p>
    <input type="text" name="name" class="inputStyle"/>

    <div id="inputButtons">
        <button href="javascript:InsertUser.submit()">회원가입</button>
    </div>
</form>
</body>
</html>