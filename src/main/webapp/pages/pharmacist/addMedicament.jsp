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
                <form id="searchForm" method="POST" action="/medicaments_by_name.do"><input type="text" name="name"
                                                                                            placeholder="Поиск"></form>
            </li>
        </ul>
        <c:choose>
            <c:when test="${user_login!=null}">
                <ul id="auth">
                    <li>${user_login}</li>
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
    <form action="/add_medicament.do" method="POST" enctype="multipart/form-data">
        <input type="text" name="name" id="name" placeholder="Название">
        <input type="text" name="producer" id="producer" placeholder="Производитель">
        <input type="text" name="price" id="price" placeholder="Цена">
        <input type="checkbox" name="prescription" id="prescription" placeholder="Нужен рецепт">
        <input type="file" name="imagePath" id="imagePath">
        <input type="checkbox" name="availability" id="availability">
        <input type="submit" value="Добавить">
    </form>
</div>


<footer>Mariya Horuzhenko © 2018.</footer>

</body>
</html>
