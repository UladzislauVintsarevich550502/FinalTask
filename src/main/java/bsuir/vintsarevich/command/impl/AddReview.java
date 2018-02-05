package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.buisness.review.service.IReviewService;
import bsuir.vintsarevich.command.ICommand;
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

public class AddReview implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add review");
        try {
            IReviewService reviewService = serviceFactory.getReviewService();
            Integer clientId = ((User)request.getSession().getAttribute("user")).getId();
            String text = request.getParameter(JspElemetName.REVIEW_TEXT.getValue());
            Integer mark = Integer.parseInt(request.getParameter(JspElemetName.REVIEW_STARS.getValue()));
            reviewService.addReview(text, mark, clientId);
            response.sendRedirect("/cafe.by/index");
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Finish add review");
        return jspPageName.getPath();
    }
}
