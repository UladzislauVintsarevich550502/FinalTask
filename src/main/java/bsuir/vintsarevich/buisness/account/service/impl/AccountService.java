package bsuir.vintsarevich.buisness.account.service.impl;

import bsuir.vintsarevich.buisness.account.dao.IAccountDao;
import bsuir.vintsarevich.buisness.account.service.IAccountService;
import bsuir.vintsarevich.entity.Account;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class AccountService implements IAccountService {

    private static final Logger LOGGER = Logger.getLogger(AccountService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public boolean addAccount(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Account Product service: start add account");
        IAccountDao accountDao = daoFactory.getAccountDao();
        Account account;
        Integer accountNumber;
        try {
            do {
                accountNumber = (int) (Math.random() * 100000);
            } while (accountDao.checkAccountNumber(accountNumber));
            account = new Account(accountNumber, 0.0, clientId);
            if (accountDao.addAccount(account)) {
                return true;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Account Product service: finish add account");
        return false;
    }

    @Override
    public boolean editAccount(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Account service: start edit account");
        IAccountDao accountDao = daoFactory.getAccountDao();
        try {
            accountDao.editAccount(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Account service: finish edit account");
        return true;
    }
}
