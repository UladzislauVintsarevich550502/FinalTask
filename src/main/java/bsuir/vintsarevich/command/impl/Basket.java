package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Basket implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.BASKET;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Index command start");
        try {
            Integer clentId = ((User) request.getSession().getAttribute("user")).getId();
            IProductService producteService = serviceFactory.getProducteService();
            IOrderService orderService = serviceFactory.getOrderService();
            Integer orderId = orderService.getOrderIdByClientId(clentId);
            List<Product> allProducts = new ArrayList<>();
            List<Product> tempProducts = producteService.getProductByOrderId(orderId);
            List<Order> orders = orderService.getPaymentOrdersByClientId(clentId);
            if (tempProducts != null) {
                for (Product product : tempProducts) {
                    product.setOrdered(0);
                }
                allProducts.addAll(tempProducts);
            }
            for (Order order : orders) {
                order.setData(converDataToString(order.getData()));
                tempProducts = producteService.getProductByOrderId(order.getId());
                if (tempProducts != null) {
                    for (Product product : tempProducts) {
                        product.setOrdered(1);
                        product.setOrderId(order.getId());
                    }
                    allProducts.addAll(tempProducts);
                }
            }
            request.setAttribute("products", allProducts);
            request.setAttribute("orderCost", orderService.getOrderCost(clentId));
            request.setAttribute("orders", orders);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Index command finish");
        return jspPageName.getPath();
    }

    String converDataToString(String data) {
        data = data.substring(0, data.length() - 5);
        String[] strings = data.split(" ");
        String[] strings1 = strings[0].split("-");
        return strings[1] + " " + strings1[2] + "-" + strings1[1] + "-" + strings1[0];
    }
}
