package bsuir.vintsarevich.buisness.account.service;

import bsuir.vintsarevich.exception.service.ServiceException;

public interface IAccountService {

    Double getCashById(Integer clientId) throws ServiceException;

    boolean addAccount(Integer clientId) throws ServiceException;

    boolean editAccount(Integer clientId, Double orderCostNew) throws ServiceException;

    boolean findAccountByClientId(Integer clientId) throws ServiceException;

}
