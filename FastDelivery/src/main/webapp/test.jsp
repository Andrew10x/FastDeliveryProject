<%--
  Created by IntelliJ IDEA.
  User: svtan
  Date: 02.01.2024
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%=request.getSession().getAttribute("userRole")%>
<%=request.getSession().getAttribute("userName")%>
</body>
</html>
