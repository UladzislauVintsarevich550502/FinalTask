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
    <!-- <script type="text/javascript">
         window.location.href = "index.do"
     </script> -->
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
    <title>Ошибка</title>
</head>

<body>
<%@include file="../header.html" %>
<div class="page">
    Ошибка. Обратитесь к администратору.
</div>
</body>
<footer>Mariya Horuzhenko © 2018.</footer>
<%@include file="../footer.html" %>
<%@include file="../forms.html" %></body>
</html>
