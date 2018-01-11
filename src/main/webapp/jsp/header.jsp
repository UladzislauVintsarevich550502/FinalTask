<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head><title>Fragment</title></head>
<body>
<div id="wrap">
    <div id="regbar">
        <div id="navthing">
            <h2 id="Registr"><a href="#" id="loginForm">Login</a> | <a href="#" id="registerForm">Register</a></h2>
            <%--<form method="POST" action="/sign_in.do">--%>
                <div class="login">
                    <div class="loginArrow-up"></div>
                    <div class="loginFormholder">
                        <div class="loginRandompad">
                            <fieldset>
                                <label name="loginEmail">Email</label>
                                <input type="auth_login" name="auth_login" id="loginEmail"/>
                                <label name="loginPassword"><Passwo></Passwo>rd</label>
                                <input type="auth_password" name="auth_password" id="loginPassword"/>
                                <input type="checkbox" name="remember_me"><label>Remember me</label></li>
                                <p align="right" id="button_auth"><input type="submit" value="Enter"></p>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </form>
            <form method="POST" action="/sign_up.do" onsubmit="return valid(this)">
                <div class="register">
                    <div class="registerArrow-up"></div>
                    <div class="registerFormholder">
                        <div class="registerRandompad">
                            <fieldset>
                                <label name="registerName">Name</label>
                                <input type="registerName" name="registerName" value="Влад"/>
                                <label name="registerSurname">Surname</label>
                                <input type="registerSurname" name="registerSurname" value="Винцаревич"/>
                                <label name="registerEmail">Email</label>
                                <input type="registerEmail" name="registerEmail" value="example@example.com"/>
                                <label name="registerPassword">Password</label>
                                <input type="registerPassword" name="registerPassword"/>
                                <p align="right"><input type="submit" name="reg_submit" id="form_submit"
                                                        value="Register"></p>
                            </fieldset>
                        </div>
                    </div>
                </div>
            <%--</form>--%>
        </div>
    </div>
</div>
</body>
</html>