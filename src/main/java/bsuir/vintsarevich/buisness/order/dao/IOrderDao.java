package bsuir.vintsarevich.buisness.order.dao;

import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.exception.dao.DaoException;

public interface IOrderDao {

    boolean addOrder(Order order) throws DaoException;

    Integer getOrderIdByClientId(Integer clientId) throws DaoException;

    boolean editOrder(Integer clientId, Double orderCost) throws DaoException;

}
