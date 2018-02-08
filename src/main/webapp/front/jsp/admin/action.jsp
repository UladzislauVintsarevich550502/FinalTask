<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>
<html>
<head>
    <%@include file="/front/html/allBundle.html" %>
</head>
<section>
    <header class="major">
        <h2>${range_word}</h2>
    </header>
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
                            <div class="wall_form" id="popup_message_form_${product.id}" style="display:none;">
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
                            <%--<div class="wall_form" id="popup_edit_form_${product.id}" style="display:none;">--%>
                                <%--<form method="post" class="cd-form" action="/cafe.by/add_product">--%>
                                    <%--<%@include file="/front/html/addForm.html" %>--%>
                                <%--</form>--%>
                            <%--</div>--%>
                            <ul class="actions">
                                <li>
                                    <input type="button" id="click_mes_form_${product.id}"
                                           value="${view_word}">
                                </li>
                                <li>
                                    <input type="button" id="click_edit_form_${product.id}"
                                           value="${view_word}">
                                </li>
                                <script type="text/javascript">
                                    $(document).ready(function () {
                                        $("#click_edit_form_${product.id}").click(function () {
                                            $("#popup_edit_form_${product.id}").slideToggle("slow");
                                            return false;
                                        });
                                        $("#click_mes_form_${product.id}").click(function () {
                                            $("#popup_message_form_${product.id}").slideToggle("slow");
                                            return false;
                                        });
                                    });
                                </script>
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
    <div class="posts">
        <article>
            <form method="post" class="cd-form" id="add-product" action="/cafe.by/add_product"
                  enctype="multipart/form-data">
                <%@include file="/front/html/addForm.html" %>
            </form>
        </article>
    </div>
</section>