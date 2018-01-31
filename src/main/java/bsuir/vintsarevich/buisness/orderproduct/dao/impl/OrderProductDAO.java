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
    public static String ADD_ORDER_PRODUCT = "INSERT INTO epamcafe.orderproducts (orderId, productId) VALUES(?,?) ";
    public static String DELETE_ORDER_PRODUCT = "DELETE FROM epamcafe.orderproducts WHERE (orderproducts.orderId, orderproducts.productId)=(?,?)";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    @Override
    public boolean addOrderProduct(OrderProducts orderProducts) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Add order start");
        try {
            System.out.println(orderProducts);
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(ADD_ORDER_PRODUCT);
            statement.setInt(1, orderProducts.getOrderId());
            statement.setInt(2, orderProducts.getProductId());
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
            connection = connectionPool.retrieve();
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
}
