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
import java.util.Locale;

public class SearchProduct implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Search command start");
        try {
            IProductService producteService = serviceFactory.getProducteService();
            String name = request.getParameter(JspElemetName.SEARCH_NAME.getValue());
            List<Product> products = producteService.getAllProducts();
            List<Product> allProducts = new ArrayList<>();
            for (Product product : products) {
                if (product.getNameEn().toLowerCase().contains(name.toLowerCase())
                        || product.getNameRu().toLowerCase().contains(name.toLowerCase())) {
                    allProducts.add(product);
                }
            }
            request.setAttribute("products", allProducts);
            request.getSession().setAttribute("pageCount", 0);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Search command finish");
        return jspPageName.getPath();
    }
}
