<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/elements/header.jsp" flush="true">
    <jsp:param name="header" value="Dashboard" />
</jsp:include>
    <h3>Users:</h3>
    <!-- no c:if => c:else =( using c:choose -->
    <c:choose>
        <c:when test="${users ne null}">
            <ul>
                <c:forEach var="user" items="${users}">
                    <li>Id: <c:out value="${user.id}" />; email: ${user.email}</li>
                </c:forEach>
            <ul>
        </c:when>
        <c:otherwise>
            <jsp:include page="/WEB-INF/view/elements/errors.jsp" flush="true">
                <jsp:param name="errors" value="${errors}" />
            </jsp:include>
        </c:otherwise>
    </c:choose>
<jsp:include page="/WEB-INF/view/elements/footer.jsp" />