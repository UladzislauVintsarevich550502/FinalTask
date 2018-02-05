package bsuir.vintsarevich.buisness.order.service.impl;

import bsuir.vintsarevich.buisness.order.dao.IOrderDao;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.product.dao.IProductDao;
import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderService implements IOrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public boolean addOrder(String orderType, Double orderCost, Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start addOrder");
        IOrderDao orderDao = daoFactory.getOrderDao();
        Order order;
        try {
            order = new Order(orderType, orderCost, clientId);
            if (orderDao.addOrder(order)) {
                return true;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Order service: finish addOrder");
        return false;
    }

    @Override
    public boolean editOrder(Integer clientId, Double orderCost, Integer productCount) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start edit order");
        IOrderDao orderDao = daoFactory.getOrderDao();
        try {
            orderDao.editOrder(clientId, orderCost, productCount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Order service: finish edit order");
        return true;
    }

    @Override
    public Double getOrderCost(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: get Order cost start");
        IOrderDao orderDao = daoFactory.getOrderDao();
        Double orderCost;
        try {
            orderCost = (orderDao.getOrderByClientId(clientId)).getCost();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Order service: get Order cost finish");
        return orderCost;
    }

    @Override
    public Integer paymentOrder(String orderType, String orderData, Double orderCost, Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start payment order");
        IOrderDao orderDao = daoFactory.getOrderDao();
        Order order;
        try {
            order = new Order(orderType, orderData, orderCost, clientId);
            return orderDao.paymentOrder(order);
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Order service: finish payment order");
        return null;
    }

    @Override
    public boolean clearOrderCost(Integer orderId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start clear order");
        IOrderDao orderDao = daoFactory.getOrderDao();
        try {
            orderDao.clearOrderCost(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Order service: finish clear order");
        return true;    }

    @Override
    public List<Order> getPaymentOrdersByClientId(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start get orders by clientId");
        List<Order> orders;
        try {
            IOrderDao orderDao = daoFactory.getOrderDao();
            orders = orderDao.getPaymentOrdersByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Order Service: Finish get orders by clientId");
        return orders;
    }

    @Override
    public Integer getOrderIdByClientId(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG,"Order Service: start get order id");
        IOrderDao orderDao = daoFactory.getOrderDao();
        try {
            return orderDao.getOrderIdByClientId(clientId);
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG,"Order Service: finish get order id");
        return null;
    }

    @Override
    public List<Order> getAllOrdersByClientId(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start get all orders by clientId");
        List<Order> orders;
        try {
            IOrderDao orderDao = daoFactory.getOrderDao();
            orders = orderDao.getAllOrdersByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Order Service: Finish get all orders by clientId");
        return orders;
    }
}

