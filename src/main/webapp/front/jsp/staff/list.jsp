<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>
<html>
<head>
    <%@include file="/front/html/allBundle.html" %>
</head>
<!-- Section -->
<section>
    <header class="major">
        <h2>Cписок заказов</h2>
    </header>

    <ul>
        <c:choose>
            <c:when test="${orders!=null}">
                <c:forEach var="order" items="${orders}">
                    <h3>${order.id}</h3>
                    <div style="display: inline-block">
                        <h3 id="COST" style="float: left">Итого: ${order.cost} BYN</h3>
                        <label id="cost_data" for="COST">Дата получения: ${order.date}</label>
                    </div>
                    <li>
                        <a class="button" href="/cafe.by/order_accept?order_id=${order.id}">Подтвердить</a>
                        <a class="button" href="/cafe.by/order_deny?order_id=${order.id}">Отменить</a>
                    </li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>${found_nothing_word}</h2>
            </c:otherwise>
        </c:choose>
    </ul>
</section>
