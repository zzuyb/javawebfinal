<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13258
  Date: 2020/11/27
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <!-- 遍历List集合 -->
<%--    <c:forEach var="list" items="${fileNameList }">--%>
<%--        <c:url value="/servlets/download" var="downurl">--%>
<%--            <c:param name="filename" value="${list }"></c:param>--%>
<%--        </c:url>--%>
<%--        <tr>--%>
<%--            <td>--%>
<%--                    ${list }--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <a href="${downurl }">下载</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<% List<String> list=(ArrayList<String>) request.getAttribute("fileNameList");%>
    <c:url value="/servlets/download" var="downurl"></c:url>
    <%
        for(int j=0;j<list.size();j++){%>
    <tr>
        <td><%=list.get(j) %></td>
        <a href="${downurl }">下载</a>
    </tr>
    <% }%>
</table>
</body>
</html>
