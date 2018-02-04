package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
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
            Integer currentPage = Integer.valueOf(request.getParameter(JspElemetName.CURRENT_PAGE.getValue()));
            request.getSession().setAttribute("currentPage", currentPage);
            response.sendRedirect("/index.do");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, this.getClass() + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Set current page command finish");
        return jspPageName.getPath();
    }
}
