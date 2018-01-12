package bsuir.vintsarevich.buisness.client.dao.impl;

import bsuir.vintsarevich.buisness.client.dao.IClientDao;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.exception.dao.ConnectionException;
import bsuir.vintsarevich.exception.dao.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDAO implements IClientDao {
    public static String GET_CLIENT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM epam-cafe.client WHERE login=? AND password=?;";
    public static String GET_CLIENT_BY_ID = "SELECT * FROM pharmacy.account WHERE id=?;";
    public static String SIGN_UP_CLIENT = "INSERT INTO account (idRole,login,password,name,surname,address) VALUES(?,?,?,?,?,?);";
    public static String GET_ALL_CLIENTS = "SELECT * FROM pharmacy.account WHERE idRole=1;";
    private static Logger logger = Logger.getLogger(ClientDAO.class);
    ConnectionPool connectionPool;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;
    Client clientEntity;
    List<Client> clients;


    @Override
    public boolean addClient() throws DaoException {
        return false;
    }

    @Override
    public boolean deleteClient() {
        return false;
    }

    @Override
    public boolean createClient() {
        return false;
    }

    @Override
    public boolean signUp(Client client) throws DaoException {
        return false;
    }

    @Override
    public Client signIn(String login, String password) throws DaoException {
        logger.log(Level.DEBUG, "ClientDao.signIn()");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_CLIENT_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = null;
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                clientEntity = createClientByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error with adding in database" + e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        logger.log(Level.DEBUG, "ClientDao.signIn() - success");
        return clientEntity;
    }

    @Override
    public Client getClientById(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Client> getAllClients() throws DaoException {
        return null;
    }

    private Client createClientByResultSet(ResultSet resultSet) throws DaoException {
        Client client = new Client();
        try {
            client.setId(resultSet.getInt("clientId"));
            client.setName(resultSet.getString("clientName"));
            client.setSurname(resultSet.getString("clientSurname"));
            client.setLogin(resultSet.getString("clientLogin"));
            client.setPassword(resultSet.getString("clientPassword"));
            client.setEmail(resultSet.getString("clientEmail"));
            client.setStatus(resultSet.getString("clientStatus"));
            client.setPoint(resultSet.getInt("clientPoint"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }
}
