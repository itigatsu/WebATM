<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<jsp:include page="/WEB-INF/view/elements/plain_error.jsp">
    <jsp:param name="header" value="403 Access Denied" />
    <jsp:param name="title" value="Forbidden" />
    <jsp:param name="text" value="You are not authorized to view this page." />
</jsp:include>
