<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>
<jsp:useBean class="bsuir.vintsarevich.entity.Order" scope="page" id="order"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
        <%@include file="/front/css/menu/main.css" %>
        <%@include file="/front/css/form/form.css"%>
        <%@include file="/front/css/index/review.css"%>
        <%@include file="/front/css/menu/search.css"%>
        <%@include file="/front/css/form/addForm.css"%>
        <%@include file="/front/css/form/button.css"%>
    </style>
    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
        <%@include file="/front/js/lib/skel.min.js" %>
    </script>

    <%@include file="/front/html/allBundle.html" %>

    <title>Epam Cafe</title>
</head>
<body>
<!-- Wrapper -->
<div id="wrapper">
    <!-- Main -->
    <div id="main">
        <div class="inner">

            <%@include file="/front/html/header.html" %>

            <%@include file="/front/html/forms.html" %>


            <!-- Section -->
            <section>
                <header class="major">
                    <h2>${basket_word}</h2>
                </header>

                <ul class="leaders">
                    <c:choose>
                        <c:when test="${products!=null}">
                            <c:forEach var="product" items="${products}">
                                <c:choose><c:when test="${product.ordered == 0}">
                                    <li>
                                        <c:choose>
                                            <c:when test="${locale eq 'ru'}">
                                                <span>${product.nameRu}(x${product.number})</span>
                                            </c:when>
                                            <c:when test="${locale eq 'en'}">
                                                <span>${product.nameEn}(x${product.number})</span>
                                            </c:when>
                                        </c:choose>
                                        <span>${product.commonCost}</span>
                                    </li>
                                    <form method="POST"
                                          action="/cafe.by/remove_product_from_basket?productId=${product.id}">
                                        <input type="number" step="1" min="0" max="${product.number}" value="0"
                                               id="number-for-delete" name="number_for_delete"
                                               onkeypress="return false">
                                        <input type="submit" id="delete-button-from-basket"
                                               value="Удалить из корзины">
                                    </form>
                                </c:when></c:choose>
                            </c:forEach>
                            <h1>Итого: ${orderCost}</h1>
                        </c:when>
                        <c:otherwise>
                            <h2>${found_nothing_word}</h2>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${products!=null}">
                            <h2>
                                <form method="POST" name="payment" action="/cafe.by/payment">
                                    <div>
                                        <p>Выберете форму оплаты</p>
                                        <input type="radio" class="radio_payment" id="by-card" name="choise_of_payment"
                                               value="card" checked>
                                        <label for="by-card">Кард</label>
                                        <input type="radio" class="radio_payment" id="by-cash" name="choise_of_payment"
                                               value="cash">
                                        <label for="by-cash">Кэш</label>
                                    </div>
                                    <h3>
                                        <p>Время желаемого получения</p>
                                        <input id="dateTime" type="datetime-local" name="dateTime" required>
                                        <span class="validity"></span>
                                    </h3>
                                    <div>
                                        <input type="submit" id="payment_button" value="Оплатить">
                                    </div>
                                </form>
                            </h2>
                        </c:when>
                    </c:choose>
                </ul>
            </section>

            <section>
                <header class="major">
                    <h2>Заказанные</h2>
                </header>

                <ul>
                    <c:choose>
                        <c:when test="${orders!=null}">
                            <c:forEach var="order" items="${orders}">
                                <h3>${order.id}</h3>
                                <c:forEach var="product" items="${products}">
                                    <c:choose>
                                        <c:when test="${product.orderId == order.id}">
                                            <li>
                                                <c:choose>
                                                    <c:when test="${locale eq 'ru'}">
                                                        <p>${product.nameRu}(x${product.number})</p>
                                                    </c:when>
                                                    <c:when test="${locale eq 'en'}">
                                                        <p>${product.nameEn}(x${product.number})</p>
                                                    </c:when>
                                                </c:choose>
                                            </li>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                <div style="display: inline-block">
                                    <h3 id="COST" style="float: left">Итого: ${order.cost} BYN</h3>
                                    <label id="cost_data" for="COST">Дата получения: ${order.data}</label>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h2>${found_nothing_word}</h2>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </section>


        </div>
    </div>

    <%@include file="/front/html/menu.html" %>

</div>

<!-- Scripts -->
<script>
    <%@include file="/front/js/menu/main.js" %>
</script>
<script>
    <%@include file="/front/js/menu/util.js" %>
</script>
<script>
    <%@include file="/front/js/form/form.js"%>
</script>
<script>
    <%@include file="/front/js/elementcontroller.js"%>
</script>

</body>
</html>