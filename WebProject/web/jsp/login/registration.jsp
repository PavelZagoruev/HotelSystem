
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><t:translate key="registration.page.title"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="page-header">
    <h2 style="text-align: center;"><t:translate key="registration.message"/></h2>
</div>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <form action="${pageContext.servletContext.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="registration">

                    <div class="input-group">
                        <span class="input-group-addon">@</span>
                        <input type="text" name="login" class="form-control"
                               placeholder="<t:translate key="placeholder.username" />">
                    </div>
                    <p style="color: red;"> ${loginError} </p>

                    <div class="input-group">
                        <span class="input-group-addon">@</span>
                        <input type="text" name="password" class="form-control"
                               placeholder="<t:translate key="placeholder.password"/>">
                    </div>
                    <p style="color: red;"> ${passwordError} </p>

                    <p style="color: red;"> ${accountExist} </p>
                    <input type="submit" class="btn btn-default" value="<t:translate key="reg.button"/>"/>
                    <a class="btn btn-default" role="button" href="<c:url value="/index.jsp" />"><t:translate
                            key="return.button"/></a>
                </form>
            </div>
            <div class="col-md-5">
                <h3 style="text-align: center; color: green">${registrationComplete}</h3>
            </div>
        </div>
    </div>
</main>
</body>
</html>
