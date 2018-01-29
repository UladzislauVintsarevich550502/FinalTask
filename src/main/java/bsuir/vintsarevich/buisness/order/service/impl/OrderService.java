package bsuir.vintsarevich.buisness.order.service.impl;

import bsuir.vintsarevich.buisness.order.dao.IOrderDao;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class OrderService implements IOrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public boolean addOrder(String orderStatus, Double orderCost, Integer clientId) {
        LOGGER.log(Level.DEBUG, "Order service: start addOrder");
        IOrderDao orderDao = daoFactory.getOrderDao();
        Order order;
        try {
            order = new Order(orderStatus, orderCost, clientId);
            if (orderDao.addOrder(order)) {
                return true;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Order service: finish addOrder");
        return false;
    }
}
