package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.AttributeName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAdmin implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.ADMIN;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add admin");
        try {
            IAdminService adminService = ServiceFactory.getInstance().getAdminService();
            String staffLogin = request.getParameter(AttributeName.STAFF_LOGIN.getValue());
            String staffPassword = request.getParameter(AttributeName.STAFF_PASSWORD.getValue());
            adminService.signUp(staffLogin, staffPassword);
            response.sendRedirect(RedirectingCommandName.ADMIN_LIST.getCommand());
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish add admin");
        return jspPageName.getPath();
    }
}