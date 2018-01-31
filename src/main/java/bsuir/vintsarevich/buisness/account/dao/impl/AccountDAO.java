package bsuir.vintsarevich.buisness.account.dao.impl;

import bsuir.vintsarevich.buisness.account.dao.IAccountDao;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.entity.Account;
import bsuir.vintsarevich.exception.dao.ConnectionException;
import bsuir.vintsarevich.exception.dao.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO implements IAccountDao {

    private static final Logger LOGGER = Logger.getLogger(AccountDAO.class);
    public static String ADD_ACCOUNT = "INSERT INTO epamcafe.account " +
            "(accountNumber,accountCredit,clientId) VALUES(?,?,?)";
    public static String CHECK_ACCOUNT_NUMBER = "SELECT * FROM epamcafe.account WHERE accountNumber = ?";
    public static String EDIT_ACCOUNT = "UPDATE epamcafe.account SET epamcafe.account.accountCredit = " +
            "(epamcafe.account.accountCredit - (SELECT orderCost FROM epamcafe.order" +
            " WHERE epamcafe.order.clientId = epamcafe.account.clientId)) " +
            "WHERE epamcafe.account.clientId = ?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;


    @Override
    public boolean addAccount(Account account) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Add account start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(ADD_ACCOUNT);
            statement.setInt(1, account.getAccountNumber());
            statement.setDouble(2, account.getAccountCredit());
            statement.setInt(3, account.getClientId());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add account success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add account finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(addAccount)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Product DAO: Add account finish");
        }
    }

    @Override
    public boolean checkAccountNumber(Integer accountNumber) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: check account number start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(CHECK_ACCOUNT_NUMBER);
            statement.setInt(1, accountNumber);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LOGGER.log(Level.DEBUG, "check account number success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "check account number finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(check account number)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }

            LOGGER.log(Level.DEBUG, "Account DAO: check account number finish");
        }
    }

    @Override
    public boolean editAccount(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: edit start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(EDIT_ACCOUNT);
            statement.setInt(1, clientId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "edit account success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "edit account finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(edit account)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Account DAO: edit finish");
        }
    }
}