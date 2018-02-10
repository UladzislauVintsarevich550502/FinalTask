package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.enumeration.AttributeName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOut implements bsuir.vintsarevich.command.ICommand {

    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.ERROR;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Sign out start");
        try {
            String locale = SessionElements.getLocale(request);
            request.getSession().removeAttribute(AttributeName.USER.toString());
            request.getSession().invalidate();
            request.getSession().setAttribute("locale",locale);
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Sign out finish");
        return jspPageName.getPath();
    }
}
