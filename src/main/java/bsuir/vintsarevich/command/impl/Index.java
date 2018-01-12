package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.product.service.IProducteService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Index implements ICommand {

    private static Logger logger = Logger.getLogger(Index.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            IProducteService producteService = serviceFactory.getProducteService();
            List<Product> products = producteService.getAllProducts();
            request.setAttribute("medicaments", products);
        } catch (ServiceException e) {
            logger.error(this.getClass() + e.getMessage());
        }
        return jspPageName.getPath();
    }
}
