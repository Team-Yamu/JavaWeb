<!--로그인 폼 페이지(로그인 정보를 입력하는 페이지)-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<form name="loginform" action="/loginProcess.login" method="post">
    <center>
        <table border="1">
            <tr>
                <td colspan="2" align="center">
                    <b><font size="5">로그인 페이지</font></b>
                </td>
            </tr>
            <tr>
                <td>아이디:</td>
                <td><input type="text" name="id"/></td>
            </tr>
            <tr>
                <td>비밀번호:</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <a href="javascript:loginform.submit()">로그인</a>
                    &nbsp;&nbsp;
                    <a href="/Insertpage.login">회원가입</a>
                </td>
            </tr>
        </table>
    </center>
</form>
</body>
</html>