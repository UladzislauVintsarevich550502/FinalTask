package com.epam.dao;

import com.epam.dao.exception.DaoException;
import com.epam.entity.Order;

import java.util.List;

public interface IOrderDao {
    boolean addOrder(Order order) throws DaoException;

    boolean changeOrderStatus(Order order) throws DaoException;

    boolean deleteOrder();

    Order getOrderById(int id);

    List<Order> getUsersByUserLogin(String login);

//    List<Medicament> getMedicamentsInBasket(int idUser) throws DaoException;
}
