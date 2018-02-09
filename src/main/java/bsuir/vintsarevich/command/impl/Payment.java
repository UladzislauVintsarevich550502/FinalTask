package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.account.service.IAccountService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.order.dao.IOrderDao;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.orderproduct.service.IOrderProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import com.sun.java.browser.plugin2.DOM;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Payment implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private JspPageName jspPageName = JspPageName.BASKET;
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start payment");

        IOrderService orderService = serviceFactory.getOrderService();
        IOrderProductService orderProductService = serviceFactory.getOrderProductService();
        IOrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        IAccountService accountService = ServiceFactory.getInstance().getAccountService();
        IClientService clientService = serviceFactory.getClientService();

        try {
            String radio = request.getParameter("choise_of_payment");
            String dateTime = request.getParameter("dateTime");
            Double pointToPayment = Double.valueOf(request.getParameter("point_to_payment"));
            Integer clientId = ((User) request.getSession().getAttribute("user")).getId();
            Double orderCost = orderService.getOrderCost(clientId);
            Integer orderId = orderDao.getOrderIdByClientId(clientId);

            if (radio.equals("cash")) {
                Integer orderIdNew = orderService.paymentOrder("ordered", dateTime, (orderCost - pointToPayment), clientId);
                orderService.clearOrderCost(orderId);
                orderProductService.editOrderProductPayment(orderIdNew, orderId);
                clientService.editPoint(clientId, pointToPayment);
            } else {
                if (accountService.findAccountByClientId(clientId)) {
                    Integer orderIdNew = orderService.paymentOrder("payment", dateTime, (orderCost - pointToPayment), clientId);
                    orderService.clearOrderCost(orderId);
                    orderProductService.editOrderProductPayment(orderIdNew, orderId);
                    accountService.editAccount(clientId, orderCost);
                    clientService.editPoint(clientId, pointToPayment);
                } else {
                    System.out.println("NO");
                }


            }
            response.sendRedirect("/cafe.by/basket");
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "Finish payment");
        return jspPageName.getPath();
    }

}
