package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.order.dao.IOrderDao;
import bsuir.vintsarevich.buisness.order.dao.impl.OrderDAO;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.orderproduct.dao.IOrderProductDao;
import bsuir.vintsarevich.buisness.orderproduct.service.IOrderProductService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * class AddBasketProduct created to add basket
 */
public class AddBasketProduct implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.BASKET;
    private Integer productId;
    private Integer productCount;
    private Integer clientId;
    private IOrderProductService orderProductService = serviceFactory.getOrderProductService();
    private IOrderService orderService = serviceFactory.getOrderService();
    private IProductService productService = serviceFactory.getProducteService();
    private IOrderProductDao orderProductDao = DaoFactory.getInstance().getOrderProductDao();

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start add product to basket");
        try {
            clientId = ((User) request.getSession().getAttribute(AttributeParameterName.USER.getValue())).getId();
            List<Product> allProducts = productService.getAllProducts();
            IOrderDao orderDao = new OrderDAO();

            int number = 0;

            for (Product product : allProducts) {
                if (request.getParameter(AttributeParameterName.PRODUCT_ID.getValue() + "_" + product.getId()) != null &&
                        request.getParameter(AttributeParameterName.NUMBER_FOR_ADD.getValue() + "_" + product.getId()) != null) {

                    productId = Integer.valueOf(request.getParameter(AttributeParameterName.PRODUCT_ID.getValue() + "_" + product.getId()));
                    productCount = Integer.valueOf(request.getParameter(AttributeParameterName.NUMBER_FOR_ADD.getValue() + "_" + product.getId()));

                    Double orderCost = productService.getProductById(productId).getCost();
                    Integer orderId = orderDao.getOrderIdByClientId(clientId);
                    if (productCount.equals(0)) {
                        number++;
                    } else {
                        if (orderProductDao.findOrderProduct(productId, orderId)) {
                            orderProductDao.editOrderProduct(productId, productCount, orderId);
                            orderService.editOrder(clientId, orderCost, productCount);
                        } else {
                            orderProductService.addOrderProduct(clientId, productId, productCount);
                            orderService.editOrder(clientId, orderCost, productCount);
                        }
                    }
                } else {
                    number++;
                }
            }
            if (number == allProducts.size()) {
                diagnoseError(request);
            }
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (ServiceException | IOException | DaoException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish add product to basket");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Ничего не выбрано");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "You hadn't choose anything to add");
        }
    }
}