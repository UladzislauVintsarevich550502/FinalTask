package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.staff.service.IStaffService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStaff implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.INDEX;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add staff");
        try {
            IStaffService staffService = serviceFactory.getStaffService();
            String staffLogin = request.getParameter(JspElemetName.STAFF_LOGIN.getValue());
            String staffPassword = request.getParameter(JspElemetName.STAFF_PASSWORD.getValue());
            staffService.signUp(staffLogin, staffPassword);
            response.sendRedirect("/cafe.by/index");
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Finish add staff");
        return jspPageName.getPath();
    }
}
