package bsuir.vintsarevich.buisness.orderproduct.service;

import bsuir.vintsarevich.exception.service.ServiceException;

public interface IOrderProductService {

    boolean addOrderProduct(Integer clientId, Integer productId) throws ServiceException;

    boolean deleteOrderProduct(Integer orderId, Integer productId) throws ServiceException;

}
