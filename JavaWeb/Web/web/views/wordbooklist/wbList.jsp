<%@ page import="java.util.List" %>
<%@ page import="com.wordbookpage.db.vo.WordbookBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("id") == null)
    {
%>
        <script>
            location.href="./login.login";
        </script>
<%
    }
    List wordbookList = (List)request.getAttribute("wordbookList");
%>
<html>
<head>
    <title>YAMU</title>
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link href="/resources/static/css/topTemplateStyle.css" rel="stylesheet" type="text/css">
    <link href="/resources/static/css/wbListStyle.css" rel="stylesheet" type="text/css">
    <script>
        $(function()
        {
            $("#addWordbookBtn").on("click",function ()
            {
                $.ajax
                ({
                    type:"post",
                    url:"/wordbookAdd.wb",
                    //key : value 방식으로 보냄
                    data:$("#addWordbook").serialize(),
                    dataType : 'json',
                    success: whenSuccess
                })
            })
        });

        //값을 가져왔을때 실행
        function whenSuccess(result){
            var html = '<div class="grid-item"><button id="addBtn">+</button></div>';
            var count = 0;
            $.each(result, function(index,entry){
                html += '<div class="grid-item">';
                html += '<button onclick="location.href=\'./wordList.wl?wbid='+entry.id+'\'"';
                html += ' class="wordbookBtn" id="randomColor'+(count++)+'">';
                html += entry.wordbookName + '</button>';
                html += '</div>';
            });
            $("#wordbookList").html(html);
            randomBgColor(count);
        }

        //랜덤색상 칠하기
        function randomBgColor (size) {
            var array = ["#0018A8", "#0283F4", "#0158A6"];
            for (var i = 0; i < size; i++) {
                var colorCode = array[Math.round(Math.random() * (array.length-1))];
                var idName = "randomColor" + i
                document.getElementById(idName).style.background = colorCode;
            }
        }
    </script>
</head>
<body>
<jsp:include page="/resources/templates/topTemplate.jsp" flush="false"/>
<form id="addWordbook" style="z-index:100">
    단어장 이름 : <input type="text" name="wordbookName">
    설명 : <input type="text" name="info">
    <input type="button" value="추가" id="addWordbookBtn">
</form>

<div id="title">
    <a style="font-size: 30px; font-weight: bold;">단어장</a>
    <hr>
</div>
<div id="wordbookList" class="grid-container">
    <div class="grid-item"><button id="addBtn">+</button></div>
        <%
            for(int i = 0; i<wordbookList.size();i++)
            {
        %>
                <div class="grid-item"><button onclick="location.href='./wordList.wl?wbid=<%=((WordbookBean) wordbookList.get(i)).getId()%>'" class="wordbookBtn" id="randomColor<%=i%>"><%=((WordbookBean) wordbookList.get(i)).getName()%></button></div>
        <%
            }
        %>
    <script>
        randomBgColor(<%=wordbookList.size()%>);
    </script>
</div>

</body>
</html>