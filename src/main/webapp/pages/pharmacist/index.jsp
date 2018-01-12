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

    <button onclick="$('#add-product').show();">Дoбавить медикамент</button>

    <table>
        <c:choose>
            <c:when test="${products!=null}">
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td><img src="images/products/${product.imagePath}" alt="${product.name}" style="width:100%">
                        </td>
                        <td>${product.name}</td>
                        <td>${product.producer}</td>
                        <td>${product.price}</td>
                        <td><a href="/product.do?id=${product.id}">Просмотреть</a></p></td>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2>Ничего не найдено</h2>
            </c:otherwise>
        </c:choose>
    </table>
</div>
<%@include file="../forms.html" %>
<%@include file="../footer.html" %>

<div id="add-product" class="popup">
    <div class="card">
        <form name="Reviews" method="POST" id="form" action="/add_medicament.do"
              onsubmit="return validMedicament(this)">
            <h2>Добавление медикамента</h2>
            <input type="text" name="name" id="name" placeholder="Название">
            <input type="text" name="producer" id="producer" placeholder="Производитель">
            <input type="text" name="price" id="price" placeholder="Цена">
            <input type="checkbox" name="prescription" id="prescription" placeholder="Нужен рецепт">
            <input type="file" name="imagePath" id="imagePath">
            <input type="checkbox" name="availability" id="availability">

            <input type="submit" name="Добавить" value="Добавить" class="button1">
            <input type="button" name="Закрыть" value="Закрыть" onclick="hideSignUp();" class="button1">
        </form>
    </div>
</div>

</body>
</html>
