<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.loginpage.db.dao.UserDAO" %>
<%@ page import="com.loginpage.db.vo.UserBean" %>
<%
    List userList = (List) request.getAttribute("userList");
%>

<html>
<head>
    <title>UserList</title>
</head>
<body>
<%
    for (int i = 0; i < userList.size(); i++)
    {
        UserBean user = (UserBean) userList.get(i);
%>
        id: <%=user.getName()%> &nbsp;
        pw: <%=user.getPassword()%> &nbsp;
        이름: <%=user.getName()%> &nbsp;
        <br>
<%
    }
%>
</body>
</html>
