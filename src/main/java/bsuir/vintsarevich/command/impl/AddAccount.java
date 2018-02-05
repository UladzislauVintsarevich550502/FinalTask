package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.account.service.IAccountService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public class AddAccount implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.INDEX;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add account");
        try {
            IAccountService accountService = serviceFactory.getAccountService();
            User user = ((User) request.getSession().getAttribute("user"));
            accountService.addAccount(user.getId());
            user.setAccount(true);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/cafe.by/index");
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Finish add product");
        return jspPageName.getPath();
    }
}
