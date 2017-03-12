<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><t:translate key="order.title"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../static/header_client.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><t:translate key="message.orders.client"/></div>
                <table class="table">
                    <tr>
                        <td><t:translate key="order.num"/></td>
                        <td><t:translate key="placeholder.apartment.orderEntry"/></td>
                        <td><t:translate key="placeholder.apartment.orderExit"/></td>
                        <td><t:translate key="placeholder.apartment.class"/></td>
                        <td><t:translate key="placeholder.apartment.bed"/></td>
                    </tr>
                    <c:forEach items="${allOrders}" var="order">
                        <form method="post" action="${pageContext.servletContext.contextPath}/controller">
                            <input type="hidden" name="command" value="create_trip">
                            <tr>
                                <input type="hidden" name="orderId" value="${order.id}">
                                <td>${order.statusEnum}</td>
                                <td>${order.orderEntry}</td>
                                <td>${order.orderExit}</td>
                                <td>${order.orderApartmentClass}</td>
                                <td>${order.orderApartmentBed}</td>
                                <%--<td><input type="submit" class="btn btn-default"
                                           value="<t:translate key="link.delete.client.order"/>"/></td>--%>
                            </tr>
                        </form>
                    </c:forEach>
                </table>

            <h2 style="text-align: center; color: darkred;">${noOrders}</h2>

        </div>
    </div>
</div>
</body>
</html>