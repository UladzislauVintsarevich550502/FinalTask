package bsuir.vintsarevich.buisness.client.service.impl;

import bsuir.vintsarevich.buisness.admin.dao.IAdminDao;
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

    private static final Logger LOGGER = Logger.getLogger(ClientService.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public void updateLocale(String clientLogin, String locale) throws ServiceException {

    }

    @Override
    public Client getClientByLogin(String clientLogin) throws ServiceException {
        return null;
    }

    @Override
    public Client signUp(String name, String surname, String login, String password, String email) throws ServiceException, ServiceLogicException {
        LOGGER.log(Level.DEBUG, "Client Service: start SignUp");
        IClientDao clientDao = daoFactory.getClientDao();
        IAdminDao adminDao = daoFactory.getAdminDao();
        Client client;

        try {
            Validator.isNull(name, login, password, name, surname, email);
            Validator.isEmptyString(name, login, password, name, surname, email);
            Validator.matchProperName(name, surname);
            Validator.matchLogin(login);
            Validator.matchPassword(password);
            Validator.matchEmail(email);
            if (!adminDao.findAdminByLogin(login)) {
                password = Hasher.hashBySha1(password);
                client = new Client(name, surname, login, password, email, "активен", 0);
                return (clientDao.addClient(client));
            }
        } catch (ValidatorException e) {
            throw new ServiceException(e);
        } catch (NumberFormatException e) {
            throw new ServiceException("number format exception", e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public Client signIn(String clientLogin, String clientPassword) {
        LOGGER.log(Level.DEBUG, "Client Service: start SignIn");
        Client client;
        IClientDao clientDao = daoFactory.getClientDao();
        try {
            Validator.isNull(clientLogin, clientPassword);
            Validator.isEmptyString(clientLogin, clientPassword);
            //откоментить после того как из проги подобавляю
            clientPassword = Hasher.hashBySha1(clientPassword);
            client = clientDao.signIn(clientLogin, clientPassword);
        } catch (DaoException | ValidatorException e) {
            return null;
        }
        LOGGER.log(Level.DEBUG, "Client Service: finish SignIn");
        return client;
    }

    @Override
    public List<Client> getAllClients() throws ServiceException {
        return null;
    }
}
