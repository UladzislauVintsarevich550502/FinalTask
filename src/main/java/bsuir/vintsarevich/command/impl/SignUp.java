package bsuir.vintsarevich.command.impl;


import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enums.JspPageName;
import bsuir.vintsarevich.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements ICommand {
    private static Logger logger = Logger.getLogger(SignUp.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return jspPageName.getPath();
    }
}
