
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel panel-default">
    <div class="panel-heading"><t:translate key="message.clients"/></div>
    <table class="table">
        <tr>
            <td><t:translate key="message.client.login"/></td>
            <td><t:translate key="message.client.id"/></td>
        </tr>
        <c:forEach items="${clients}" var="client">
            <tr>
                <td>${client.login}</td>
                <td>${client.id}</td>
            </tr>
        </c:forEach>
    </table>
</div>
