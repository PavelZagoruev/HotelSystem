
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
                        <form  action="${pageContext.servletContext.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="show_clients"/>
                        <input type="submit" class="btn btn-default" value="<t:translate key="link.clients"/>"/>
                        </form>
                    </li>
                    <li>
                        <form  action="${pageContext.servletContext.contextPath}/controller" method="post">
                        <input type="submit" class="btn btn-default" value="<t:translate key="link.orders"/>"/>
                            <input type="hidden" name="command" value="show_administrator_orders">
                        </form>
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
