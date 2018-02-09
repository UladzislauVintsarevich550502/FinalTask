<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
        <%@include file="/front/css/menu/main.css" %>
    </style>
    <style>
        <%@include file="/front/css/form/form.css"%>
    </style>
    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
    </script>
    <script>
        <%@include file="/front/js/lib/skel.min.js" %>
    </script>

    <fmt:setLocale scope="session" value="${locale}"/>
    <fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
    <fmt:message bundle="${loc}" key="local.word.welcome" var="welcome_word"/>
    <fmt:message bundle="${loc}" key="local.word.cafename_with_other_information"
                 var="cafename_with_other_information_word"/>
    <fmt:message bundle="${loc}" key="local.word.cafe_description" var="cafe_description_word"/>
    <fmt:message bundle="${loc}" key="local.word.login" var="login_word"/>
    <fmt:message bundle="${loc}" key="local.word.password" var="password_word"/>
    <fmt:message bundle="${loc}" key="local.word.name" var="name_word"/>
    <fmt:message bundle="${loc}" key="local.word.surname" var="surname_word"/>
    <fmt:message bundle="${loc}" key="local.word.remember" var="remember_word"/>
    <fmt:message bundle="${loc}" key="local.word.email" var="email_word"/>
    <fmt:message bundle="${loc}" key="local.button.signin" var="signin_button"/>
    <fmt:message bundle="${loc}" key="local.button.signup" var="signup_button"/>
    <fmt:message bundle="${loc}" key="local.button.signout" var="signout_button"/>
    <fmt:message bundle="${loc}" key="local.button.create" var="create_button"/>
    <fmt:message bundle="${loc}" key="local.button.comein" var="comein_button"/>
    <fmt:message bundle="${loc}" key="local.word.home" var="home_word"/>
    <fmt:message bundle="${loc}" key="local.word.view" var="view_word"/>
    <fmt:message bundle="${loc}" key="local.word.description_under_welcome" var="description_under_welcome_word"/>
    <fmt:message bundle="${loc}" key="local.word.add_product" var="add_product_word"/>
    <fmt:message bundle="${loc}" key="local.word.type_product" var="type_product_word"/>
    <fmt:message bundle="${loc}" key="local.word.name_ru" var="name_ru_word"/>
    <fmt:message bundle="${loc}" key="local.word.name_en" var="name_en_word"/>
    <fmt:message bundle="${loc}" key="local.word.volume" var="volume_word"/>
    <fmt:message bundle="${loc}" key="local.word.existence" var="existence_word"/>
    <fmt:message bundle="${loc}" key="local.word.desc_en" var="desc_en_word"/>
    <fmt:message bundle="${loc}" key="local.word.desc_ru" var="desc_ru_word"/>
    <fmt:message bundle="${loc}" key="local.word.cost" var="cost_word"/>
    <fmt:message bundle="${loc}" key="local.word.type_drink" var="type_drink_word"/>
    <fmt:message bundle="${loc}" key="local.word.type_food" var="type_food_word"/>
    <fmt:message bundle="${loc}" key="local.word.type_drink1" var="type_drink_word1"/>
    <fmt:message bundle="${loc}" key="local.word.type_food1" var="type_food_word1"/>
    <fmt:message bundle="${loc}" key="local.word.existense_no" var="existense_no_word"/>
    <fmt:message bundle="${loc}" key="local.word.existanse_yes" var="existanse_yes_word"/>
    <fmt:message bundle="${loc}" key="local.word.choose_file" var="choose_file_word"/>
    <fmt:message bundle="${loc}" key="local.word.choose_not_choosen" var="choose_not_choosen_word"/>
    <fmt:message bundle="${loc}" key="local.word.add_button" var="add_button_word"/>
    <fmt:message bundle="${loc}" key="local.word.found_nothing" var="found_nothing_word"/>
    <fmt:message bundle="${loc}" key="local.word.menu" var="menu_word"/>
    <fmt:message bundle="${loc}" key="local.word.search" var="search_word"/>
    <fmt:message bundle="${loc}" key="local.word.contacts" var="contacts_word"/>
    <fmt:message bundle="${loc}" key="local.word.address" var="address_word"/>
    <fmt:message bundle="${loc}" key="local.word.street" var="street_word"/>
    <fmt:message bundle="${loc}" key="local.word.stocks" var="stocks_word"/>
    <fmt:message bundle="${loc}" key="local.word.stock1" var="stock1_word"/>
    <fmt:message bundle="${loc}" key="local.word.stock2" var="stock2_word"/>
    <fmt:message bundle="${loc}" key="local.word.re_password" var="re_password_word"/>
    <fmt:message bundle="${loc}" key="local.word.mistake" var="mistake_word"/>
    <fmt:message bundle="${loc}" key="local.word.range" var="range_word"/>
    <fmt:message bundle="${loc}" key="local.word.forgot_pass" var="forgot_pass_word"/>
    <fmt:message bundle="${loc}" key="local.word.re_pass" var="re_pass_word"/>
    <fmt:message bundle="${loc}" key="local.word.back" var="back_word"/>
    <fmt:message bundle="${loc}" key="local.word.re_message" var="re_message_word"/>
    <fmt:message bundle="${loc}" key="local.word.mistake_login" var="mistake_login_word"/>
    <fmt:message bundle="${loc}" key="local.word.mistake_password" var="mistake_password_word"/>
    <fmt:message bundle="${loc}" key="local.word.mistake_email" var="mistake_email_word"/>
    <fmt:message bundle="${loc}" key="local.word.close" var="close_word"/>
    <fmt:message bundle="${loc}" key="local.word.mistake_name" var="mistake_name_word"/>
    <fmt:message bundle="${loc}" key="local.word.mistake_surname" var="mistake_surname_word"/>
    <fmt:message bundle="${loc}" key="local.word.exit" var="exit_word"/>
    <fmt:message bundle="${loc}" key="local.word.edit" var="edit_word"/>
    <fmt:message bundle="${loc}" key="local.word.basket_add" var="basket_add_word"/>
    <fmt:message bundle="${loc}" key="local.word.basket" var="basket_word"/>
    <fmt:message bundle="${loc}" key="local.word.basket_delete" var="basket_delete_word"/>
    <fmt:message bundle="${loc}" key="local.word.found_nothing" var="found_nothing_word"/>
    <fmt:message bundle="${loc}" key="local.word.clients_all" var="client_all_word"/>
    <fmt:message bundle="${loc}" key="local.word.client_ban" var="client_ban_word"/>
    <fmt:message bundle="${loc}" key="local.word.client_unban" var="client_unban_word"/>


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
            <div id="add-form"> <!— форма входа —>
                <form method="post" class="cd-form" id="change-password" action="/cafe.by/change_password" enctype="multipart/form-date">
                    <h2>Смена пароля</h2>
                    <p class="fieldset">
                        <input type="text" name="old_password" id="old-password" placeholder="Введите текущий пароль">
                        <span class="cd-error-message">Неверный пароль</span>
                    </p>
                    <p class="fieldset">
                        <input type="text" name="new_password" id="new-password" placeholder="Введите новый пароль">
                        <span class="cd-error-message">Некорректный пароль</span>
                    </p>
                    <p class="fieldset">
                        <input type="text" name="new_password_repeat" id="new-password-repeat" placeholder="Повторите новый пароль">
                        <span class="cd-error-message">Пароль не совпадают</span>
                    </p>
                    <input type="submit" id="add" value=${add_button_word}>
                </form>
            </div> <!— login —>

        </div>
    </div>

    <%@include file="/front/html/menu.html" %>

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