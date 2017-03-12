
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="tags" prefix="t" %>
<html>
<head>
    <title><t:translate key="title.main.apartment"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">

    <script src="${pageContext.servletContext.contextPath}/js/myscript.js"></script>
</head>
<body>
    <jsp:include page="../static/header_client.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <jsp:include page="apartmentInfo.jsp"/>
            </div>

        </div>
    </div>
</body>
</html>
