package bsuir.vintsarevich.buisness.client.service.impl;

import bsuir.vintsarevich.buisness.admin.dao.IAdminDao;
import bsuir.vintsarevich.buisness.client.dao.IClientDao;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.entity.Order;
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
    private final static Double DISCOUNT = 0.05;

    @Override
    public boolean countPoints(Order order) throws ServiceException {
        IClientDao clientDao = daoFactory.getClientDao();
        LOGGER.log(Level.DEBUG, "ClientService: start countPoints");
        Double points = DISCOUNT * order.getCost();
        try {
            if (clientDao.addPoints(points, order.getClientId())) {
                LOGGER.log(Level.DEBUG, "ClientService: success countPoints");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "ClientService: finish countPoints");
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean clearPoints(Integer clientId) throws ServiceException {
        IClientDao clientDao = daoFactory.getClientDao();
        LOGGER.log(Level.DEBUG, "ClientService: start clearPoints");
        try {
            if (clientDao.clearPoints(clientId)) {
                LOGGER.log(Level.DEBUG, "ClientService: success clearPoints");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "ClientService: finish clearPoints");
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
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
            password = Hasher.hashBySha1(password);
            if (!adminDao.findAdminByLogin(login)) {
                client = new Client(name, surname, login, password, email, "active", 0.0);
                return (clientDao.addClient(client));
            }
        } catch (ValidatorException e) {
            throw new ServiceException(e);
        } catch (NumberFormatException e) {
            throw new ServiceException(e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Client Service: finish SignUp");
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
        LOGGER.log(Level.DEBUG, "Client Service: Start get all clients");
        List<Client> clients;
        try {
            IClientDao clientDao = daoFactory.getClientDao();
            clients = clientDao.getAllClients();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Client Service: Finish get all clients");
        return clients;
    }

    @Override
    public boolean deleteClient(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Sevice: Delete client start");
        IClientDao clientDao = daoFactory.getClientDao();
        try {
            clientDao.deleteClient(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Client Service: Finish delete client");
        return true;
    }

    @Override
    public boolean changeClientStatus(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Sevice: Change client status start");
        IClientDao clientDao = daoFactory.getClientDao();
        try {
            clientDao.changeClientStatus(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Client Service: Finish change client status");
        return true;
    }

    @Override
    public boolean checkPassword(String password, Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service: check password start");
        password = Hasher.hashBySha1(password);
        IClientDao clientDao = daoFactory.getClientDao();
        try {
            LOGGER.log(Level.DEBUG, "Client Service: finish check password");
            return clientDao.checkPassword(password, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changePassword(String password, Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service: change password start");
        password = Hasher.hashBySha1(password);
        IClientDao clientDao = daoFactory.getClientDao();
        try {
            clientDao.changePassword(password, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Client Service: finish change password");
        return true;
    }

    @Override
    public boolean editPoint(Integer clientId, Double clientPoint) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service: edit points start");
        IClientDao clientDao = daoFactory.getClientDao();
        try {
            clientDao.editPoint(clientId, clientPoint);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Client Service: finish edit points");
        return true;
    }

    @Override
    public Client getClientById(Integer clientId) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service: get client by id start");
        IClientDao clientDao = daoFactory.getClientDao();
        try {
            LOGGER.log(Level.DEBUG, "Client Service: finish get client by id");
            return clientDao.getClientById(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }
}
