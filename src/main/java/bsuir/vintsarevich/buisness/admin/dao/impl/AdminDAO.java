package bsuir.vintsarevich.buisness.admin.dao.impl;

import bsuir.vintsarevich.buisness.admin.dao.IAdminDao;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.entity.Admin;
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

public class AdminDAO implements IAdminDao {
    private static String GET_ADMIN_BY_LOGIN_AND_PASSWORD = "SELECT * FROM epamcafe.admin WHERE adminLogin=? AND adminPassword=?;";
    private static String ADD_ADMIN = "INSERT INTO admin (adminLogin, adminPassword) VALUES(?,?)";
    private static String DELETE_ADMIN = "DELETE FROM epamcafe.admin WHERE adminId=?";
    private static String GET_ALL_ADMINS = "SELECT *FROM epamcafe.admin";
    private static String GET_ADMIN_BY_LOGIN = "SELECT * FROM epamcafe.admin WHERE adminLogin=?";
    private static final Logger LOGGER = Logger.getLogger(AdminDAO.class);
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private List<Admin> admins;

    @Override
    public boolean addAdmin(Admin admin) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: Add admin start");
        try {
            System.out.println(admin);
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(ADD_ADMIN);
            statement.setString(1, admin.getLogin());
            statement.setString(2, admin.getPassword());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add admin success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add admin finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(addMedicament)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Admin DAO: Add admin finish");
        }
    }

    @Override
    public boolean deleteAdmin(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: Delete admin start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = connection.prepareStatement(DELETE_ADMIN);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Delete admin success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Delete admin finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(deleteAdmin)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Admin DAO: Delete admin finish");
        }
    }

    @Override
    public Admin signIn(String login, String password) throws DaoException {
        Admin adminEnyity = null;
        LOGGER.log(Level.DEBUG, "Admin DAO: start SignIn");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_ADMIN_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = null;
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                adminEnyity = createAdminByResultSet(resultSet);
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
        return adminEnyity;
    }

    @Override
    public boolean findAdminByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: start Find");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_ADMIN_BY_LOGIN);
            statement.setString(1, login);
            resultSet = null;
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return true;
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
        LOGGER.log(Level.DEBUG, "Admin DAO: finish Find");
        return false;
    }

    @Override
    public List<Admin> getAllAdmins() throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: Start get all admins");
        Admin adminEnyity;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_ALL_ADMINS);
            resultSet = null;
            resultSet = statement.executeQuery();
            admins = new ArrayList<>();
            while (resultSet.next()) {
                adminEnyity = createAdminByResultSet(resultSet);
                admins.add(adminEnyity);
            }
            LOGGER.log(Level.INFO, admins);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(getAdminDAO)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "Admin DAO: Finish get all admins");
        return admins;
    }

    private Admin createAdminByResultSet(ResultSet resultSet) throws DaoException {
        Admin admin = new Admin();
        try {
            admin.setId(resultSet.getInt("adminId"));
            admin.setLogin(resultSet.getString("adminLogin"));
            admin.setPassword(resultSet.getString("adminPassword"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return admin;
    }
}
