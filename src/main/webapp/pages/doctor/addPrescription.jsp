<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.vintsarevich.entity.Product" scope="page" id="product"/>
<jsp:useBean class="bsuir.vintsarevich.entity.Client" scope="page" id="client"/>
<html>
<head>
    <title>Выписать рецепт</title>
</head>
<body>
<form action="add_prescription.do" method="post">
    <select name="product">
        <c:forEach var="product" items="${products}">
            <option value="${product.id}">${product.name}</option>
        </c:forEach>
    </select>
    <select name="client">
        <c:forEach var="client" items="${clients}">
            <option value="${client.id}">${client.name} ${client.surname}</option>
        </c:forEach>
    </select>
    <input type="date" name="dateOfCompletion">
    <input type="submit" value="Выписать рецепт">
</form>
</body>
</html>
