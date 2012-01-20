<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %> <!-- does not loading without this directive!!! -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- unable to use &lt;c:* without this directive!!! -->

<c:if test="${errors ne null}">
    <h4>Error(s) raised:</h4>
    <ul class="errors">
        <c:forEach var="error" items="${errors}">
            <li><c:out value="${error}" /></li>
        </c:forEach>
    </ul>
</c:if>

