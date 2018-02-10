package bsuir.vintsarevich.buisness.orderproduct.dao;

import bsuir.vintsarevich.entity.OrderProduct;
import bsuir.vintsarevich.exception.dao.DaoException;

public interface IOrderProductDao {

    boolean addOrderProduct(OrderProduct orderProduct) throws DaoException;

    boolean deleteOrderProduct(Integer orderId, Integer productId) throws DaoException;

    boolean editOrderProduct(Integer productId, Integer productCount, Integer orderId) throws DaoException;

    boolean findOrderProduct(Integer productId, Integer orderId) throws DaoException;

    Integer orderProductCount(Integer productId) throws DaoException;

    boolean editOrderProductPayment(Integer orderIdNew, Integer orderId) throws DaoException;
}
