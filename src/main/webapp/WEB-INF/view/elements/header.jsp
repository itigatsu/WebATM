<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %> <!-- does not loading without this directive!!! -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- unable to use &lt;c:* without this directive!!! -->
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/img/styles.css" />
    <title>${param.header}</title>
</head>
<body>
<div class="menu">
    <b class="big">Menu</b>
    <ul>
        <li><a href="<%= request.getContextPath() %>">Home</a></li>
        <li>
            <a href="<%= request.getContextPath() %>/admin">Admin area</a>
            <c:if test="${user ne null && user.isAdmin}">
                <ul>
                    <li><a href="<%= request.getContextPath() %>/admin/dashboard">Dashboard</a></li>
                    <li><a href="<%= request.getContextPath() %>/admin/initDb">Init DB with mock data</a></li>
                </ul>
            </c:if>
        </li>
        <li>
            <a href="<%= request.getContextPath() %>/user">User area</a>
            <c:if test="${user ne null && user.email ne null}">
                <ul>
                    <li><a href="<%= request.getContextPath() %>/user/dashboard">Dashboard</a></li>
                </ul>
            </c:if>
        </li>
    </ul>
</div>
<div class="container">
