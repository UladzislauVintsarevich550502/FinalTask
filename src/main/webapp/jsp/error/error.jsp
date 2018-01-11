<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<h1> Sorry, error ${alert_value}</h1>
Request from ${pageContext.information.requestURI} is failed
<br/>
Servlet name: ${pageContext.information.servletName}
<br/>
Status code: ${pageContext.information.statusCode}
<br>
Message from exception: ${pageContext.information.throwable}
<br>
Message from exception: ${pageContext.information}
<script>
    alert("${alert_value}");
</script>
<form action ="/hotelrooming" method="post">
    <input type="hidden" name ="command" value="redirect"/>
    <input type="submit" value="перенаправить"/>
</form>
</body>
</html>
