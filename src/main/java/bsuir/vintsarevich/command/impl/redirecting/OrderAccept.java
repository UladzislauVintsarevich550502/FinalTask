package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class OrderAccept created to accept clients' orders by staff
 */
public class OrderAccept implements ICommand {
    private final static Logger LOGGER = Logger.getLogger(OrderDeny.class);
    private JspPageName jspPageName = JspPageName.INDEX;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.DEBUG, "start order accept");
        Integer orderId = Integer.valueOf(request.getParameter(AttributeParameterName.ORDER_ID.getValue()));
        IOrderService orderService = ServiceFactory.getInstance().getOrderService();
        IClientService clientService = ServiceFactory.getInstance().getClientService();
        try {
            clientService.countPoints(orderService.getOrderByOrderId(orderId));
            orderService.deleteOrder(orderId);
            response.sendRedirect(RedirectingCommandName.ORDER_SHOW.getCommand());
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.DEBUG, "finish order accept");
        return jspPageName.getPath();
    }
}
