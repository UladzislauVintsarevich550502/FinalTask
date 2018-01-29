package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Admin;
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
import java.io.IOException;

public class SignIn implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignIn.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.TEST;
    private JspElemetName jspElemetName = JspElemetName.INFORMATION;
    private User user;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Sign in command start");
        String login = request.getParameter(JspElemetName.SIGNIN_LOGIN.getValue());
        String password = request.getParameter(JspElemetName.SIGNIN_PASSWORD.getValue());
        System.out.println(login);
        System.out.println(password);

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorData", "введите логин или пароль");
            jspPageName = JspPageName.INFORMATION;
            return jspPageName.getPath();
        }

        IClientService clientService = serviceFactory.getClientService();
        Client client = clientService.signIn(login, password);
        IAdminService adminService = serviceFactory.getAdminService();
        Admin admin = adminService.signIn(login, password);
        LOGGER.log(Level.INFO, client);
        try {
            if (client != null) {
                user = new User(client.getId(), client.getLogin(), "client", client.getName(), client.getSurname());
                HttpSession session = request.getSession();
                session.setAttribute(JspElemetName.USER.getValue(), user);
                LOGGER.log(Level.INFO, "Successfull sign in account as " + login);
                response.sendRedirect("/index.do");
            } else {
                if (admin != null) {
                    user = new User(admin.getId(), admin.getLogin(), "admin", null, null);
                    HttpSession session = request.getSession();
                    session.setAttribute(JspElemetName.USER.getValue(), user);
                    LOGGER.log(Level.INFO, "Successfull sign in account as " + login);
                    response.sendRedirect("/index.do");
                }
                LOGGER.log(Level.INFO, "Unsuccessfully sign in account.");
                request.setAttribute(jspElemetName.getValue(), "Such user is not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.log(Level.INFO, "Sign in command finish");
        return jspPageName.getPath();
    }
}

