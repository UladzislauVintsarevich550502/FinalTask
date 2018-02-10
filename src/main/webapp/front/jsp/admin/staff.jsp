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


            <!— Section —>
            <section>
                <header class="major">
                    <h2>${list_personal_word}</h2>
                </header>
                <c:choose>
                    <c:when test="${allStaff!=null}">
                        <c:forEach var="staff" items="${allStaff}">
                            <li id="client_s">${staff.id} ${staff.login} </li>
                            <h4>
                                <a href="/cafe.by/delete_staff?staff_id=${staff.id}" class="button">${delete_word}</a>
                            </h4>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h2>${found_nothing_word}</h2>
                    </c:otherwise>
                </c:choose>
            </section>
            <section>
                <h2>${add_personal_word}</h2>
                <form id="staff-form" class="cd-form" method="POST" action="/cafe.by/add_staff">
                    <p class="fieldset">
                        <label class="image-replace cd-login" for="staff-login">${login_word}</label>
                        <input class="full-width has-padding has-border" name="staff_login" id="staff-login"
                               type="text"
                               placeholder="${login_word}">
                        <span class="cd-error-message">${mistake_login_word}</span>
                    </p>
                    <p class="fieldset">
                        <label class="image-replace cd-password" for="staff-password">${password_word}</label>
                        <input class="full-width has-padding has-border" name="staff_password" id="staff-password"
                               type="text" placeholder="${password_word}">
                        <a href="#0" id="a-signin-password" class="hide-password">""</a>
                        <span class="cd-error-message">${mistake_password_word}</span>
                    </p>

                    <p class="fieldset">
                        <input class="full-width" type="submit" value=${add_word}>
                    </p>
                </form>
            </section>
        </div>
    </div>

    <ctg:menu/>

</div>

<!— Scripts —>
<script>
    <%@include file="/front/js/menu/main.js" %>
    <%@include file="/front/js/menu/util.js" %>
    <%@include file="/front/js/form/form.js"%>
    <%@include file="/front/js/validation.js"%>
</script>

</body>
</html>


