<%--
  Created by IntelliJ IDEA.
  User: 13258
  Date: 2020/11/25
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<script type="text/javascript">
    var emt = document.getElementsByTagName("em");
    var t=1;
    function  f0(){
        if(register.userid.value.length<6||register.userid.value.length>12){
            emt[0].innerHTML="账号长度6-12位!";
            t=0;
        }
        else{
            emt[0].innerHTML="";
            t=1;
        }
    }
    function  f1(){
        if(register.username.value.length<1||register.username.value.length>4){
            emt[1].innerHTML="用户名长度1-4位!";
            t=0;
        }
        else{
            emt[1].innerHTML="";
            t=1;
        }
    }
    function  f2(){
        var mm=/^[0-9a-zA-Z]{6,12}$/;
        var flag=mm.test(register.password1.value);
        if(!flag){
            emt[2].innerHTML="密码要求长度6-12位；字母数字的组合，不能有其他字符!";
            t=0;
        }
        else{
            emt[2].innerHTML="";
            t=1;
        }
    }
    function  f3(){
        if(register.password1.value != register.password2.value){
            emt[3].innerHTML="密码两次应一致！";
            t=0;
        }else{
            emt[3].innerHTML="";
            t=1;
        }
    }
</script>
<div id="top"><p>欢迎登录学生成绩管理系统</p></div>
<div id="index">
    <form name="register" action="${pageContext.request.contextPath}/register.do" method="post">
        <p> 账号<input name="userid" type="text" onBlur="f0()"/><br><em></em></p>
        <p> 姓名<input name="username" type="text" onBlur="f1()"/><br><em></em></p>
        <p> 密码<input name="password1" type="password" onBlur="f2()"/><br><em></em></p>
        <p>确认密码<input name="password2" type="password" onBlur="f3()"/><br><em></em></p>
        <p><input type="submit" value="确认"></p>
    </form>
    <p><a href="index.jsp">返回登录</a></p>
</div>
</body>
<body>

</body>
</html>
