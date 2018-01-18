package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOut implements bsuir.vintsarevich.command.ICommand {

    private static Logger logger = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.INFORMATION;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String login = request.getSession().getAttribute(JspElemetName.USER_LOGIN.getValue()).toString();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(JspElemetName.J_SESSION_ID.getValue())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);

            }
        }
        request.getSession().removeAttribute(JspElemetName.USER_LOGIN.toString());
        request.getSession().removeAttribute(JspElemetName.USER_ROLE.toString());
        request.getSession().removeAttribute(JspElemetName.USER_ID.toString());
        request.getSession().invalidate();
        logger.debug(login + " came out");
        return jspPageName.getPath();
    }
}
