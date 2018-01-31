package bsuir.vintsarevich.buisness.account.service;

import bsuir.vintsarevich.exception.service.ServiceException;

public interface IAccountService {

    boolean addAccount(Integer clientId) throws ServiceException;

    boolean editAccount(Integer clientId) throws ServiceException;

}
