<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="table1" class="panel panel-default">
    <div class="panel-heading"><t:translate key="message.apartment.all"/> ${hotelName}</div>

    <%--<form action="${pageContext.servletContext.contextPath}/controller" method="post">
    <input type="hidden" name="hotelId" value="${hotelId}">
        <input type="hidden" name="command" value="create_order">--%>
    <table class="table">

        <tr>
            <td><t:translate key="placeholder.apartment.id"/></td>
            <td><t:translate key="placeholder.apartment.class"/></td>
            <td><t:translate key="placeholder.apartment.bed"/></td>
            <td>
                <div id="dataEntry" style="display:none"><t:translate key="placeholder.apartment.orderEntry"/></div>
            </td>
            <td>
                <div id="dataExit" style="display: none"><t:translate key="placeholder.apartment.orderExit"/></div>
            </td>
            <td></td>
        </tr>


        <c:forEach items="${apartments}" var="apartment">
            <form action="${pageContext.servletContext.contextPath}/controller" method="post">
                <input type="hidden" name="hotelId" value="${hotelId}">
                <input type="hidden" name="command" value="create_order">
                <tr>
                    <input type="hidden" name="apartmentId" value="${apartment.id}"/>
                    <td>${apartment.id}</td>
                    <td>${apartment.classOfApartment}</td>
                    <td>${apartment.numberOfBed}</td>
                    <td><input type="text" class="form-control" id="${apartment.id-1}" style="display:none"
                               name="${apartment.id}-1"/></td>
                    <td><input type="text" class="form-control" id="${apartment.id+1}" style="display:none"
                               name="${apartment.id}+1"/></td>
                    <td><input type="submit" id="${apartment.id+100}" class="newclass" style="display:none"
                               value="<t:translate key="apartment.registration"/>"/></td>
            </form>
            <td><input id="${apartment.id}" type="submit" class="mynewclass" style="display: block"
                       onclick="show(${apartment.id},${apartment.id-1},${apartment.id+1},'dataEntry','dataExit','table1',${apartment.id+100})"
                       value="<t:translate key="apartment.show.registration"/>"/>
            </td>
            </tr>
        </c:forEach>
    </table>
    <h3>${noApartment}</h3>
</div>
