package bsuir.vintsarevich.command.impl.dispatching;

import bsuir.vintsarevich.buisness.staff.service.IStaffService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Staff;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StaffList implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(StaffList.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.STAFF;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Start staff command");
        try {
            IStaffService staffService = serviceFactory.getStaffService();
            List<Staff> allStaff = staffService.getAllStaff();
            request.setAttribute("allStaff", allStaff);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.STAFF_LIST.getCommand());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command: Finish staff command");
        return jspPageName.getPath();
    }
}
