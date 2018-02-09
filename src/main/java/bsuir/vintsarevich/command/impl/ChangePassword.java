package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.staff.service.IStaffService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePassword implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.INDEX;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Change password start");
        IAdminService adminService = serviceFactory.getAdminService();
        IClientService clientService = serviceFactory.getClientService();
        IStaffService staffService = serviceFactory.getStaffService();

        User user = ((User) request.getSession().getAttribute("user"));

        String oldPassword = request.getParameter(JspElemetName.OLD_PASSWORD.getValue());
        String newPassword = request.getParameter(JspElemetName.NEW_PASSWORD.getValue());
        String newPasswordRepeat = request.getParameter(JspElemetName.NEW_PASSWORD_REPEAT.getValue());

        try {
            if (user.getRole().equals("admin")) {
                if (adminService.checkPassword(oldPassword, user.getId()) && newPassword.equals(newPasswordRepeat)) {
                    adminService.changePassword(newPassword, user.getId());
                } else {
                    jspPageName = JspPageName.ERROR;
                }
            }
            if (user.getRole().equals("client")) {
                if (clientService.checkPassword(oldPassword, user.getId()) && newPassword.equals(newPasswordRepeat)) {
                    clientService.changePassword(newPassword, user.getId());
                } else {
                    jspPageName = JspPageName.ERROR;
                }
            }
            if (user.getRole().equals("staff")) {
                if (staffService.checkPassword(oldPassword, user.getId()) && newPassword.equals(newPasswordRepeat)) {
                    staffService.changePassword(newPassword, user.getId());
                } else {
                    jspPageName = JspPageName.ERROR;
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Change password finish");
        return jspPageName.getPath();
    }
}
