
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="tags" prefix="t" %>
<html>
<style>
    .navbar-form {
        margin: 0;
    }
</style>
<header>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/jsp/main_dispatcher.jsp">
                            <t:translate key="link.drivers"/>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/jsp/order/orders.jsp">
                            <t:translate key="link.orders"/>
                        </a>
                    </li>
                </ul>
                <form class="navbar-form navbar-right" action="${pageContext.servletContext.contextPath}/controller" method="post">
                    <div class="form-group">
                        <a class="navbar-brand" href="#">${user}</a>
                        <input type="hidden" name="command" value="logout" class="form-control"/>
                    </div>
                    <button type="submit" class="btn btn-default"><t:translate key="logout.button"/></button>
                </form>
            </div>
        </div>
    </nav>
</header>
</html>
