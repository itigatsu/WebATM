<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<jsp:include page="/WEB-INF/view/elements/plain_error.jsp">
    <jsp:param name="header" value="500 Internal Server Error" />
    <jsp:param name="title" value="500 Internal Server Error" />
    <jsp:param name="text" value="Here goes some stacktrace" />
</jsp:include>
