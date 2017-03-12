<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page not found</title>
</head>
<body>
<h2>${pageContext.errorData.statusCode} : File Not Found </h2>

<p> The URL: <i>${pageContext.errorData.requestURI}</i> was not found. </p>
<br/>
<a href="<c:url value="/index.jsp"/>">Return to main page.</a>
</body>
</html>
