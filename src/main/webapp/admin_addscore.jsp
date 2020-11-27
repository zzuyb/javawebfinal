<%--
  Created by IntelliJ IDEA.
  User: 13258
  Date: 2020/11/26
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<div id="top"><p>学生成绩管理系统-录入成绩</p></div>
<div id="admin">输出下列信息进行分数录入</div>
<table align="center" cellpadding="15" cellspacing="15">
    <tr>
        <td>学生学号</td>
        <td>课程号</td>
        <td>成绩</td>
        <td>老师号</td>
    </tr>
    <tr>
        <form action="${pageContext.request.contextPath}/admin_addscore.do" method="post">
            <td><input type="text" name="studentid"></td>
            <td><input type="text" name="courseid"></td>
            <td><input type="text" name="score"></td>
            <td><input type="text" name="Tno"></td>
        </form>
    </tr>
    <tr>
        <td colspan="3" align="center"><input type="submit" value="添加"></td>
        </form>
    </tr>
    <tr>
        <td colspan="3" align="center">
            <form action="${pageContext.request.contextPath}/admin_exitscore.do" method="post">
                <input name="exit" type="hidden" value="score">
                <input type="submit" value="返回">
            </form></td>
    </tr>
</table>
</body>
</html>
