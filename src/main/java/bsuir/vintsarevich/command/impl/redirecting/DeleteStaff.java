package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.staff.service.IStaffService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class DeleteStaff created to delete staff
 */
public class DeleteStaff implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(DeleteStaff.class);
    private JspPageName jspPageName = JspPageName.STAFF;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start delete staff");
        try {
            IStaffService staffService = ServiceFactory.getInstance().getStaffService();
            Integer staffId = Integer.valueOf(request.getParameter(AttributeParameterName.STAFF_ID.getValue()));
            staffService.deleteStaff(staffId);
            response.sendRedirect(RedirectingCommandName.STAFF_LIST.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish delete staff");
        return jspPageName.getPath();
    }
}
