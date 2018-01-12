<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>
<jsp:useBean class="bsuir.vintsarevich.entity.Client" scope="page" id="client"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
        <%@include file="/css/index.css" %>
    </style>
    <script>
        <%@include file="/js/index.js" %>
    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script>

        function openMenu() {
            if ($("#nav > a:nth-child(2) > li").css("display") == 'none') {
                $("nav > ul > a > li").show();
            } else {
                $("nav > ul > a > li").hide();
            }
        }
    </script>
    <title>PHARMACY</title>
</head>

<body>
<%@include file="../header.html" %>
<div class="page">

    <div class="row">
        <c:choose>
            <c:when test="${products!=null}">
                <c:forEach var="product" items="${products}">
                    <div class="column">
                        <div class="card">
                            <img src="images/products/${product.imagePath}" alt="${product.name}" style="width:100%">
                            <div class="container">
                                <h2>${product.name}</h2>
                                <p class="title">${product.producer}</p>
                                <p>${product.price}</p>
                                <p class="bottom-button"><a href="/product.do?id=${product.id}">
                                    <button class="button">Просмотреть</button>
                                </a></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>Ничего не найдено</h2>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="../forms.html" %>
<footer>Mariya Horuzhenko © 2018.</footer>

</body>
</html>
