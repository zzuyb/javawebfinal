<%@ page import="beans.Score" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 13258
  Date: 2020/11/26
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<div id="top"><p>学生成绩管理系统-查询成绩</p></div>
<div id="admin">
    <form action="${pageContext.request.contextPath}/admin_showscore.do" method="post">
        <input name="func" type="hidden" value="show">选择成绩查询方式：
        <input name="flag" type="radio" value="course" checked>课程号查询
        <input name="flag" type="radio" value="stu">学号查询<br>
        <input name="id" type="text">
        <input type="submit" value="查询">
    </form>
</div>
<table align="center" cellpadding="15" cellspacing="15">
    <%
        ArrayList<Score> list=(ArrayList<Score>) session.getAttribute("score");
        String flag=(String) session.getAttribute("flag");
        if(list!=null&&list.size()!=0){
            if(flag.equals("stu")){%>
    <tr>
        <td colspan="4" align="center">学号为<%=list.get(0).getSno() %>的学生成绩如下</td>
    </tr>
    <% }else{%>
    <tr>
        <td colspan="4" align="center">  课程号为<%=list.get(0).getCno()%>的全体学生成绩如下</td>
    </tr>
    <%}%>
    <tr>
        <td>学生学号</td>
        <td>学生姓名</td>
        <td>课程号</td>
        <td>课程名</td>
        <td>老师号</td>
        <td>成绩</td>
    </tr>
    <%
        for(int j=0;j<list.size();j++){%>
    <tr>
        <td><%=list.get(j).getSno() %></td>
        <td><%=list.get(j).getSname()%></td>
        <td><%=list.get(j).getCno()%></td>
        <td><%=list.get(j).getCname()%></td>
        <td><%=list.get(j).getTno()%></td>
        <td><%=list.get(j).getGrade()%></td>
    </tr>
    <% }}%>
    <tr>
        <td colspan="4" align="center">
            <form action="${pageContext.request.contextPath}/admin_exitscore.do" method="post">
                <input name="exit" type="hidden" value="score">
                <input type="submit" value="返回">
            </form>
        </td>
    </tr>
</table>
</body>

</html>
