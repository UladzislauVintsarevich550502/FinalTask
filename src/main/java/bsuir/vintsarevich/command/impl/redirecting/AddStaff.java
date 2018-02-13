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
 * class AddStaff created to add staff
 */
public class AddStaff implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.STAFF;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add staff");
        try {
            IStaffService staffService = serviceFactory.getStaffService();
            String staffLogin = request.getParameter(AttributeParameterName.STAFF_LOGIN.getValue());
            String staffPassword = request.getParameter(AttributeParameterName.STAFF_PASSWORD.getValue());
            if(!staffService.signUp(staffLogin, staffPassword)){
                diagnoseError(request);
            }
            response.sendRedirect(RedirectingCommandName.STAFF_LIST.getCommand());
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish add staff");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.ADD_STAFF_ERROR.getValue(), "Пользователь с таким логиом уже существует");
        } else {
            request.getSession().setAttribute(AttributeParameterName.ADD_STAFF_ERROR.getValue(), "User with this nickname already exist");
        }
    }
}