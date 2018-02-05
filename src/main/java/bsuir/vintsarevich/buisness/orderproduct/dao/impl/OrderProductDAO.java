package bsuir.vintsarevich.buisness.orderproduct.dao.impl;

import bsuir.vintsarevich.buisness.order.dao.impl.OrderDAO;
import bsuir.vintsarevich.buisness.orderproduct.dao.IOrderProductDao;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.entity.OrderProducts;
import bsuir.vintsarevich.exception.dao.ConnectionException;
import bsuir.vintsarevich.exception.dao.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderProductDAO implements IOrderProductDao {

    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);
    private static String ADD_ORDER_PRODUCT = "INSERT INTO epamcafe.orderproducts (orderId, productId, productCount) VALUES(?,?,?) ";
    private static String DELETE_ORDER_PRODUCT = "DELETE FROM epamcafe.orderproducts WHERE (orderproducts.orderId, orderproducts.productId)=(?,?)";
    private static String EDIT_ORDER_PRODUCT = "UPDATE epamcafe.orderproducts SET epamcafe.orderproducts.productCount = (epamcafe.orderproducts.productCount + ?) " +
            "WHERE epamcafe.orderproducts.productId = ? AND epamcafe.orderproducts.orderId=?";
    private static String FIND_ORDER_PRODUCT = "SELECT * FROM epamcafe.orderproducts WHERE epamcafe.orderproducts.productId=? AND epamcafe.orderproducts.orderId=?";
    private static String FIND_ORDER_PRODUCT_COUNT = "SELECT epamcafe.orderproducts.productCount FROM epamcafe.orderproducts WHERE epamcafe.orderproducts.productId=?";
    private static String EDIT_ORDER_PRODUCT_PAYMENT = "UPDATE epamcafe.orderproducts SET epamcafe.orderproducts.orderId=? WHERE epamcafe.orderproducts.orderId=?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    @Override
    public boolean addOrderProduct(OrderProducts orderProducts) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Add order start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = null;
            statement = connection.prepareStatement(ADD_ORDER_PRODUCT);
            statement.setInt(1, orderProducts.getOrderId());
            statement.setInt(2, orderProducts.getProductId());
            statement.setInt(3, orderProducts.getProductCount());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add order product success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add order product finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(addOrderProduct)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Product DAO: Add order product finish");
        }
    }

    @Override
    public boolean deleteOrderProduct(Integer orderId, Integer productId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete orderProduct start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_ORDER_PRODUCT);
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Delete orderProduct success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Delete orderProduct finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(deleteOrderProduct)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Product DAO: Delete orderProduct finish");
        }
    }

    @Override
    public boolean editOrderProduct(Integer productId, Integer productCount, Integer orderId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order product DAO: edit start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = null;
            statement = connection.prepareStatement(EDIT_ORDER_PRODUCT);
            statement.setInt(1, productCount);
            statement.setInt(2, productId);
            statement.setInt(3, orderId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "edit order product success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "edit order product finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(edit order product)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Order product DAO: edit product finish");
        }
    }

    @Override
    public boolean findOrderProduct(Integer productId, Integer orderId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order product DAO: find start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = null;
            statement = connection.prepareStatement(FIND_ORDER_PRODUCT);
            statement.setInt(1, productId);
            statement.setInt(2, orderId);
            resultSet = null;
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(edit order product)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Order product DAO: find product finish");
        }

    }

    @Override
    public Integer orderProductCount(Integer productId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order product DAO: find count start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = null;
            statement = connection.prepareStatement(FIND_ORDER_PRODUCT_COUNT);
            statement.setInt(1, productId);
            resultSet = null;
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("productCount");
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(find count order product)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Order product DAO: find count finish");
        }
        return null;
    }

    @Override
    public boolean editOrderProductPayment(Integer orderIdNew, Integer orderId) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order Product DAO: edit order product payment start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = null;
            statement = connection.prepareStatement(EDIT_ORDER_PRODUCT_PAYMENT);
            statement.setInt(1, orderIdNew);
            statement.setInt(2, orderId);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "edit order product payment success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "edit order product payment finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(edit order product payment)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Order Product DAO: edit order product payment finish");
        }
    }
}