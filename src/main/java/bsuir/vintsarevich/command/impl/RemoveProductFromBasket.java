package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.order.dao.IOrderDao;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.orderproduct.dao.IOrderProductDao;
import bsuir.vintsarevich.buisness.orderproduct.service.IOrderProductService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveProductFromBasket implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.BASKET;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start remove product from basket");
        try {
            IOrderProductService orderProductService = serviceFactory.getOrderProductService();
            IOrderService orderService = serviceFactory.getOrderService();
            IProductService productService = serviceFactory.getProducteService();
            IOrderProductDao orderProductDao = DaoFactory.getInstance().getOrderProductDao();
            IOrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            Integer clentId = ((User) request.getSession().getAttribute("user")).getId();
            System.out.println(clentId);
            Integer productId = Integer.valueOf(request.getParameter(JspElemetName.PRODUCT_ID.getValue()));
            System.out.println(productId);
            Double orderCost = productService.getProductById(productId).getCost();
            System.out.println(orderCost);
            Integer clientId = ((User) request.getSession().getAttribute("user")).getId();
            Integer productCount = Integer.valueOf(request.getParameter(JspElemetName.NUMBER_FOR_DELETE.getValue()));
            System.out.println(productCount);
            Integer productCountNow = orderProductDao.orderProductCount(productId);
            Integer orderId = orderDao.getOrderIdByClientId(clientId);
            if (productCount >= productCountNow) {
                orderService.editOrder(clentId, orderCost, -productCountNow);
                orderProductService.deleteOrderProduct(clentId, productId);
            } else {
                orderProductDao.editOrderProduct(productId, -productCount, orderId);
                orderService.editOrder(clentId, orderCost, -productCount);
            }
            response.sendRedirect("/cafe.by/basket");
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "Finish remove product from basket");
        return jspPageName.getPath();
    }
}

