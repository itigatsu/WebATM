<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %> <!-- does not loading without this directive!!! -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- unable to use &lt;c:* without this directive!!! -->

<jsp:include page="/WEB-INF/view/elements/header.jsp" flush="true">
    <jsp:param name="header" value="Welcome!" />
</jsp:include>

<h2>Hello dear ATM user!</h2>
Please use menu to navigate through the site

<jsp:include page="/WEB-INF/view/elements/footer.jsp" />
