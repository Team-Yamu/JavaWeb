<!--회원가입 폼 페이지(회원가입 정보를 입력하는 페이지)-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Join</title>
</head>
<body>
<form name="InsertUser" action="./Insertuser.login" method="post">
    <center>
        <table border="1">
            <tr>
                <td colspan="2" align="center">
                    <b><font size="5">회원가입</font></b>
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
                <td>이름:</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <a href="javascript:InsertUser.submit()">회원가입</a>
                    <a href="javascript:joinform.reset()">다시작성</a>
                </td>
            </tr>
        </table>
    </center>
</form>
</body>
</html>