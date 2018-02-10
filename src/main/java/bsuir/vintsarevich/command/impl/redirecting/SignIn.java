package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.account.service.IAccountService;
import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.staff.service.IStaffService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.entity.Staff;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.AttributeName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.SessionElements;
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
    private AttributeName attributeName = AttributeName.INFORMATION;
    private User user;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Sign in command start");
        user = null;
        String login = request.getParameter(AttributeName.SIGNIN_LOGIN.getValue());
        String password = request.getParameter(AttributeName.SIGNIN_PASSWORD.getValue());

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
                    session.setAttribute(AttributeName.USER.getValue(), user);
                    LOGGER.log(Level.INFO, "Successful sign in account as " + login);
                    response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
                } else {
                    jspPageName = JspPageName.TEST;
                }
            } else {
                if (admin != null) {
                    user = new User(admin.getId(), admin.getLogin(), "admin", admin.getIsMain());
                    HttpSession session = request.getSession();
                    session.setAttribute(AttributeName.USER.getValue(), user);
                    LOGGER.log(Level.INFO, "Successful sign in account as " + login);
                    response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
                } else {
                    if (staff != null) {
                        user = new User(staff.getId(), staff.getLogin(), "staff");
                        request.getSession().setAttribute(AttributeName.USER.getValue(), user);
                        LOGGER.log(Level.INFO, "Successful sign in account as " + login);
                        response.sendRedirect(RedirectingCommandName.ORDER_SHOW.getCommand());
                    } else {
                        diagnoseError(request);
                        response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
                    }
                }
            }
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Sign in command finish");
        return jspPageName.getPath();
    }

    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.setAttribute(AttributeName.FIND_BY_TYPE_ERROR.getValue(), "Пользователя с таким логином не существует");
        } else {
            request.setAttribute(AttributeName.FIND_BY_TYPE_ERROR.getValue(), "User with such login not exists");
        }
    }
}