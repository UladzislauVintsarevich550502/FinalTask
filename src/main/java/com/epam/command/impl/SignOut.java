package com.epam.command.impl;

import com.epam.servlet.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOut implements com.epam.command.ICommand {

    private static Logger logger = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.INFORMATION;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String login = request.getSession().getAttribute(RequestEnum.USER_LOGIN.getValue()).toString();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(RequestEnum.J_SESSION_ID.getValue())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);

            }
        }
        request.getSession().removeAttribute(RequestEnum.USER_LOGIN.toString());
        request.getSession().removeAttribute(RequestEnum.USER_ROLE.toString());
        request.getSession().removeAttribute(RequestEnum.USER_ID.toString());
        request.getSession().invalidate();
        logger.debug(login + " came out");
        return jspPageName.getPath();
    }
}
