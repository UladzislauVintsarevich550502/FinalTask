package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderAccept implements ICommand {
    private final static Logger LOGGER = Logger.getLogger(OrderDeny.class);
    private final JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.DEBUG, "start order accept");
        Integer orderId = Integer.valueOf(request.getParameter(JspElemetName.ORDER_ID.getValue()));
        IOrderService orderService = ServiceFactory.getInstance().getOrderService();
        IClientService clientService = ServiceFactory.getInstance().getClientService();
        try {
            clientService.countPoints(orderService.getOrderByOrderId(orderId));
            orderService.deleteOrder(orderId);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            LOGGER.log(Level.DEBUG, "finish order accept");
            return jspPageName.getPath();
        }
        LOGGER.log(Level.DEBUG, "success order accept");
        try {
            response.sendRedirect("/cafe.by/order_show");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jspPageName.getPath();
    }
}
