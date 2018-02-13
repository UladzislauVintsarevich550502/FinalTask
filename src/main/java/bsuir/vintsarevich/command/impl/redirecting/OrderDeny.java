package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.account.service.IAccountService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Order;
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
 * class OrderDeny created to deny clients' orders by staff
 */
public class OrderDeny implements ICommand {
    private final static Logger LOGGER = Logger.getLogger(OrderDeny.class);
    private JspPageName jspPageName = JspPageName.INDEX;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.DEBUG, "start order deny");
        Integer orderId = Integer.valueOf(request.getParameter(AttributeParameterName.ORDER_ID.getValue()));
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
            response.sendRedirect(RedirectingCommandName.ORDER_SHOW.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.DEBUG, "finish order deny");
        return jspPageName.getPath();
    }
}
