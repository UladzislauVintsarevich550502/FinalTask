package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.AttributeName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class EditProduct implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.TEST;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            IProductService productService = serviceFactory.getProducteService();
            Integer productId = new Integer(request.getParameter(AttributeName.PRODUCT_ID.getValue()));
            String productType = request.getParameter(AttributeName.PRODUCT_TYPE.getValue());
            String nameRu = request.getParameter(AttributeName.NAME_RU.getValue());
            String nameEn = request.getParameter(AttributeName.NAME_EN.getValue());
            Integer weight = new Integer(request.getParameter(AttributeName.VALUE.getValue()));
            Double cost = new Double(request.getParameter(AttributeName.COST.getValue()));
            String status = request.getParameter(AttributeName.STATUS.getValue());
            String descriptionRu = request.getParameter(AttributeName.DESCRIPTION_RU.getValue());
            String descriptionEn = request.getParameter(AttributeName.DESCRIPTION_EN.getValue());
            Part part = request.getPart(AttributeName.IMAGE.getValue());
            String webPath = request.getServletContext().getRealPath("/");
            if (!productService.editProduct(productId, productType, nameRu, nameEn, weight, cost, status, descriptionRu, descriptionEn, part, webPath)) {
                diagnoseError(request);
            }
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (ServletException | IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        return jspPageName.getPath();
    }

    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.setAttribute(AttributeName.ADD_PRODUCT_ERROR.getValue(), "Ошибка! Продукт не изменен");
        } else {
            request.setAttribute(AttributeName.ADD_PRODUCT_ERROR.getValue(), "Error! Product wasn't edited");
        }
    }
}
