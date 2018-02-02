package bsuir.vintsarevich.buisness.order.service;

import bsuir.vintsarevich.exception.service.ServiceException;

public interface IOrderService {
    boolean addOrder(String orderStatus, Double orderCost, Integer clientId) throws ServiceException;

    boolean editOrder(Integer clientId, Double orderCost, Integer productCount) throws ServiceException;

    Double getOrderCost(Integer clientId) throws ServiceException;

}
