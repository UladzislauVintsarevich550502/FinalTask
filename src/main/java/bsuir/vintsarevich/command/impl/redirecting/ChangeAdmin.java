package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.staff.service.IStaffService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class ChangeAdmin created to change administrators
 */

public class ChangeAdmin implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.INDEX;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IAdminService adminService = serviceFactory.getAdminService();
        String password = request.getParameter(AttributeParameterName.NEW_PASSWORD.getValue());
        Integer adminId = new Integer(request.getParameter(AttributeParameterName.ADMIN_ID.getValue()));
        LOGGER.log(Level.INFO, "Change admin start");
        try {
            adminService.changePassword(password, adminId);
            response.sendRedirect(RedirectingCommandName.ADMIN_LIST.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Change admin finish");
        return jspPageName.getPath();
    }
}
