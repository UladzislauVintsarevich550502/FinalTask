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
        function showSignUp() {
            $("#signup").show();
        }

        function hideSignUp() {
            $("#signup").hide();
        }

        function showSignIn() {
            $("#login").show();
        }

        function hideSignIn() {
            $("#login").hide();
        }

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

<header>
    <nav>
        <ul id="nav">
            <a>
                <li id="icon-nav" onclick="openMenu();"><img src="images/menu.png" class="icon"></li>
            </a>
            <a href="index.do">
                <li>Главная</li>
            </a>
            <a href="contacts">
                <li>Контакты</li>
            </a>
            <a href="news">
                <li>Новости</li>
            </a>
            <li id="search">
                <form id="searchForm" method="POST" action="/search"><input type="text" name="searchQuery"
                                                                            placeholder="Поиск"></form>
            </li>
        </ul>
        <c:choose>
            <c:when test="${login!=null}">
                <ul id="auth">
                    <li>${login}</li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul id="auth">
                    <a onclick="showSignUp();" id="registration">
                        <li>Регистрация</li>
                    </a>
                    <a onclick="showSignIn();">
                        <li class="last">Вход</li>
                    </a>
                </ul>
            </c:otherwise>
        </c:choose>
    </nav>
</header>

<div class="page">
    <div class="card">
        <h2 class="medicamentName">${med.name}</h2>
        <img src="images/products/${med.imagePath}" class="good">
        <p class="medicamentDescription">${med.producer}</p>
    </div>


    <form method="POST" enctype="multipart/form-data">
        <input type="text" name="number" id="number" placeholder="Количество"/>
        <input type="text" name="dosage" id="dosage" placeholder="Дозировка"/>
        <input type="submit" formaction="/add_to_basket.do?id=${med.id}" value="Добавить в корзину"/>
        <input type="submit" formaction="/checkout.do?id=${med.id}" value="Оформить заказ"/>
    </form>


</div>
<%@include file="../forms.html" %>
<footer>Mariya Horuzhenko © 2018.</footer>

</body>
</html>
