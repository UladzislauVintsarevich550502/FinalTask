package bsuir.vintsarevich.command.impl.redirecting;

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
 * class ChangeStaff created to change staff
 */
public class ChangeStaff implements ICommand {
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
        IStaffService staffService = serviceFactory.getStaffService();
        String password = request.getParameter(AttributeParameterName.NEW_PASSWORD.getValue());
        Integer staffId = new Integer(request.getParameter(AttributeParameterName.STAFF_ID.getValue()));
        LOGGER.log(Level.INFO, "Change staff start");
        try {
            staffService.changePassword(password, staffId);
            response.sendRedirect(RedirectingCommandName.STAFF_LIST.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Change staff finish");
        return jspPageName.getPath();
    }
}
