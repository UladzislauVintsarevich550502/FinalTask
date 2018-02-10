package bsuir.vintsarevich.command.impl.dispatching;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.enumeration.AttributeName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindByType implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Find command start");
        try {
            IProductService productService = serviceFactory.getProducteService();
            String type = request.getParameter(AttributeName.PRODUCT_TYPE.getValue());
            LOGGER.log(Level.INFO, "Type is:" + type);
            List<Product> products = productService.getProductByType(type);
            if (products.size() == 0) {
                diagnoseError(request);
            } else {
                request.setAttribute("products", products);
            }
            request.getSession().setAttribute("pageCount", 0);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.FIND_BY_TYPE.getCommand());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Find command finish");
        return jspPageName.getPath();
    }

    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.setAttribute(AttributeName.FIND_BY_TYPE_ERROR.getValue(), "Ничего не найдено");
        } else {
            request.setAttribute(AttributeName.FIND_BY_TYPE_ERROR.getValue(), "Nothing found");
        }
    }
}
