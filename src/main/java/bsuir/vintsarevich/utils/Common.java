package bsuir.vintsarevich.utils;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.review.service.IReviewService;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.Review;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Common {
    private static final int NUMBER_OF_PRODUCT_ON_PAGE = 6;

    public static void setReview(HttpServletRequest request) throws ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IReviewService reviewService = serviceFactory.getReviewService();
        IClientService clientService = serviceFactory.getClientService();
        List<Review> reviews = reviewService.getAllReviews();
        Collections.reverse(reviews);
        for (Review review : reviews) {
            Client client = clientService.getClientById(review.getClientId());
            review.setClientName(client.getName());
            review.setClientSurname(client.getSurname());
        }
        request.setAttribute("reviews", reviews);
    }
    public static void calculatePageNumber(HttpServletRequest request, List<Product> allProducts) throws ServiceException {
        int pageCount;
        if (allProducts.size() % NUMBER_OF_PRODUCT_ON_PAGE == 0) {
            pageCount = allProducts.size() / NUMBER_OF_PRODUCT_ON_PAGE;
        } else {
            pageCount = allProducts.size() / NUMBER_OF_PRODUCT_ON_PAGE + 1;
        }
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
}