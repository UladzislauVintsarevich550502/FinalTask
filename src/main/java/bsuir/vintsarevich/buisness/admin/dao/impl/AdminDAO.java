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
import java.util.List;

public class AdminDAO implements IAdminDao {
    public static String GET_ADMIN_BY_LOGIN_AND_PASSWORD = "SELECT * FROM epam-cafe.admin WHERE login=? AND password=?;";
    private static final Logger LOGGER = Logger.getLogger(AdminDAO.class);
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private Admin adminEnyity;
    private List<Admin> admins;


    @Override
    public boolean addAdmin() throws DaoException {
        return false;
    }

    @Override
    public boolean deleteAdmin() {
        return false;
    }

    @Override
    public boolean createAdmin() {
        return false;
    }

    @Override
    public Admin signIn(String login, String password) throws DaoException {
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
    public Admin getAdminById(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Admin> getAllAdmins() throws DaoException {
        return null;
    }

    private Admin createAdminByResultSet(ResultSet resultSet) throws DaoException {
        Admin admin = new Admin();
        try {
            admin.setLogin(resultSet.getString("adminLogin"));
            admin.setPassword(resultSet.getString("adminPassword"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return admin;
    }
}
