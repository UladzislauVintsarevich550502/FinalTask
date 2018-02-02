package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.orderproduct.dao.IOrderProductDao;
import bsuir.vintsarevich.buisness.orderproduct.service.IOrderProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


public class AddProductToBasket implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.BASKET;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add product to basket");
        try {
            IOrderProductService orderProductService = serviceFactory.getOrderProductService();
            IOrderService orderService = serviceFactory.getOrderService();
            IProductService productService = serviceFactory.getProducteService();
            IOrderProductDao orderProductDao = DaoFactory.getInstance().getOrderProductDao();

            Integer clentId = ((User) request.getSession().getAttribute("user")).getId();
            Integer productId = Integer.valueOf(request.getParameter(JspElemetName.PRODUCT_ID.getValue()));
            Integer productCount = Integer.valueOf(request.getParameter(JspElemetName.NUMBER_FOR_ADD.getValue()));
            Double orderCost = productService.getProductById(productId).getCost();

            if (orderProductDao.findOrderProduct(productId)) {
                orderProductDao.editOrderProduct(productId, productCount);
                orderService.editOrder(clentId, orderCost, productCount);
            } else {
                orderProductService.addOrderProduct(clentId, productId, productCount);
                orderService.editOrder(clentId, orderCost, productCount);
            }
            response.sendRedirect("/index.do");
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Finish add product to basket");
        return jspPageName.getPath();
    }
}
