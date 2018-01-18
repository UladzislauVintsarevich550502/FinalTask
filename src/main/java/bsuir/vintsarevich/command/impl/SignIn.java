package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.entity.Role;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignIn.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.TEST;
    private Role role;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Sign In");
        String login = request.getParameter(JspElemetName.SIGNINLOGIN.getValue());
        String password = request.getParameter(JspElemetName.SIGNINPASSWORD.getValue());

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorData", "введите логин или пароль");
            jspPageName = JspPageName.INFORMATION;
            return jspPageName.getPath();
        }

        try {
            IClientService clientService = serviceFactory.getClientService();
            Client client = clientService.signIn(login, password);
            IAdminService adminService = serviceFactory.getAdminService();
            Admin admin = adminService.signIn(login, password);
            LOGGER.log(Level.INFO, client);
            if (client != null) {
                role = new Role(client.getId(), client.getLogin(), "Client");
            } else {
                if (admin != null) {
                    role = new Role(admi)
                    HttpSession session = request.getSession();
                    session.setAttribute("login", admin.getLogin());
                    session.setAttribute("role", "2");
                    logger.info("Successfull sign in account as " + login);
                    request.getRequestDispatcher("/index.do").forward(request, response);
                }
                logger.info(request.getHeader("User-Agent") + " unsuccessfully sign in account.");
                request.setAttribute(JspElemetName.INFORMATION.getValue(), "such user is not exist");
            }
        } catch (ServletException | IOException e) {
            logger.info(e.getMessage());
            request.setAttribute(JspElemetName.INFORMATION.getValue(), e.getCause().getMessage());
        }

        return jspPageName.getPath();
    }
}

