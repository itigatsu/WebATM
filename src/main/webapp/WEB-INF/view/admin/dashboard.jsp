<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %> <!-- does not loading without this directive!!! -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- unable to use &lt;c:* without this directive!!! -->

<jsp:include page="/WEB-INF/view/elements/header.jsp" flush="true">
    <jsp:param name="header" value="Dashboard" />
</jsp:include>
    Welcome to the <%= request.getServerName() %>, <!-- scriplets -->
    <b>${user.email}</b> !<br /> <!-- Expression Language -->

    <!-- Using JSTL tags -->
    <!-- User v1: <c:out value='${user.email}' /><br /> -->

    <!-- using useBean -->
    <jsp:useBean id="user" class="com.tc.webatm.model.User" scope="request" />
    <!-- User v2: <jsp:getProperty name="user" property="email" /><br > -->

    <!-- using scriplets (not perfect solution) -->
    <%@page import="com.tc.webatm.model.User" %>
    <!-- User v3: <%= ((User)request.getAttribute("user")).getEmail() %><br /> -->
<jsp:include page="/WEB-INF/view/elements/footer.jsp" />