<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>
<jsp:useBean class="bsuir.vintsarevich.entity.Role" scope="page" id="role"/>

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
    <title>Epam Cafe</title>
</head>
<body>

<!-- Wrapper -->
<div id="wrapper">

    <!-- Main -->
    <div id="main">
        <div class="inner">

            <!-- Header -->
            <header id="header">
                <a href="index.do" class="logo"><strong>Editorial</strong> by HTML5 UP</a>
                <nav class="main-nav">
                    <ul>
                        <!-- ссылки на вызов форм -->
                        <li><a class="cd-signin" href="#0">Вход</a></li>
                        <li><a class="cd-signup" href="#0">Регистрация</a></li>
                    </ul>
                </nav>
            </header>

            <div class="cd-user-modal"> <!-- все формы на фоне затемнения-->
                <div class="cd-user-modal-container"> <!-- основной контейнер -->
                    <ul class="cd-switcher">
                        <li><a href="#0">Вход</a></li>
                        <li><a href="#0">Регистрация</a></li>
                    </ul>

                    <div id="cd-login"> <!-- форма входа -->
                        <form class="cd-form">
                            <p class="fieldset">
                                <label class="image-replace cd-email" for="signin-login">E-mail</label>
                                <input class="full-width has-padding has-border" id="signin-login" type="text"
                                       placeholder="Login">
                                <span class="cd-error-message">Здесь сообщение об ошибке!</span>
                            </p>

                            <p class="fieldset">
                                <label class="image-replace cd-password" for="signin-password">Пароль</label>
                                <input class="full-width has-padding has-border" id="signin-password" type="text"
                                       placeholder="Пароль">
                                <a href="#0" class="hide-password">Скрыть</a>
                                <span class="cd-error-message">Здесь сообщение об ошибке!</span>
                            </p>

                            <p class="fieldset">
                                <input type="checkbox" id="remember-me" checked>
                                <label for="remember-me">Запомнить меня</label>
                            </p>

                            <p class="fieldset">
                                <input class="full-width" type="button" value="Войти">
                            </p>
                        </form>

                        <p class="cd-form-bottom-message"><a href="#0">Забыли свой пароль?</a></p>
                        <!-- <a href="#0" class="cd-close-form">Close</a> -->
                    </div> <!-- cd-login -->

                    <div id="cd-signup"> <!-- форма регистрации -->
                        <form class="cd-form">
                            <p class="fieldset">
                                <label class="image-replace cd-username" for="signup-username">Имя пользователя</label>
                                <input class="full-width has-padding has-border" id="signup-username" type="text"
                                       placeholder="Имя пользователя">
                                <span class="cd-error-message">Здесь сообщение об ошибке!</span>
                            </p>

                            <p class="fieldset">
                                <label class="image-replace cd-email" for="signup-email">E-mail</label>
                                <input class="full-width has-padding has-border" id="signup-email" type="email"
                                       placeholder="E-mail">
                                <span class="cd-error-message">Здесь сообщение об ошибке!</span>
                            </p>

                            <p class="fieldset">
                                <label class="image-replace cd-password" for="signup-password">Пароль</label>
                                <input class="full-width has-padding has-border" id="signup-password" type="text"
                                       placeholder="Пароль">
                                <a href="#0" class="hide-password">Скрыть</a>
                                <span class="cd-error-message">Здесь сообщение об ошибке!</span>
                            </p>

                            <p class="fieldset">
                                <input type="checkbox" id="accept-terms">
                                <label for="accept-terms">Я согласен с <a href="#0">Условиями</a></label>
                            </p>

                            <p class="fieldset">
                                <input class="full-width" type="button" value="Создать аккаунт">
                            </p>
                        </form>

                        <!-- <a href="#0" class="cd-close-form">Close</a> -->
                    </div> <!-- cd-signup -->

                    <div id="cd-reset-password"> <!-- форма восстановления пароля -->
                        <p class="cd-form-message">Забыли пароль? Пожалуйста, введите адрес своей электронной почты. Вы
                            получите ссылку, чтобы создать новый пароль.</p>

                        <form class="cd-form">
                            <p class="fieldset">
                                <label class="image-replace cd-email" for="reset-email">E-mail</label>
                                <input class="full-width has-padding has-border" id="reset-email" type="email"
                                       placeholder="E-mail">
                                <span class="cd-error-message">Здесь сообщение об ошибке!</span>
                            </p>

                            <p class="fieldset">
                                <input class="full-width has-padding" type="button" value="Восстановить пароль">
                            </p>
                        </form>

                        <p class="cd-form-bottom-message"><a href="#0">Вернуться к входу</a></p>
                    </div> <!-- cd-reset-password -->
                    <a href="#0" class="cd-close-form">Закрыть</a>
                </div> <!-- cd-user-modal-container -->
            </div> <!-- cd-user-modal -->

            <!-- Banner -->
            <section id="banner">
                <div class="content">
                    <header>
                        <h1>Hi, I’m Editorial<br/>
                            by HTML5 UP</h1>
                        <p>A free and fully responsive site template</p>
                    </header>
                    <p>Aenean ornare velit lacus, ac varius enim ullamcorper eu. Proin aliquam facilisis ante interdum
                        congue. Integer mollis, nisl amet convallis, porttitor magna ullamcorper, amet egestas mauris.
                        Ut magna finibus nisi nec lacinia. Nam maximus erat id euismod egestas. Pellentesque sapien ac
                        quam. Lorem ipsum dolor sit nullam.</p>
                    <ul class="actions">
                        <li><a href="#" class="button big">Learn More</a></li>
                    </ul>
                </div>
                <span class="image object">
										<img src="images/pic10.jpg" alt=""/>
									</span>
            </section>

            <!-- Section -->
            <section>
                <header class="major">
                    <h2>Ipsum sed dolor</h2>
                </header>
                <div class="posts">
                    <c:choose>
                        <c:when test="${products!=null}">
                            <c:forEach var="product" items="${products}">
                                <article>
                                    <a href="#" class="image"><img src="images/products/${product.imagePath}"
                                                                   alt=""/></a>
                                    <h3>${product.name}</h3>
                                    <p>${product.description}</p>
                                    <ul class="actions">
                                        <li><a href="/product.do?id=${product.id}" class="button">Просмотреть</a></li>
                                    </ul>
                                </article>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h2>Ничего не найдено</h2>
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>

        </div>
    </div>

    <!-- Sidebar -->
    <div id="sidebar">
        <div class="inner">

            <!-- Search -->
            <section id="search" class="alt">
                <form method="post" action="#">
                    <input type="text" name="query" id="query" placeholder="Search"/>
                </form>
            </section>

            <!-- Menu -->
            <nav id="menu">
                <header class="major">
                    <h2>Menu</h2>
                </header>
                <ul>
                    <li><a href="index.jsp">Homepage</a></li>
                    <li><a href="generic.html">Generic</a></li>
                    <li><a href="elements.html">Elements</a></li>
                    <li>
                        <span class="opener">Submenu</span>
                        <ul>
                            <li><a href="#">Lorem Dolor</a></li>
                            <li><a href="#">Ipsum Adipiscing</a></li>
                            <li><a href="#">Tempus Magna</a></li>
                            <li><a href="#">Feugiat Veroeros</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Etiam Dolore</a></li>
                    <li><a href="#">Adipiscing</a></li>
                    <li>
                        <span class="opener">Another Submenu</span>
                        <ul>
                            <li><a href="#">Lorem Dolor</a></li>
                            <li><a href="#">Ipsum Adipiscing</a></li>
                            <li><a href="#">Tempus Magna</a></li>
                            <li><a href="#">Feugiat Veroeros</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Maximus Erat</a></li>
                    <li><a href="#">Sapien Mauris</a></li>
                    <li><a href="#">Amet Lacinia</a></li>
                </ul>
            </nav>

            <!-- Section -->
            <section>
                <header class="major">
                    <h2>Ante interdum</h2>
                </header>
                <div class="mini-posts">
                    <article>
                        <a href="#" class="image"><img src="images/pic07.jpg" alt=""/></a>
                        <p>Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.</p>
                    </article>
                    <article>
                        <a href="#" class="image"><img src="images/pic08.jpg" alt=""/></a>
                        <p>Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.</p>
                    </article>
                    <article>
                        <a href="#" class="image"><img src="images/pic09.jpg" alt=""/></a>
                        <p>Aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore aliquam.</p>
                    </article>
                </div>
                <ul class="actions">
                    <li><a href="#" class="button">More</a></li>
                </ul>
            </section>

            <!-- Section -->
            <section>
                <header class="major">
                    <h2>Get in touch</h2>
                </header>
                <p>Sed varius enim lorem ullamcorper dolore aliquam aenean ornare velit lacus, ac varius enim lorem
                    ullamcorper dolore. Proin sed aliquam facilisis ante interdum. Sed nulla amet lorem feugiat tempus
                    aliquam.</p>
                <ul class="contact">
                    <li class="fa-envelope-o"><a href="#">information@untitled.tld</a></li>
                    <li class="fa-phone">(000) 000-0000</li>
                    <li class="fa-home">1234 Somewhere Road #8254<br/>
                        Nashville, TN 00000-0000
                    </li>
                </ul>
            </section>

            <!-- Footer -->
            <footer id="footer">
                <p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>.
                    Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
                <ul class="icons">
                    <li>
                        <a href="https://www.facebook.com/Loveforty1">
                            <img src="../images/footer/facebook.png">
                        </a>
                    </li>
                    <li>
                        <a href="https://twitter.com/loveforty1">
                            <img src="../images/footer/twitter.png">
                        </a>
                    </li>
                    <li>
                        <a href="http://instagram.com/loveforty1">
                            <img src="../images/footer/instagram.png">
                        </a>
                    </li>
                    <li>
                        <a href="http://www.pinterest.com/loveforty1/">
                            <img src="../images/footer/pin.png">
                        </a>
                    </li>
                </ul>
            </footer>

        </div>
    </div>

</div>

<!-- Scripts -->
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