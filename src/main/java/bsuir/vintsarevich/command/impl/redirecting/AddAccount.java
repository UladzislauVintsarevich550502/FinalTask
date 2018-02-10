package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.account.service.IAccountService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.AttributeName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAccount implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.INDEX;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add account");
        try {
            IAccountService accountService = serviceFactory.getAccountService();
            User user = ((User) request.getSession().getAttribute(AttributeName.USER.getValue()));
            if(!accountService.addAccount(user.getId())) {
                diagnoseError(request);
            }else {
                user.setAccount(true);
                request.getSession().setAttribute(AttributeName.USER.getValue(), user);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish add account");
        return jspPageName.getPath();
    }

    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.setAttribute(AttributeName.ADD_ACCOUNT_ERROR.getValue(), "Ошибка! Аккаунт не добавлен");
        } else {
            request.setAttribute(AttributeName.ADD_ACCOUNT_ERROR.getValue(), "Error! Account wasn't added");
        }
    }
}
