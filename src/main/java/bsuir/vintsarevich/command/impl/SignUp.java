package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RequestName;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements ICommand {
    private static final String CLIENT_ROLE = "1";
    private static Logger logger = Logger.getLogger(SignUp.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            IClientService clientService = serviceFactory.getClientService();
            String login = request.getParameter(RequestName.LOGIN.getValue());
            String password = request.getParameter(RequestName.PASSWORD.getValue());
            String name = request.getParameter(RequestName.NAME.getValue());
            String surname = request.getParameter(RequestName.SURNAME.getValue());
            String address = request.getParameter(RequestName.ADDRESS.getValue());
            String email = request.getParameter(RequestName.EMAIL.getValue());
            request.getRequestDispatcher("/index.do").forward(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return jspPageName.getPath();
    }
}
