package bsuir.vintsarevich.command.impl.dispatching;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.Common;
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
    private String productType;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Find command start");
        try {
            String type = request.getParameter(AttributeParameterName.PRODUCT_TYPE.getValue());
            if (type != null) {
                productType = type;
                request.getSession().setAttribute("currentPage", 1);
            }
            setPageProduct(request);
            Common.setReview(request);
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
            request.setAttribute(AttributeParameterName.PRODUCT_NOT_FIND.getValue(), "Ничего не найдено");
        } else {
            request.setAttribute(AttributeParameterName.PRODUCT_NOT_FIND.getValue(), "Nothing found");
        }
    }

    private void setPageProduct(HttpServletRequest request) throws ServiceException {
        IProductService productService = serviceFactory.getProducteService();
        List<Product> allProducts = productService.getProductByType(productType);
        if (allProducts.size() == 0) {
            diagnoseError(request);
            request.getSession().setAttribute("pageCount", 0);
        } else {
          Common.calculatePageNumber(request,allProducts);
        }
    }
}
