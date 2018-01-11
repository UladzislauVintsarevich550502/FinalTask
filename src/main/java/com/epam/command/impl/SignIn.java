package com.epam.command.impl;

import com.epam.command.ICommand;
import com.epam.entity.User;
import com.epam.service.IUserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;
import com.epam.servlet.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements ICommand {
    private static Logger logger = Logger.getLogger(SignIn.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug(request.getHeader("User-Agent") + " try to sign in account");
        String login = request.getParameter(RequestEnum.LOGLOGIN.getValue());
        String password = request.getParameter(RequestEnum.LOGPASSWORD.getValue());

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorData", "введите логин или пароль");
            jspPageName = JspPageName.INFORMATION;
            return jspPageName.getPath();
        }
        try {
            IUserService iUserService = serviceFactory.getUserServiceImpl();
            User user = iUserService.signIn(login, password);
            logger.info(user);
            if (user.getIdRole() > 0 && user.getIdRole() < 5) {
                //Cookie cookieLogin = new Cookie(RequestEnum.LOGIN.getValue(), user.getLogin());
                //response.addCookie(cookieLogin);
                HttpSession session = request.getSession();
                session.setAttribute(RequestEnum.USER_ID.getValue(), user.getId());
                session.setAttribute(RequestEnum.USER_LOGIN.getValue(), user.getLogin());
                session.setAttribute(RequestEnum.USER_ROLE.getValue(), user.getIdRole());
                logger.info("Successfull sign in account as " + login);
                request.getRequestDispatcher("/index.do").forward(request, response);
            } else {
                logger.info(request.getHeader("User-Agent") + " unsuccessfully sign in account.");
                request.setAttribute(RequestEnum.INFORMATION.getValue(), "such user is not exist");
            }
        } catch (ServiceException e) {
            logger.info(e.getMessage());
            request.setAttribute(RequestEnum.INFORMATION.getValue(), e.getCause().getMessage());
        } catch (ServletException | IOException e) {
            logger.info(e.getMessage());
            request.setAttribute(RequestEnum.INFORMATION.getValue(), e.getCause().getMessage());
        }
        return jspPageName.getPath();
    }
}

