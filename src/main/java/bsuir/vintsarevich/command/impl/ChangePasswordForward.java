package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangePasswordForward implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.CHANGE_PASSWORD;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Change password forward start");
        LOGGER.log(Level.INFO, "Change password forward finish");
        return jspPageName.getPath();
    }
}
