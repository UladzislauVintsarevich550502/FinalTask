<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>
<html>
<head>
    <%@include file="/front/html/allBundle.html" %>
</head>

<!— Sidebar —>
<div id="sidebar">
    <div class="inner">

        <%@include file="/front/html/search.html" %>

        <!— Menu —>
        <nav id="menu">
            <header class="major">
                <h2>${menu_word}</h2>
            </header>
            <ul>
                <li>
                    <p>${range_word}</p>
                </li>
            </ul>
        </nav>

        <%@include file="/front/html/promotions.html" %>
        <%@include file="/front/html/contacts.html" %>
        <%@include file="/front/html/footer.html" %>
    </div>
</div>
</html>