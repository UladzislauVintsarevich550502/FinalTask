package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.command.impl.dispatching.Index;
import bsuir.vintsarevich.enumeration.AttributeName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetCurrentPage implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            LOGGER.log(Level.INFO, "Set current page command start");
            Integer currentPage = Integer.valueOf(request.getParameter(AttributeName.CURRENT_PAGE.getValue()));
            request.getSession().setAttribute("currentPage", currentPage);
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Set current page command finish");
        return jspPageName.getPath();
    }
}
