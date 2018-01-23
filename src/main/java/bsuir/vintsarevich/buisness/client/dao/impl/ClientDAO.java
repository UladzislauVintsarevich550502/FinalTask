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
    public static String GET_CLIENT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM epamcafe.client WHERE clientLogin=? AND clientPassword=?;";
    public static String GET_CLIENT_BY_LOGIN = "SELECT * FROM epamcafe.client WHERE clientLogin=?;";
    public static String ADD_CLIENT = "INSERT INTO client (clientName,clientSurname,clientLogin,clientPassword,clientEmail," +
            "clientStatus,clientPoint) VALUES(?,?,?,?,?,?,?);";
    public static String GET_ALL_CLIENTS = "SELECT * FROM pharmacy.account WHERE idRole=1;";
    private static final Logger LOGGER = Logger.getLogger(ClientDAO.class);

    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private Client clientEntity;
    private List<Client> clients;


    @Override
    public Client addClient(Client client) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start SignUp");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            System.out.println(client);
            statement = connection.prepareStatement(ADD_CLIENT);
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getLogin());
            statement.setString(4, client.getPassword());
            statement.setString(5, client.getEmail());
            statement.setString(6, client.getStatus());
            statement.setInt(7, client.getPoint());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.INFO, "Client DAO: Client adds successful");
                return findClientByLogin(client.getLogin());
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(addClient)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "Client DAO: finish SignUp");
        return null;
    }

    @Override
    public Client findClientByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start Find");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_CLIENT_BY_LOGIN);
            statement.setString(1, login);
            resultSet = null;
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return createClientByResultSet(resultSet);
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
        LOGGER.log(Level.DEBUG, "Admin DAO: finish SignIn");
        return null;
    }

    @Override
    public boolean signUp(Client client) throws DaoException {
        return false;
    }

    @Override
    public Client signIn(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start SignIn");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_CLIENT_BY_LOGIN_AND_PASSWORD);
            System.out.println(login);
            System.out.println(password);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = null;
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                clientEntity = createClientByResultSet(resultSet);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(signIn)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "Client DAO: finish SignIn");
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
            System.out.println(client);
            return client;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
