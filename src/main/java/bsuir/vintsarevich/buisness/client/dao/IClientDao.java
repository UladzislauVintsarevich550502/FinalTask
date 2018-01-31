package bsuir.vintsarevich.buisness.client.dao;

import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.exception.dao.DaoException;

public interface IClientDao {
    Client addClient(Client client) throws DaoException;

    Client findClientByLogin(String login) throws DaoException;

    boolean deleteClient(Integer id) throws DaoException;

    Client signIn(String login, String password) throws DaoException;
}
