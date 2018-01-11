package com.epam.command.impl;

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

public class AddUser implements com.epam.command.ICommand {
    private static final String CLIENT_ROLE = "1";
    private static final String PHARMACIST_ROLE = "2";
    private static final String DOCTOR_ROLE = "3";
    private static final String ADMIN_ROLE = "4";
    private static Logger logger = Logger.getLogger(AddUser.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INFORMATION;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String idRole = session.getAttribute(RequestEnum.USER_ROLE.getValue()).toString();
            if (idRole.equals(Constants.ADMIN)) {
                String login = request.getParameter(RequestEnum.LOGIN.getValue());
                String password = request.getParameter(RequestEnum.PASSWORD.getValue());

                if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
                    request.setAttribute("errorData", "введите логин или пароль");
                    jspPageName = JspPageName.INFORMATION;
                    return jspPageName.getPath();
                }

                IUserService iUserService = serviceFactory.getUserServiceImpl();
                String name = request.getParameter(RequestEnum.NAME.getValue());
                String surname = request.getParameter(RequestEnum.SURNAME.getValue());
                String address = request.getParameter(RequestEnum.ADDRESS.getValue());
                String email = request.getParameter(RequestEnum.EMAIL.getValue());
                String role = "";
                if (request.getParameter(RequestEnum.USER_ROLE.getValue()).equals(PHARMACIST_ROLE))
                    role = PHARMACIST_ROLE;
                else if (request.getParameter(RequestEnum.USER_ROLE.getValue()).equals(DOCTOR_ROLE))
                    role = DOCTOR_ROLE;
                if (iUserService.signUp(role, login, password, name, surname, address, email)) {
                    request.setAttribute(RequestEnum.INFORMATION.getValue(), "User " + login + " is added");
                } else {
                    request.setAttribute(RequestEnum.INFORMATION.getValue(), "Can't add user " + login);
                }
            } else {
                jspPageName = JspPageName.INFORMATION;
                request.setAttribute(RequestEnum.INFORMATION.getValue(), "Нет прав");
            }
        } catch (ServiceException e) {
            logger.info(e.getMessage());
            request.setAttribute(RequestEnum.INFORMATION.getValue(), e.getCause().getMessage());
        } catch (ServiceLogicException e) {
            logger.info(e.getMessage());
        }
        return jspPageName.getPath();
    }
}
