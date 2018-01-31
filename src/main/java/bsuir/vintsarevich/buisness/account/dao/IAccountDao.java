package bsuir.vintsarevich.buisness.account.dao;

import bsuir.vintsarevich.entity.Account;
import bsuir.vintsarevich.exception.dao.DaoException;

public interface IAccountDao {

    boolean addAccount(Account account) throws DaoException;

    boolean checkAccountNumber(Integer accountNumber) throws DaoException;

    boolean editAccount(Integer clientId) throws DaoException;
}
