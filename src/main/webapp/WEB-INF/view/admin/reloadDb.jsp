<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %> <!-- does not loading without this directive!!! -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- unable to use &lt;c:* without this directive!!! -->

<jsp:include page="/WEB-INF/view/elements/header.jsp" flush="true">
    <jsp:param name="header" value="Dashboard" />
</jsp:include>
    <h3>Database reload result:</h3>
    <div class="msgbox">
        <c:forEach var="msg" items="${msgs}">
            ${msg}<br />
        </c:forEach>
    </div>
<jsp:include page="/WEB-INF/view/elements/footer.jsp" />