package com.epam.command.impl;

import com.epam.entity.User;
import com.epam.service.IUserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceLogicException;
import com.epam.service.factory.ServiceFactory;
import com.epam.service.utils.Constants;
import com.epam.servlet.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetUsers implements com.epam.command.ICommand {
    private static Logger logger = Logger.getLogger(GetUsers.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.USERS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String idRole = session.getAttribute(RequestEnum.USER_ROLE.getValue()).toString();
            String usersRole = request.getParameter(RequestEnum.USER_ROLE.getValue());
            if ((idRole.equals(Constants.DOCTOR) && usersRole.equals(Constants.USER))
                    || idRole.equals(Constants.ADMIN)) {

                IUserService iUserService = serviceFactory.getUserServiceImpl();
                List<User> users = iUserService.getAllUsersByRoleId(usersRole);
                request.setAttribute("users", users);
            } else {
                jspPageName = JspPageName.INFORMATION;
                request.setAttribute(RequestEnum.INFORMATION.getValue(), "Нет прав");
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        } catch (ServiceLogicException e) {
            logger.error(e.getMessage());
        }
        return jspPageName.getPath();

    }
}
