package bsuir.vintsarevich.buisness.orderproduct.service.impl;

import bsuir.vintsarevich.buisness.order.dao.IOrderDao;
import bsuir.vintsarevich.buisness.order.service.impl.OrderService;
import bsuir.vintsarevich.buisness.orderproduct.dao.IOrderProductDao;
import bsuir.vintsarevich.buisness.orderproduct.service.IOrderProductService;
import bsuir.vintsarevich.entity.OrderProducts;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class OrderProductService implements IOrderProductService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public boolean addOrderProduct(Integer clientId, Integer productId) throws ServiceException {
        IOrderDao orderDao = daoFactory.getOrderDao();
        LOGGER.log(Level.DEBUG, "Order Product service: start addOrderProducts");
        IOrderProductDao orderProductDao = daoFactory.getOrderProductDao();
        OrderProducts orderProducts;
        try {
            Integer orderId = orderDao.getOrderIdByClientId(clientId);
            System.out.println(orderId);
            orderProducts = new OrderProducts(orderId, productId);
            if (orderProductDao.addOrderProduct(orderProducts)) {
                return true;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Order Product service: finish addOrderProducts");
        return false;
    }

    @Override
    public boolean deleteOrderProduct(Integer orderId, Integer productId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete orderProduct start");
        IOrderProductDao orderProductDao = daoFactory.getOrderProductDao();
        try {
            orderProductDao.deleteOrderProduct(orderId, productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "ProductService: finish orderProduct client");
        return true;
    }
}
