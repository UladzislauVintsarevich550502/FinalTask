package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RequestName;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn implements ICommand {
    private static Logger logger = Logger.getLogger(SignIn.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug(request.getHeader("User-Agent") + " try to sign in account");
        String login = request.getParameter(RequestName.LOGLOGIN.getValue());
        String password = request.getParameter(RequestName.LOGPASSWORD.getValue());

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorData", "введите логин или пароль");
            jspPageName = JspPageName.INFORMATION;
            return jspPageName.getPath();
        }
        return jspPageName.getPath();
    }
}

