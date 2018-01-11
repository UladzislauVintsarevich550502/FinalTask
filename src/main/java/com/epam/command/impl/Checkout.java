package com.epam.command.impl;

import com.epam.service.IOrderService;
import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceLogicException;
import com.epam.service.factory.ServiceFactory;
import com.epam.service.utils.Constants;
import com.epam.servlet.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Checkout implements com.epam.command.ICommand {
    private static Logger logger = Logger.getLogger(Checkout.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.MEDICAMENT;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String idRole = session.getAttribute(RequestEnum.USER_ROLE.getValue()).toString();
            if (idRole.equals(Constants.USER)) {
                IOrderService orderService = serviceFactory.getOrderServiceImpl();
                String idUser = request.getSession().getAttribute(RequestEnum.USER_ID.getValue()).toString();
                String idMedicament = request.getParameter(RequestEnum.ID_MEDICAMENT.getValue());
                String number = request.getParameter(RequestEnum.NUMBER.getValue());
                String dosage = request.getParameter(RequestEnum.DOSAGE.getValue());

                orderService.checkout(idUser, idMedicament, number, dosage);

                request.setAttribute(RequestEnum.ADD_TO_BASKET.getValue(), "Order is successfully served");
            } else {
                jspPageName = JspPageName.INFORMATION;
                request.setAttribute(RequestEnum.INFORMATION.getValue(), "Нет прав");
            }
        } catch (ServiceException e) {
            logger.error(((String) request.getSession().getAttribute(RequestEnum.ADD_TO_BASKET.getValue())), e);
            request.setAttribute(RequestEnum.ADD_TO_BASKET.getValue(), e.getCause().getMessage());
        } catch (ServiceLogicException e) {
            logger.error(((String) request.getSession().getAttribute(RequestEnum.ADD_TO_BASKET.getValue())), e);
            request.setAttribute(RequestEnum.ADD_TO_BASKET.getValue(), e.getCause().getMessage());
        }
        return jspPageName.getPath();
    }
}
