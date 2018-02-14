package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.account.service.IAccountService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.order.dao.IOrderDao;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.orderproduct.service.IOrderProductService;
import bsuir.vintsarevich.command.ICommand;
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

/**
 * class Payment created to handle orders and potential payment for it
 */
public class Payment implements ICommand {
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

            if (orderCost != 0 && orderCost != null) {
                if (radio.equals("cash")) {
                    Integer orderIdNew = orderService.paymentOrder("ordered", dateTime, (orderCost - pointToPayment), clientId);
                    orderService.clearOrderCost(orderId);
                    orderProductService.editOrderProductPayment(orderIdNew, orderId);
                    clientService.editPoint(clientId, pointToPayment);
                } else {
                    if (accountService.findAccountByClientId(clientId)) {
                        if (accountService.getCashById(clientId) != null && accountService.getCashById(clientId) >= 0) {
                            Integer orderIdNew = orderService.paymentOrder("payment", dateTime, (orderCost - pointToPayment), clientId);
                            orderService.clearOrderCost(orderId);
                            orderProductService.editOrderProductPayment(orderIdNew, orderId);
                            accountService.editAccount(clientId, orderCost);
                            clientService.editPoint(clientId, pointToPayment);
                        } else {
                            diagnoseCashError(request);
                        }
                    } else {
                        diagnoseError(request);
                    }
                }
            } else {
                diagnoseOrderEmptyError(request);
            }
            response.sendRedirect(RedirectingCommandName.BASKET.getCommand());
        } catch (IOException | ServiceException | DaoException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish payment");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.ACCOUNT_PAYMENT_ERROR.getValue(), "Счет не добавлен");
        } else {
            request.getSession().setAttribute(AttributeParameterName.ACCOUNT_PAYMENT_ERROR.getValue(), "Account don't added");
        }
    }

    /**
     * @param request
     */
    private void diagnoseOrderEmptyError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.ACCOUNT_PAYMENT_ERROR.getValue(), "Заказ пуст");
        } else {
            request.getSession().setAttribute(AttributeParameterName.ACCOUNT_PAYMENT_ERROR.getValue(), "Order is empty");
        }
    }

    /**
     * @param request
     */
    private void diagnoseCashError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.ACCOUNT_PAYMENT_ERROR.getValue(), "У вас уже есть задолжность");
        } else {
            request.getSession().setAttribute(AttributeParameterName.ACCOUNT_PAYMENT_ERROR.getValue(), "You already have a debt");
        }
    }

}