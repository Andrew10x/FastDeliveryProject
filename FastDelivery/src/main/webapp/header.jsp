<%@ page import="code.CookieAction" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <link href="${pageContext.request.contextPath}/resources/header.css" rel="stylesheet">
</head>
<body>
<%
    String UserRole = (String) request.getAttribute("UserRole");
%>
<header>
    <div class="header-title">Fast Delivery</div>
    <div class="header-menu">
        <div><a href="${pageContext.request.contextPath}/">Розрахувати вартість</a></div>
        <% if(Objects.equals(UserRole, "")) {%>
        <div ><a href = "${pageContext.request.contextPath}/auth/singin.jsp" >Вхід</a ></div >
        <div ><a href = "${pageContext.request.contextPath}/auth/singup.jsp" >Реєстрація</a ></div >
        <%} else {%>
        <div ><a href = "${pageContext.request.contextPath}/auth/singin.jsp" >Створити замовлення</a ></div >
        <div ><a href = "${pageContext.request.contextPath}/auth/singup.jsp" >Мої замовлення</a ></div >
        <div ><a href = "${pageContext.request.contextPath}/ExitServ" >Вийти</a ></div >
        <%}%>
    </div>
</header>
</body>
</html>