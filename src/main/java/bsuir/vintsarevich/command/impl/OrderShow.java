package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderShow implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(OrderShow.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.DEBUG, "start order show");
        IOrderService orderService = ServiceFactory.getInstance().getOrderService();
        List<Order> orders = null;
        try {
            orders = orderService.getAllOrderedOrders();
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        request.setAttribute("orders", orders);
        LOGGER.log(Level.DEBUG, "success order show");
        return JspPageName.ORDER.getPath();
    }
}
