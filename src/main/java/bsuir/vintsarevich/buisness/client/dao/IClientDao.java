package bsuir.vintsarevich.buisness.client.dao;

import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.exception.dao.DaoException;

import java.util.List;

public interface IClientDao {
    Client addClient(Client client) throws DaoException;

    boolean deleteClient(Integer id) throws DaoException;
    Client signIn(String login, String password) throws DaoException;

    Client getClientByLogin(String clientLogin) throws DaoException;

    List<Client> getAllClients() throws DaoException;
}
