package bsuir.vintsarevich.command.impl;


import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.dto.admin.service.IAdminService;
import bsuir.vintsarevich.dto.client.service.IClientService;
import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.enums.JspElementEnum;
import bsuir.vintsarevich.enums.JspPageName;
import bsuir.vintsarevich.enums.RequestEnum;
import bsuir.vintsarevich.service.factory.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements ICommand {
    private static Logger logger = Logger.getLogger(SignIn.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.log(Level.DEBUG, request.getHeader("client-Agent") + " try to sign in account");
        String login = request.getParameter(JspElementEnum.AUTH_LOGIN.toString().toLowerCase());
        String password = request.getParameter(JspElementEnum.AUTH_PASSWORD.toString().toLowerCase());

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorData", "введите логин или пароль");
            jspPageName = JspPageName.INDEX;
            return jspPageName.getPath();
        }
        try {
            IClientService clientService = serviceFactory.getClientService();
            Client client = clientService.signIn(login, password);
            IAdminService adminService = serviceFactory.getAdminService();
            Admin admin = adminService.signIn(login,password);
            HttpSession session = request.getSession();
            logger.info(client);
            if (client != null) {
                session.setAttribute(JspElementEnum.AUTH_ID.toString().toLowerCase(), client.getId());
                session.setAttribute(JspElementEnum.AUTH_LOGIN.toString().toLowerCase(), client.getLogin());
                session.setAttribute(JspElementEnum.AUTH_PASSWORD.toString().toLowerCase(), "client");
                logger.info("Successfull sign in account as client:" + login);
                request.getRequestDispatcher("/index.do").forward(request, response);
                jspPageName = JspPageName.CLIENT;
            } else {
                if (admin != null) {
                    session.setAttribute(JspElementEnum.AUTH_ID.toString().toLowerCase(), 0);
                    session.setAttribute(JspElementEnum.AUTH_LOGIN.toString().toLowerCase(), admin.getLogin());
                    session.setAttribute(JspElementEnum.AUTH_PASSWORD.toString().toLowerCase(), "admin");
                    logger.info("Successfull sign in account as admin:" + login);
                    request.getRequestDispatcher("/index.do").forward(request, response);
                    jspPageName = JspPageName.ADMIN;
                } else {
                    logger.info(request.getHeader("client-Agent") + " unsuccessfully sign in account.");
                    request.setAttribute(RequestEnum.INFORMATION.getValue(), "such client is not exist");
                }
            }
        } catch (ServletException | IOException e) {
            logger.info(e.getMessage());
            request.setAttribute(RequestEnum.INFORMATION.getValue(), e.getCause().getMessage());
        }
        return jspPageName.getPath();
    }
}

