package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocale implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(CloseDBCommand.class);
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.DEBUG, "Change locale is starting");
        String local = request.getParameter(JspElemetName.LOCALE.getValue());
        request.getSession().setAttribute(JspElemetName.MAIN_LOCALE.getValue(), local);

        return jspPageName.getPath();
    }
}
