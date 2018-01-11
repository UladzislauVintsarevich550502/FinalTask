package bsuir.vintsarevich.dto.client.dao;

import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.exception.dao.DaoException;
import java.util.List;

public interface IClientDao {
    boolean addClient() throws DaoException;
    boolean deleteClient();
    boolean createClient();
    boolean signUp(Client client) throws DaoException;
    Client signIn(String login, String password) throws DaoException;
    Client getClientById(int id) throws DaoException;
    List<Client> getAllClients() throws DaoException;
}
