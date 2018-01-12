package bsuir.vintsarevich.buisness.client.service.impl;

import bsuir.vintsarevich.buisness.client.dao.IClientDao;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;
import bsuir.vintsarevich.exception.validation.ValidatorException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.utils.Hasher;
import bsuir.vintsarevich.utils.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class ClientService implements IClientService {

    private static Logger logger = Logger.getLogger(ClientService.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public void updateLocale(String clientLogin, String locale) throws ServiceException {

    }

    @Override
    public Client getClientByLogin(String clientLogin) throws ServiceException {
        return null;
    }

    @Override
    public boolean signUp(String name, String surname, String login, String password, String email) throws ServiceException, ServiceLogicException {
        return false;
    }

    @Override
    public Client signIn(String clientLogin, String clientPassword) {
        logger.log(Level.DEBUG, "UserService.signIn()");
        Client client = null;
        IClientDao clientDao = daoFactory.getClientDao();
        try {
            Validator.isNull(clientLogin, clientPassword);
            Validator.isEmptyString(clientLogin, clientPassword);
            clientPassword = Hasher.hashBySha1(clientPassword);
            client = clientDao.signIn(clientLogin, clientPassword);
        } catch (DaoException | ValidatorException e) {
            return null;
        }
        logger.debug("UserService.signIn() - success. ");
        return client;
    }

    @Override
    public List<Client> getAllClients() throws ServiceException {
        return null;
    }
}
