<%--
  Created by IntelliJ IDEA.
  User: 13258
  Date: 2020/11/26
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="gb2312"%>${pageContext.request.contextPath}
<html>
<head></head>
<body style="font-size:40px;font-style:italic;">
<form action="${pageContext.request.contextPath}/upload.do" method="post"
      enctype="multipart/form-data">
    <fieldset>
        <legend>�ϴ�</legend>
        ����:<input name="uname"/><br/>
        ��Ƭ:<input type="file" name="file1"/><br/>
        <input type="submit" value="�ύ"/>
    </fieldset>
</form>
</body>
</html>