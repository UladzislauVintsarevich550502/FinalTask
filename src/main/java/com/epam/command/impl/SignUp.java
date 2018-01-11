package com.epam.command.impl;

import com.epam.command.ICommand;
import com.epam.service.IUserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;
import com.epam.servlet.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements ICommand {
    private static final String CLIENT_ROLE = "1";
    private static Logger logger = Logger.getLogger(SignUp.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            IUserService iUserService = serviceFactory.getUserServiceImpl();
            String login = request.getParameter(RequestEnum.LOGIN.getValue());
            String password = request.getParameter(RequestEnum.PASSWORD.getValue());
            String name = request.getParameter(RequestEnum.NAME.getValue());
            String surname = request.getParameter(RequestEnum.SURNAME.getValue());
            String address = request.getParameter(RequestEnum.ADDRESS.getValue());
            String email = request.getParameter(RequestEnum.EMAIL.getValue());
            iUserService.signUp(CLIENT_ROLE, login, password, name, surname, address, email);
            request.getRequestDispatcher("/index.do").forward(request, response);
        } catch (ServiceException e) {
            logger.error(((String) request.getSession().getAttribute(RequestEnum.SIGN_UP.getValue())), e);
            request.setAttribute(RequestEnum.INFORMATION.getValue(), e.getCause().getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return jspPageName.getPath();
    }
}
