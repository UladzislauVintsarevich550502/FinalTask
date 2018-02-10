package bsuir.vintsarevich.command.impl.dispatching;

import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminList implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(StaffList.class);
    private JspPageName jspPageName = JspPageName.ADMIN;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Start admin command");
        try {
            IAdminService adminService = ServiceFactory.getInstance().getAdminService();
            List<Admin> allAdmins = adminService.getAllAdmins();
            request.setAttribute("allAdmins", allAdmins);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.ADMIN_LIST.getCommand());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command: Finish admin command");
        return jspPageName.getPath();
    }
}
