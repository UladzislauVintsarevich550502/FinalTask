package bsuir.vintsarevich.command.impl.dispatching;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.buisness.review.service.IReviewService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.Review;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Index implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(Index.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;
    private static final int NUMBER_OF_PRODUCT_ON_PAGE = 6;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Index command start");
        try {
            setPageProduct(request);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.INDEX.getCommand());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
            setReview(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Index command finish");
        return jspPageName.getPath();
    }

    private void setPageProduct(HttpServletRequest request) throws ServiceException {
        IProductService productService = serviceFactory.getProducteService();
        List<Product> allProducts = productService.getAllProducts();
        int pageCount;
        if (allProducts.size() % NUMBER_OF_PRODUCT_ON_PAGE == 0) {
            pageCount = allProducts.size() / NUMBER_OF_PRODUCT_ON_PAGE;
        } else {
            pageCount = allProducts.size() / NUMBER_OF_PRODUCT_ON_PAGE + 1;
        }
        LOGGER.log(Level.INFO, "pageCount:" + pageCount);
        if (request.getSession().getAttribute("currentPage") == null ||
                (Integer) request.getSession().getAttribute("currentPage") == 0) {
            request.getSession().setAttribute("currentPage", 1);
        }
        int currentPage = (Integer) request.getSession().getAttribute("currentPage");
        List<Product> pageProducts = new ArrayList<>();
        for (int i = (currentPage - 1) * NUMBER_OF_PRODUCT_ON_PAGE; i < currentPage * NUMBER_OF_PRODUCT_ON_PAGE
                && i < allProducts.size(); i++) {
            pageProducts.add(allProducts.get(i));
        }
        request.setAttribute("products", pageProducts);
        request.getSession().setAttribute("pageCount", pageCount);
    }

    private void setReview(HttpServletRequest request) throws ServiceException {
        IReviewService reviewService = serviceFactory.getReviewService();
        IClientService clientService = serviceFactory.getClientService();
        List<Review> reviews = reviewService.getAllReviews();
        for (Review review : reviews) {
            Client client = clientService.getClientById(review.getClientId());
            review.setClientName(client.getName());
            review.setClientSurname(client.getSurname());
        }
        request.setAttribute("reviews", reviews);
    }
}
