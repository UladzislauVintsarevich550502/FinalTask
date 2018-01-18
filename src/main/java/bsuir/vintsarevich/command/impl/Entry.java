package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.JspPageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Entry implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private JspPageName jspPageName = JspPageName.ENTRY;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Entry");
        return jspPageName.getPath();
    }
}
