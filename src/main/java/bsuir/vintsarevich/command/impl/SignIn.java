package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.account.service.IAccountService;
import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.staff.dao.IStaffDao;
import bsuir.vintsarevich.buisness.staff.service.IStaffService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.entity.Staff;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
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
        user = null;
        String login = request.getParameter(JspElemetName.SIGNIN_LOGIN.getValue());
        String password = request.getParameter(JspElemetName.SIGNIN_PASSWORD.getValue());

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorData", "введите логин или пароль");
            jspPageName = JspPageName.ERROR;
            return jspPageName.getPath();
        }

        IClientService clientService = serviceFactory.getClientService();
        Client client = clientService.signIn(login, password);

        IAdminService adminService = serviceFactory.getAdminService();
        Admin admin = adminService.signIn(login, password);

        IStaffService staffService = serviceFactory.getStaffService();
        Staff staff = staffService.signIn(login, password);

        IAccountService accountService = serviceFactory.getAccountService();
        LOGGER.log(Level.INFO, client);
        try {
            if (client != null) {
                if (!client.getStatus().equals("banned")) {
                    user = new User(client.getId(), client.getLogin(), "client", client.getName(), client.getSurname(), client.getStatus(), accountService.findAccountByClientId(client.getId()), client.getPoint());
                    HttpSession session = request.getSession();
                    session.setAttribute(JspElemetName.USER.getValue(), user);
                    LOGGER.log(Level.INFO, "Successfull sign in account as " + login);
                    response.sendRedirect("/cafe.by/index");
                } else {
                    jspPageName = JspPageName.TEST;
                }
            } else {
                if (admin != null) {
                    user = new User(admin.getId(), admin.getLogin(), "admin", null, null, null, true);
                    HttpSession session = request.getSession();
                    session.setAttribute(JspElemetName.USER.getValue(), user);
                    LOGGER.log(Level.INFO, "Successfull sign in account as " + login);
                    response.sendRedirect("/cafe.by/index");
                } else {
                    if (staff != null) {
                        user = new User(staff.getId(), staff.getLogin(), "staff", null, null, null, true);
                        request.getSession().setAttribute(JspElemetName.USER.getValue(), user);
                        LOGGER.log(Level.INFO, "Successfull sign in account as " + login);
                        response.sendRedirect("/cafe.by/order_show");
                    } else {
                        LOGGER.log(Level.INFO, "Unsuccessfully sign in account.");
                        request.setAttribute(jspElemetName.getValue(), "Such user is not exist");
                    }
                }
            }
        } catch (IOException | ServiceException e) {
            e.printStackTrace();
        }

        LOGGER.log(Level.INFO, "Sign in command finish");
        return jspPageName.getPath();
    }
}