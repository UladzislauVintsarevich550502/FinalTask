package bsuir.vintsarevich.buisness.order.service.impl;

import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.validation.ValidatorException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.utils.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * class OrderService created for preparation data before sending queries to database table "order"
 */
public class OrderService implements IOrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * @param orderType
     * @param orderCost
     * @param clientId
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean addOrder(String orderType, Double orderCost, Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start addOrder");
        try {
            Validator.isNull(orderType);
            Validator.isEmptyString(orderType);
            LOGGER.log(Level.DEBUG, "Order service: finish addOrder");
            Order order = new Order(orderType, orderCost, clientId);
            return daoFactory.getOrderDao().addOrder(order);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param clientId
     * @param orderCost
     * @param productCount
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean editOrder(Integer clientId, Double orderCost, Integer productCount) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start edit order");
        try {
            LOGGER.log(Level.DEBUG, "Order service: finish edit order");
            return daoFactory.getOrderDao().editOrder(clientId, orderCost, productCount);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param clientId
     * @return Double
     * @throws ServiceException
     */
    @Override
    public Double getOrderCost(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: get Order cost start");
        try {
            LOGGER.log(Level.DEBUG, "Order service: get Order cost finish");
            return daoFactory.getOrderDao().getOrderByClientId(clientId).getCost();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param orderType
     * @param orderData
     * @param orderCost
     * @param clientId
     * @return Integer
     * @throws ServiceException
     */
    @Override
    public Integer paymentOrder(String orderType, String orderData, Double orderCost, Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start payment order");
        try {
            Validator.isEmptyString(orderType,orderData);
            Validator.isNull(orderData,orderType);
            Order order = new Order(orderType, orderData, orderCost, clientId);
            LOGGER.log(Level.DEBUG, "Order service: finish payment order");
            return daoFactory.getOrderDao().paymentOrder(order);
        } catch (DaoException | ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param orderId
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean clearOrderCost(Integer orderId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order service: start clear order");
        try {
            LOGGER.log(Level.DEBUG, "Order service: finish clear order");
            return daoFactory.getOrderDao().clearOrderCost(orderId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param clientId
     * @return List<Order>
     * @throws ServiceException
     */
    @Override
    public List<Order> getPaymentOrdersByClientId(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start get orders by clientId");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Finish get orders by clientId");
            return daoFactory.getOrderDao().getPaymentOrdersByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param clientId
     * @return Integer
     * @throws ServiceException
     */
    @Override
    public Integer getOrderIdByClientId(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start get order id");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: finish get order id");
            return daoFactory.getOrderDao().getOrderIdByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param clientId
     * @return List<Order>
     * @throws ServiceException
     */
    @Override
    public List<Order> getAllOrdersByClientId(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start get all orders by clientId");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Finish get all orders by clientId");
            return daoFactory.getOrderDao().getAllOrdersByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @return List<Order>
     * @throws ServiceException
     */
    @Override
    public List<Order> getAllOrderedOrders() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start get all ordered orders");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Success get all orders by clientId");
            return daoFactory.getOrderDao().getAllOrderedOrders();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param orderId
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean deleteOrder(Integer orderId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start delete order");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: finish get all orders by clientId");
            return daoFactory.getOrderDao().deleteOrder(orderId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    /**
     * @param orderId
     * @return Order
     * @throws ServiceException
     */
    @Override
    public Order getOrderByOrderId(Integer orderId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: start getOrderByOrderId");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Success getOrderByOrderId");
            return daoFactory.getOrderDao().getOrderByOrderId(orderId);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}

