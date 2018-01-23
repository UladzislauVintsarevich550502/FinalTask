package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOut implements bsuir.vintsarevich.command.ICommand {

    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.INFORMATION;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Start Sign Out");
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(JspElemetName.J_SESSION_ID.getValue())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            request.getSession().removeAttribute(JspElemetName.USER.toString());
            request.getSession().invalidate();
            request.getRequestDispatcher("/index.do").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "Command: Finish Sign Out");
        return jspPageName.getPath();
    }
}
