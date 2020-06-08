<%@ page import="java.util.List" %>
<%@ page import="net.__test4.db.vo.TestBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>YAMU</title>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script>
        $(function()
        {
            $("#btn").on("click",function ()
            {
                $.ajax
                ({
                    type:"post",
                    url:"/testDataSet.ts4",
                    //key : value 방식으로 보냄
                    data:$("form[name=addData]").serialize(),
                    dataType : 'json',
                    success: whenSuccess
                })
            })
        });

        function whenSuccess(result){
            var html = '<table border=1><th>name</th><th>age</th>';
            $.each(result, function(index,entry){
                html += '<tr>';
                html += '<td>' + entry.name +'</td>';
                html += '<td>' + entry.age + '</td>';
                html += '</tr>';
            });
            html+='</table>';
            $("#suggestion_box").html(html);
        }
    </script>
</head>
<body>

<form name="addData">
    이름 : <input type="text" name="name">
    나이 : <input type="text" name="age">
    <input type="button" value="추가" id="btn">
</form>


<div id="suggestion_box">
    <table border=1><th>name</th><th>age</th>
        <%
            List testList = (List)request.getAttribute("testList");
            for(int i = 0; i<testList.size();i++)
            {
        %>
        <tr>
            <td><%=((TestBean) testList.get(i)).getName()%></td>
            <td><%=((TestBean) testList.get(i)).getAge()%></td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>

