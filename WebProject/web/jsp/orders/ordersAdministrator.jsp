
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><t:translate key="order.title"/></title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../static/header_administrator.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><t:translate key="message.order.for.you"/></div>
                <table class="table">
                    <tr>
                        <td><t:translate key="order.num"/></td>
                        <td><t:translate key="message.client.name"/></td>
                        <td><t:translate key="placeholder.apartment.orderEntry"/></td>
                        <td><t:translate key="placeholder.apartment.orderExit"/></td>
                        <td><t:translate key="placeholder.apartment.class"/></td>
                        <td><t:translate key="placeholder.apartment.bed"/></td>
                    </tr>
                    <c:forEach items="${waitingOrders}" var="order">
                        <form method="post" action="${pageContext.servletContext.contextPath}/controller">
                            <input type="hidden" name="command" value="create_trip">
                            <tr>
                                <input type="hidden" name="orderId" value="${order.id}">
                                <td>${order.statusEnum}</td>
                                <td>${order.nameOrderAccount}</td>
                                <td>${order.orderEntry}</td>
                                <td>${order.orderExit}</td>
                                <td>${order.orderApartmentClass}</td>
                                <td>${order.orderApartmentBed}</td>
                                <%--<td><input type="submit" class="btn btn-default"
                                           value="<t:translate key="link.create.trip"/>"/></td>--%>
                            </tr>
                        </form>
                    </c:forEach>
                </table>
            </div>
            <div class="panel panel-warning">
                <div class="panel-heading"><t:translate key="message.order.performed"/></div>
                <table class="table">
                    <tr>
                        <td><t:translate key="order.num"/></td>
                        <td><t:translate key="message.client.name"/></td>
                        <td><t:translate key="placeholder.apartment.orderEntry"/></td>
                        <td><t:translate key="placeholder.apartment.orderExit"/></td>
                        <td><t:translate key="placeholder.apartment.class"/></td>
                        <td><t:translate key="placeholder.apartment.bed"/></td>
                    </tr>
                    <c:forEach items="${confirmedOrders}" var="order">
                        <tr>
                            <td>${order.statusEnum}</td>
                            <td>${order.nameOrderAccount}</td>
                            <td>${order.orderEntry}</td>
                            <td>${order.orderExit}</td>
                            <td>${order.orderApartmentClass}</td>
                            <td>${order.orderApartmentBed}</td>
                        </tr>

                    </c:forEach>
                </table>
            </div>
            <div class="panel panel-success">
                <div class="panel-heading"><t:translate key="message.order.done"/></div>
                <table class="table">
                    <tr>
                        <td><t:translate key="order.num"/></td>
                        <td><t:translate key="message.client.name"/></td>
                        <td><t:translate key="placeholder.apartment.orderEntry"/></td>
                        <td><t:translate key="placeholder.apartment.orderExit"/></td>
                        <td><t:translate key="placeholder.apartment.class"/></td>
                        <td><t:translate key="placeholder.apartment.bed"/></td>
                    </tr>
                    <c:forEach items="${closedOrders}" var="order">
                        <tr>
                            <td>${order.statusEnum}</td>
                            <td>${order.nameOrderAccount}</td>
                            <td>${order.orderEntry}</td>
                            <td>${order.orderExit}</td>
                            <td>${order.orderApartmentClass}</td>
                            <td>${order.orderApartmentBed}</td>
                        </tr>

                    </c:forEach>
                </table>
            </div>
            <h2 style="text-align: center; color: darkred;">${noOrders}</h2>
        </div>
    </div>
</div>
</body>
</html>
