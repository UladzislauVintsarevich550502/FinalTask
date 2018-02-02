package bsuir.vintsarevich.buisness.order.dao;

import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.exception.dao.DaoException;

public interface IOrderDao {

    boolean addOrder(Order order) throws DaoException;

    Integer getOrderIdByClientId(Integer clientId) throws DaoException;

    boolean editOrder(Integer clientId, Double orderCost,Integer productCount) throws DaoException;

    Order getOrderByClientId(Integer clientId) throws DaoException;
}
