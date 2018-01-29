package bsuir.vintsarevich.buisness.order.dao;

import bsuir.vintsarevich.entity.Order;
import bsuir.vintsarevich.exception.dao.DaoException;

public interface IOrderDao {

    boolean addOrder(Order order) throws DaoException;
}
