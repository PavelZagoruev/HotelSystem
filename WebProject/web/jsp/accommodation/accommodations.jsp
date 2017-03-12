
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" uri="tags" %>

<div class="panel panel-default">
    <div class="panel-heading"><t:translate key="message.trip.for.you"/></div>

    <table class="table">
        <tr>
            <td><t:translate key="hotel.id"/></td>
            <td><t:translate key="hotel.name"/></td>
        </tr>
        <c:forEach items="${hotels}" var="hotel">
            <tr>
                <form action="${pageContext.servletContext.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="show_apartment">
                <td>${hotel.id}</td>
                <td>${hotel.name}</td>
                <td><input type="submit"  class="btn btn-default" value="<t:translate key="hotel.show.apartment"/>"/>
                <input type="hidden" name="submit_id" value="${hotel.id}">

                </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<h3 style="color:darkred; text-align: center;">${noHotels}</h3>
