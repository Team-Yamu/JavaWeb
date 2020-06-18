<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <span class="logo"><a href="<%=request.getContextPath()%>/">YAMU</a></span>
    <span id="right-menu"></span>
    <div id="right-btns">
        <%
            if (session.getAttribute("id") == null)
            {
        %>
                <button onclick="location.href='<%=request.getContextPath()%>/login.login';" class="button-size1 buttons" id="login-btn"wordList>login</button>
                <button onclick="location.href='<%=request.getContextPath()%>/Insertpage.login';" class="button-size1 buttons" id="signin-btn">Sign in</button>
        <%
            }
            else
            {
        %>
                <button onclick="location.href='<%=request.getContextPath()%>/logoutProcess.login';" class="button-size1 buttons" id="signin-btn">Log out</button>
        <%
            }
        %>
    </div>
    </span>
</div>
<link href="<%=request.getContextPath()%>/resources/static/css/topTemplateStyle.css" rel="stylesheet" type="text/css">