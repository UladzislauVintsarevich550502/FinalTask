<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>
<jsp:useBean class="bsuir.vintsarevich.entity.Client" scope="page" id="client"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="shortcut icon" href="/images/favicon.ico" type="imagePath/x-icon">
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
<%@include file="../header.html" %>
<div class="page">
    <button onclick="$('#add-client').show();">Дoбавить работника</button>

    <div class="row">
        <c:choose>
            <c:when test="${clients!=null}">
                <table>
                    <c:forEach var="client" items="${clients}">
                        <tr>
                            <td>${client.name}</td>
                            <td>${client.surname}</td>
                            <td>${client.email}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h2>Ничего не найдено</h2>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="../forms.html" %>
<%@include file="../footer.html" %>
<div id="add-client" class="popup">
    <div class="card">
        <form name="Reviews" method="POST" id="form" action="/add_user.do" onsubmit="return valid(this)">
            <h2>Добавление работника</h2>
            <input class="signup" type="text" name="name" placeholder="Введите имя" id="name"/>
            <input class="signup" type="text" name="surname" placeholder="Введите фамилию" id="surname"/>
            <input class="signup" type="text" name="address" placeholder="Введите адрес" id="address">
            <div class="tooltip">
                <input class="signup" type="text" name="email" placeholder="Введите e-mail" id="email"/>
                <span class="tooltiptext">Введите e-mail в виде name@mail.ru.</span>
            </div>
            <div class="tooltip">
                <input class="signup" type="text" name="login" placeholder="Введите логин">
                <span class="tooltiptext">Разрешено использовать латинские
                            буквы, цифры и знак "_".
                            Первый символ латинская буква.
                        Длина логина не менее 5 символов.</span>
            </div>
            <div class="tooltip">
                <input class="signup" type="password" name="password" placeholder="Введите пароль" id="password">
                <span class="tooltiptext">Пароль должен содержать не менее
                            6 символов.Не менее одной буквы в каждом
                            регистре и не менее одной цифры.</span>
            </div>
            <input class="signup" type="password" name="rePassword" placeholder="Подтвердите пароль" id="repassword">

            <input type="submit" name="Добавить" value="Добавить" class="button1">
            <input type="button" name="Закрыть" value="Закрыть" onclick="hideSignUp();" class="button1">
        </form>
    </div>
</div>
</body>
</html>
