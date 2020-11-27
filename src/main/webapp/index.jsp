<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div id="top"><p>欢迎登录学生成绩管理系统</p></div>
<div id="index">
    <form action="${pageContext.request.contextPath}/index.do" method="post">
        <p> 账号<input name="userid" type="text" onBlur="f0()"/><br></p>
        <p> 密码<input name="password" type="password" onBlur="f1()"/><br></p>
        <p><input name="user" type="radio" value="student" checked>学生
            <input name="user" type="radio" value="administrator">管理员<br></p>
        <p><input type="submit" value="登录"></p>
    </form>
    <p><a href="register.jsp">没有账号？点击注册吧</a></p>
</div>
</body>
</html>
