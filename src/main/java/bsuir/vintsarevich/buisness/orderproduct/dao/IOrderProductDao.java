package bsuir.vintsarevich.buisness.orderproduct.dao;

import bsuir.vintsarevich.entity.OrderProducts;
import bsuir.vintsarevich.exception.dao.DaoException;

public interface IOrderProductDao {

    boolean addOrderProduct(OrderProducts orderProducts) throws DaoException;

    boolean deleteOrderProduct(Integer ordertId, Integer productId) throws DaoException;

    boolean editOrderProduct(Integer productId, Integer productCount) throws DaoException;

    boolean findOrderProduct(Integer productId) throws DaoException;

    Integer orderProductCount(Integer productId) throws DaoException;
}
