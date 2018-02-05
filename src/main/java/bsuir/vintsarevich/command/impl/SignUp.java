package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUp implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignUp.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.ERROR;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Start Sign Up");
        try {
            IClientService clientService = serviceFactory.getClientService();
            IOrderService orderService = serviceFactory.getOrderService();
            String login = request.getParameter(JspElemetName.SIGNUP_LOGIN.getValue());
            String password = request.getParameter(JspElemetName.SIGNUP_PASSWORD.getValue());
            String name = request.getParameter(JspElemetName.SIGNUP_NAME.getValue());
            String surname = request.getParameter(JspElemetName.SIGNUP_SURNAME.getValue());
            String email = request.getParameter(JspElemetName.SIGNUP_EMAIL.getValue());
            Client client = clientService.signUp(name, surname, login, password, email);
            User user = new User(client.getId(), client.getLogin(), "client", client.getName(), client.getSurname(), client.getStatus(), false);
            orderService.addOrder("Not order", 0.0, client.getId());
            HttpSession session = request.getSession();
            session.setAttribute(JspElemetName.USER.getValue(), user);
            LOGGER.log(Level.INFO, "Successfull sign in account as " + login);
            response.sendRedirect("/cafe.by/index");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.log(Level.INFO, "Command: Finish Sign Up");
        return jspPageName.getPath();
    }
}
