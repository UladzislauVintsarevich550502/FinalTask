package bsuir.vintsarevich.command.impl.dispatching;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.enumeration.AttributeName;
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

public class Index implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Index command start");
        try {
            setPageProduct(request);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.INDEX.getCommand());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
            rewrite(request);
            Common.setReview(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Index command finish");
        return jspPageName.getPath();
    }

    private void rewrite(HttpServletRequest request) {
        request.setAttribute(AttributeName.SIGN_ERROR.getValue(), request.getSession().getAttribute(AttributeName.SIGN_ERROR.getValue()));
        request.getSession().removeAttribute(AttributeName.SIGN_ERROR.getValue());
        request.setAttribute(AttributeName.ADD_REVIEW_ERROR.getValue(), request.getSession().getAttribute(AttributeName.ADD_REVIEW_ERROR.getValue()));
        request.getSession().removeAttribute(AttributeName.ADD_REVIEW_ERROR.getValue());
        request.setAttribute(AttributeName.ADD_PRODUCT_TO_BASKET_ERROR.getValue(), request.getSession().getAttribute(AttributeName.ADD_PRODUCT_TO_BASKET_ERROR.getValue()));
        request.getSession().removeAttribute(AttributeName.ADD_PRODUCT_TO_BASKET_ERROR.getValue());
        request.setAttribute(AttributeName.ADD_PRODUCT_ERROR.getValue(), request.getSession().getAttribute(AttributeName.ADD_PRODUCT_ERROR.getValue()));
        request.getSession().removeAttribute(AttributeName.ADD_PRODUCT_ERROR.getValue());
    }

    private void setPageProduct(HttpServletRequest request) throws ServiceException {
        IProductService productService = serviceFactory.getProducteService();
        List<Product> allProducts = productService.getAllProducts();
        if (allProducts.size() == 0) {
            diagnoseError(request);
            request.getSession().setAttribute("pageCount", 0);
        } else {
            Common.calculatePageNumber(request, allProducts);
        }
    }

    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.setAttribute(AttributeName.FIND_BY_TYPE_ERROR.getValue(), "Ничего не найдено");
        } else {
            request.setAttribute(AttributeName.FIND_BY_TYPE_ERROR.getValue(), "Nothing found");
        }
    }
}
