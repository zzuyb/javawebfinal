<%@ page import="beans.Score" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 13258
  Date: 2020/11/25
  Time: 22:00
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
        <p><a href="admin_showscore.jsp">查询成绩</a></p>
        <p><a href="admin_updatescore.jsp">更改成绩</a></p>
        <p><a href="admin_addscore.jsp">添加成绩</a></p>
        <p><a href="uploadForm.jsp">上传文件</a></p>
</div>

<tr>
    <td colspan="6" align="center">
        <form action="${pageContext.request.contextPath}/admin_exitscore.do" method="post">
            <input name="exit" type="hidden" value="user">
            <input type="submit" value="退出登录">
        </form>
    </td>
</tr>
</body>
</html>
