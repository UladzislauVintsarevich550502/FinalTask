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
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IClientDao {
    private static String GET_CLIENT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM epamcafe.client WHERE clientLogin=? AND clientPassword=?;";
    private static String GET_CLIENT_BY_LOGIN = "SELECT * FROM epamcafe.client WHERE clientLogin=?";
    private static String GET_CLIENT_BY_ID = "SELECT * FROM epamcafe.client WHERE clientId=?";
    private static String ADD_CLIENT = "INSERT INTO client (clientName,clientSurname,clientLogin,clientPassword,clientEmail," +
            "clientStatus,clientPoint) VALUES(?,?,?,?,?,?,?);";
    private static String DELETE_CLIENT = "DELETE FROM epamcafe.client WHERE clientId=?";
    private static String GET_ALL_CLIENTS = "SELECT * FROM epamcafe.client";
    private static String CHANGE_STATUS = " UPDATE epamcafe.client SET epamcafe.client.clientStatus=? WHERE epamcafe.client.clientId=?";
    private static final Logger LOGGER = Logger.getLogger(ClientDAO.class);
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private List<Client> clients;

    @Override
    public Client addClient(Client client) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start SignUp");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
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
                return getClientByLogin(client.getLogin());
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
    public boolean deleteClient(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: Delete product start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_CLIENT);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Delete client success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Delete client finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(deleteClient)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Client DAO: Delete client finish");
        }
    }

    @Override
    public Client signIn(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start SignIn");
        Client clientEntity = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_CLIENT_BY_LOGIN_AND_PASSWORD);
            System.out.println(login);
            System.out.println(password);
            statement.setString(1, login);
            statement.setString(2, password);
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
    public Client getClientByLogin(String clientLogin) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start get Client bu login");
        Client clientEntity = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_CLIENT_BY_LOGIN);
            statement.setString(1, clientLogin);
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
        LOGGER.log(Level.DEBUG, "Client DAO: finish get Client by login");
        return clientEntity;
    }

    @Override
    public Client getClientById(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start get Client by ib");
        Client clientEntity = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_CLIENT_BY_ID);
            statement.setInt(1, clientId);
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
        LOGGER.log(Level.DEBUG, "Client DAO: finish get Client by id");
        return clientEntity;
    }

    @Override
    public List<Client> getAllClients() throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: Start get all clients");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ALL_CLIENTS);
            resultSet = statement.executeQuery();
            Client clientEntity;
            clients = new ArrayList<>();
            if (!resultSet.next()) {
                return null;
            }
            do {
                clientEntity = createClientByResultSet(resultSet);
                clients.add(clientEntity);
            } while (resultSet.next());
            LOGGER.log(Level.INFO, clients);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(getAllclients)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "Client DAO: Finish get all clients");
        return clients;
    }

    @Override
    public boolean changeClientStatus(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: Change status start");
        try {
            boolean status = false;
            if (getClientById(clientId).getStatus().equals("banned")) {
                status = true;
            }
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHANGE_STATUS);
            statement.setInt(2, clientId);
            if (status) {
                statement.setString(1, "active");
            } else {
                statement.setString(1, "banned");
            }
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Change status success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Change status finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(Change status)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Client DAO: Change status finish");
        }
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
