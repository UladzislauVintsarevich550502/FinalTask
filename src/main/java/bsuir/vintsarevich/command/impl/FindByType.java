package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class FindByType implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Find command start");
        try {
            IProductService producteService = serviceFactory.getProducteService();
            String type = request.getParameter(JspElemetName.PRODUCT_TYPE.getValue());
            LOGGER.log(Level.INFO, "Type is:" + type);
            List<Product> products = producteService.getProductByType(type);
            request.setAttribute("products", products);
            request.getSession().setAttribute("pageCount", 0);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Find command finish");
        return jspPageName.getPath();
    }
}
