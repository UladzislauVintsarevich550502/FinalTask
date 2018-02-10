<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="tags" %>
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
<!— Wrapper —>
<div id="wrapper">
    <!— Main —>
    <div id="main">
        <div class="inner">

            <%@include file="/front/html/header.html" %>

            <%@include file="/front/html/forms.html" %>
            <div id="add-form">
                <form method="post" class="cd-form" id="change-password" action="/cafe.by/change_password" enctype="multipart/form-date">
                    <h2>${change_password_word}</h2>
                    <p class="fieldset">
                        <input type="text" name="old_password" id="old-password" placeholder=${old_password_word}>
                        <span class="cd-error-message">Неверный пароль</span>
                    </p>
                    <p class="fieldset">
                        <input type="text" name="new_password" id="new-password" placeholder=${new_password_word}>
                        <span class="cd-error-message">Некорректный пароль</span>
                    </p>
                    <p class="fieldset">
                        <input type="text" name="new_password_repeat" id="new-password-repeat" placeholder=${re_new_password_word}>
                        <span class="cd-error-message">Пароль не совпадают</span>
                    </p>
                    <input type="submit" id="add" value=${change_password_word}>
                </form>
            </div> <!— login —>

        </div>
    </div>

    <ctg:menu/>

</div>

<!— Scripts —>
<script>
    <%@include file="/front/js/menu/main.js" %>
</script>
<script>
    <%@include file="/front/js/menu/util.js" %>
</script>
<script>
    <%@include file="/front/js/form/form.js"%>
</script>

</body>
</html>
