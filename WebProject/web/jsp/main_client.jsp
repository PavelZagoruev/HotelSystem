
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="tags" %>
<html>
<head>
    <title><t:translate key="main.client.title"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<%@ include file="static/header_client.jsp" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <%@ include file="accommodation/accommodations.jsp" %>
            </div>
        </div>
    </div>
</main>
</body>
</html>
