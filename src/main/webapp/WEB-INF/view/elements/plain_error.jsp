<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %> <!-- does not loading without this directive!!! -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <title>${param.header}</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/img/styles.css" />
    </head>
    <body>
        <h1>${param.title}</h1>
        <p>${param.text}</p>
    </body>
</html>
