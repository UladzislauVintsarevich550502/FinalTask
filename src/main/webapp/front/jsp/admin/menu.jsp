<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>
<html>
<head>
    <%@include file="/front/html/allBundle.html" %>
</head>

<!— Sidebar —>
<div id="sidebar">
    <div class="inner">
        <%@include file="/front/html/search.html" %>
        <!— Menu —>
        <nav id="menu">
            <header class="major">
                <h2>${menu_word}</h2>
            </header>
            <ul>
                <li>
                    <p>${range_word}</p>
                    <span class="opener" id="type_food">${type_food_word1}</span>
                    <ul>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=soupe">Soups</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=hotDish">Hot dishes</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=dessert">Desserts</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=salad">Salads</a></li>
                    </ul>
                    <span class="opener" id="type_drink">${type_drink_word1}</span>
                    <ul class="podmenu">
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=juices">Juices</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=water">Water</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=hotDrink">Hot drinks</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=soda">Soda</a></li>
                    </ul>
                </li>
                <li><a href="/cafe.by/edit_clients">${edit_word}</a></li>
            </ul>
        </nav>

        <%@include file="/front/html/promotions.html" %>
        <%@include file="/front/html/contacts.html" %>
        <%@include file="/front/html/footer.html" %>

    </div>
</div>
</html>