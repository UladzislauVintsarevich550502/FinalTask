<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>

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

            <%@include file="/front/html/banner.html" %>

            <!-- Section -->
            <section>
                <header class="major">
                    <h2>${range_word}</h2>
                </header>
                <form method="POST"
                      action="/cafe.by/add_product_to_basket">
                    <div class="posts">
                        <c:choose>
                            <c:when test="${products!=null}">
                                <c:forEach var="product" items="${products}">
                                    <article>
                                        <a href="#" class="image"><img src="/images/products/${product.imagePath}"
                                                                       alt="lorem"/></a>
                                        <c:choose>
                                            <c:when test="${locale eq 'ru'}">
                                                <h3>${product.nameRu}</h3>
                                            </c:when>
                                            <c:when test="${locale eq 'en'}">
                                                <h3>${product.nameEn}</h3>
                                            </c:when>
                                        </c:choose>
                                        <p>${product.cost}</p>
                                        <div class="wall_form" id="popup_message_form_${product.id}"
                                             style="display:none;">
                                            <p>${product.weight}</p>
                                            <c:choose>
                                                <c:when test="${locale eq 'en'}">
                                                    <p>${product.descriptionEn}</p>
                                                </c:when>
                                                <c:when test="${locale eq 'ru'}">
                                                    <p>${product.descriptionRu}</p>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <ul class="actions">
                                            <li><input type="button" id="click_mes_form_${product.id}"
                                                       value="${view_word}">
                                                <script type="text/javascript">
                                                    $(document).ready(function () {
                                                        $("#click_mes_form_${product.id}").click(function () {
                                                            $("#popup_message_form_${product.id}").slideToggle("slow");
                                                            $(this).toggleClass("active");
                                                            return false;
                                                        });
                                                    });
                                                </script>
                                            </li>
                                            <c:choose>
                                                <c:when test="${user.role eq 'client'}">
                                                    <li>
                                                        <input type="number" step="1" min="0" max="10"
                                                               value="0" class="number" id="number-for-add-${product.id}"
                                                               name="number_for_add_${product.id}"
                                                               onkeypress="return false">
                                                        <script type="text/javascript">
                                                            $(document).ready(function () {
                                                                $("#number-for-add-${product.id}").click(function () {
                                                                    width = screen.width;
                                                                    if (width < 500) {
                                                                        var i = $("#number-for-add-${product.id}").val();
                                                                        if (i == 10) {
                                                                            $("#number-for-add-${product.id}").val(0);
                                                                        } else {
                                                                            i = +i + 1;
                                                                            $("#number-for-add-${product.id}").val(i);
                                                                        }
                                                                    }
                                                                    return false;
                                                                });
                                                            });
                                                        </script>
                                                        <input type="text" style="display:none;"
                                                               name="productId_${product.id}"
                                                               value="${product.id}">
                                                    </li>
                                                </c:when>

                                            </c:choose>
                                        </ul>
                                    </article>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <h2>${found_nothing_word}</h2>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <c:choose>
                        <c:when test="${user.role eq 'client'}">
                            <div class="posts">
                                <article>
                                    <input type="submit" id="add-button-to-basket"
                                           value="${basket_add_word}">
                                </article>
                            </div>
                        </c:when>
                    </c:choose>
                </form>
                <c:choose>
                    <c:when test="${user.role eq 'admin'}">
                        <div class="posts">
                            <article>
                                <%@include file="/front/html/addForm.html" %>
                            </article>
                        </div>
                    </c:when>
                </c:choose>
            </section>

            <%@include file="/front/html/navigation.html" %>

            <section>
                <c:choose>
                    <c:when test="${user.role eq 'client'}">
                        <div id="rating">
                            <form method="post" id="add-review" action="/cafe.by/add_review">
                                <h2>Оставить отзыв:</h2>
                                <textarea name="review_text" id="review_text" placeholder="Напишите отзыв"></textarea>
                                <div id="mark">
                                    <div class="param">Оценка:</div>
                                    <div id="stars_form">
                                        <div class="stars"></div>
                                        <p class="progress" id="p1"></p>
                                    </div>
                                    <div class="rating" id="param1"><input type="text" id="mark_value" name="mark_value"
                                                                           value="5.0">
                                    </div>
                                    <div id="send-review"><input type="submit" value="Отправить отзыв"></div>
                                </div>
                            </form>
                        </div>


                    </c:when>
                </c:choose>
            </section>

        </div>
    </div>

    <%@include file="/front/html/menu.html" %>

</div>

<!-- Scripts -->
<script>
    <%@include file="/front/js/menu/main.js" %>
    <%@include file="/front/js/menu/util.js" %>
    <%@include file="/front/js/form/form.js"%>
    <%@include file="/front/js/validation.js" %>
    <%@include file="/front/js/elementcontroller.js" %>
</script>


</body>
</html>