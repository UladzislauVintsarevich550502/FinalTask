package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
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

public class AddProduct implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.TEST;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add product");
        try {
            IProductService productService = serviceFactory.getProducteService();
            String productType = request.getParameter(JspElemetName.PRODUCT_TYPE.getValue());
            String nameRu = request.getParameter(JspElemetName.NAME_RU.getValue());
            String nameEn = request.getParameter(JspElemetName.NAME_EN.getValue());
            Integer weight = new Integer(request.getParameter(JspElemetName.VALUE.getValue()));
            Double cost = new Double(request.getParameter(JspElemetName.COST.getValue()));
            String status = request.getParameter(JspElemetName.STATUS.getValue());
            String descriptionRu = request.getParameter(JspElemetName.DESCRIPTION_RU.getValue());
            String descriptionEn = request.getParameter(JspElemetName.DESCRIPTION_EN.getValue());
            Part part = request.getPart(JspElemetName.IMAGE.getValue());
            String webPath = request.getServletContext().getRealPath("/");
            productService.addProduct(productType, nameRu, nameEn, weight, cost, status, descriptionRu, descriptionEn, part, webPath);
            response.sendRedirect("/index.do");
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        } catch (ServletException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        } catch (ServiceLogicException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Finish add product");
        return jspPageName.getPath();
    }
}
