package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.account.service.IAccountService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderDeny implements ICommand {
    private final static Logger LOGGER = Logger.getLogger(OrderDeny.class);
    private final JspPageName jspPageName = JspPageName.ORDER;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.DEBUG, "start order deny");
        Integer orderId = Integer.valueOf(request.getParameter(JspElemetName.ORDER_ID.getValue()));
        IOrderService orderService = ServiceFactory.getInstance().getOrderService();
        IClientService clientService = ServiceFactory.getInstance().getClientService();
        try {
            Order order = orderService.getOrderByOrderId(orderId);
            if (order.getType().equals("ordered")) {
                clientService.clearPoints(order.getClientId());
                orderService.deleteOrder(orderId);
            } else if (order.getType().equals("payment")) {
                clientService.clearPoints(order.getClientId());
                IAccountService accountService = ServiceFactory.getInstance().getAccountService();
                accountService.editAccount(order.getClientId(), -order.getCost());
                orderService.deleteOrder(orderId);
            }

        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            LOGGER.log(Level.DEBUG, "finish order deny");
            return jspPageName.getPath();
        }
        LOGGER.log(Level.DEBUG, "success order deny");
        try {
            response.sendRedirect("/cafe.by/order_show");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jspPageName.getPath();
    }
}
