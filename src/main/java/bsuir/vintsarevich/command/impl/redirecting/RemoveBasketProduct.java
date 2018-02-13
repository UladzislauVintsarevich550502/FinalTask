package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.order.dao.IOrderDao;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.orderproduct.dao.IOrderProductDao;
import bsuir.vintsarevich.buisness.orderproduct.service.IOrderProductService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class RemoveBasketProduct created to remove products  from basket
 */
public class RemoveBasketProduct implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.BASKET;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start remove product from basket");
        try {
            IOrderProductService orderProductService = serviceFactory.getOrderProductService();
            IOrderService orderService = serviceFactory.getOrderService();
            IProductService productService = serviceFactory.getProducteService();
            IOrderProductDao orderProductDao = DaoFactory.getInstance().getOrderProductDao();
            IOrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            Integer clientId = ((User) request.getSession().getAttribute("user")).getId();
            Integer productId = Integer.valueOf(request.getParameter(AttributeParameterName.PRODUCT_ID.getValue()));
            Double orderCost = productService.getProductById(productId).getCost();
            Integer productCount = Integer.valueOf(request.getParameter(AttributeParameterName.NUMBER_FOR_DELETE.getValue()));
            Integer productCountNow = orderProductDao.orderProductCount(productId);
            Integer orderId = orderDao.getOrderIdByClientId(clientId);
            if (productCount >= productCountNow) {
                orderService.editOrder(clientId, orderCost, -productCountNow);
                orderProductService.deleteOrderProduct(clientId, productId);
            } else {
                orderProductDao.editOrderProduct(productId, -productCount, orderId);
                orderService.editOrder(clientId, orderCost, -productCount);
            }
            response.sendRedirect(RedirectingCommandName.BASKET.getCommand());
        } catch (ServiceException | IOException | DaoException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish remove product from basket");
        return jspPageName.getPath();
    }
}

