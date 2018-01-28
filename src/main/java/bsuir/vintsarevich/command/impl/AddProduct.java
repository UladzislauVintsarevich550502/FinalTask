package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Product;
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
    private Product product;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add product");
        try {
            IProductService productService = serviceFactory.getProducteService();
            String productType = request.getParameter(JspElemetName.PRODUCT_TYPE.getValue());
            System.out.println(productType);
            String name = request.getParameter(JspElemetName.NAME.getValue());
            System.out.println(name);
            Integer weight = new Integer(request.getParameter(JspElemetName.VALUE.getValue()));
            System.out.println(weight);
            Double cost = new Double(request.getParameter(JspElemetName.COST.getValue()));
            System.out.println(cost);
            String status = request.getParameter(JspElemetName.STATUS.getValue());
            System.out.println(status);
            String description = request.getParameter(JspElemetName.DESCRIPTION.getValue());
            System.out.println(description);
            Part part = request.getPart(JspElemetName.IMAGE.getValue());
            String webPath = request.getServletContext().getRealPath("/");
            System.out.println(webPath);
            productService.addProduct(productType, name, weight, cost, status, description, part, webPath);
            response.sendRedirect("/index.do");
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        } catch (ServletException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        } catch (ServiceLogicException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.INFO, "Finish add product");
        return jspPageName.getPath();
    }
}
