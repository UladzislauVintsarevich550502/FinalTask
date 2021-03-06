package bsuir.vintsarevich.command.impl.forwarding;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.command.impl.redirecting.SignOut;
import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
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

/**
 * class Basket created to get and display data about baskets
 */
public class Basket implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.BASKET;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Index command start");
        request.getSession().setAttribute("pageCommand", RedirectingCommandName.BASKET.getCommand());
        try {
            Integer clientId = ((User) request.getSession().getAttribute(AttributeParameterName.USER.getValue())).getId();
            IProductService productService = serviceFactory.getProducteService();
            IOrderService orderService = serviceFactory.getOrderService();
            IClientService clientService = serviceFactory.getClientService();
            Integer orderId = orderService.getOrderIdByClientId(clientId);
            List<Product> allProducts = new ArrayList<>();
            List<Product> tempProducts = productService.getProductByOrderId(orderId);
            List<Order> orders = orderService.getPaymentOrdersByClientId(clientId);

            if (tempProducts != null) {
                for (Product product : tempProducts) {
                    product.setOrdered(0);
                    product.setCommonCost();
                }
                allProducts.addAll(tempProducts);
            }
            for (Order order : orders) {
                order.setDate(convertDataToString(order.getDate()));
                tempProducts = productService.getProductByOrderId(order.getId());
                if (tempProducts != null) {
                    for (Product product : tempProducts) {
                        product.setOrdered(1);
                        product.setOrderId(order.getId());
                        product.setCommonCost();
                    }
                    allProducts.addAll(tempProducts);
                }
            }
            if (allProducts.size() > 0 && orderId != null) {
                request.setAttribute("products", allProducts);
                request.setAttribute("orderCost", orderService.getOrderCost(clientId));
                request.setAttribute("orders", orders);
                request.setAttribute("point", clientService.getClientById(clientId).getPoint());
                rewrite(request);
            } else {
                diagnoseError(request);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Index command finish");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.BASKET_ERROR.getValue(), "Ничего не найдено");
        } else {
            request.getSession().setAttribute(AttributeParameterName.BASKET_ERROR.getValue(), "Nothing found");
        }
    }

    /**
     * @param request
     */
    private void rewrite(HttpServletRequest request) {
        request.setAttribute(AttributeParameterName.BASKET_ERROR.getValue(), request.getSession().getAttribute(AttributeParameterName.BASKET_ERROR.getValue()));
        request.getSession().removeAttribute(AttributeParameterName.BASKET_ERROR.getValue());
        request.setAttribute(AttributeParameterName.ACCOUNT_PAYMENT_ERROR.getValue(), request.getSession().getAttribute(AttributeParameterName.ACCOUNT_PAYMENT_ERROR.getValue()));
        request.getSession().removeAttribute(AttributeParameterName.ACCOUNT_PAYMENT_ERROR.getValue());
    }

    /**
     * @param data
     * @return String
     */
    private String convertDataToString(String data) {
        data = data.substring(0, data.length() - 5);
        String[] strings = data.split(" ");
        String[] strings1 = strings[0].split("-");
        return strings[1] + " " + strings1[2] + "-" + strings1[1] + "-" + strings1[0];
    }
}