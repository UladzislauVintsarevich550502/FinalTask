package com.epam.dao;

import com.epam.dao.exception.ConnectionException;
import com.epam.dao.exception.DaoException;
import com.epam.dao.pool.ConnectionPool;
import com.epam.entity.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDao implements IOrderDao {
    public static String ADD_ORDER = "INSERT INTO pharmacy.order (idUser,idMedicament,idOrderStatus,number,dosage) VALUES(?,?,?,?,?);";
    public static String CHANGE_ORDER_STATUS = "UPDATE pharmacy.order SET (idOrderStatus) VALUES(?) WHERE idOrder=?;";
    private static Logger logger = Logger.getLogger(OrderDao.class);
    ConnectionPool connectionPool;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;
    Order order;
    List<Order> orders;

    @Override
    public boolean addOrder(Order order) throws DaoException {
        logger.debug("OrderDao.addOrder()");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(ADD_ORDER);
            statement.setInt(1, order.getIdUser());
            statement.setInt(2, order.getIdMedicament());
            statement.setInt(3, order.getIdOrderStatus());
            statement.setInt(4, order.getNumber());
            statement.setInt(5, order.getDosage());
            if (statement.executeUpdate() != 0) {
                logger.debug("OrderDao.addOrder()-success");
                return true;
            } else {
                logger.debug("OrderDao.addOrder()-failed");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(addOrder)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
    }

    @Override
    public boolean deleteOrder() {
        return false;
    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public List<Order> getUsersByUserLogin(String login) {
        return null;
    }

//    @Override
//    public List<Medicament> getMedicamentsByOrderStatus(int idUser, int idOrderStatus) throws DaoException {
//        logger.debug("OrderDao.getMedicamentsInBasket()");
//        try {
//            connectionPool = ConnectionPool.getInstance();
//            connection = connectionPool.retrieve();
//            statement = null;
//            statement = connection.prepareStatement(GET_MEDICAMENTS_BY_ORDER_STATUS);
//            statement.setInt(1,idUser);
//            statement.setInt(2,idOrderStatus);
//            resultSet = null;
//            resultSet = statement.executeQuery();
//            medicament = null;
//            medicaments = new ArrayList<Medicament>();
//            while (resultSet.next()) {
//                medicament = load(resultSet);
//                medicaments.add(medicament);
//            }
//        }catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                throw new DaoException(e);
//            }
//            throw new DaoException("Error of query to database(getAllMedicaments)", e);
//        } catch (ConnectionException e){
//            throw new DaoException("Error with connection with database"+e);
//        } finally {
//            if (connectionPool != null) {
//                connectionPool.putBackConnection(connection, statement, resultSet);
//            }
//        }
//        logger.debug("MedicamentDao.getAllMedicaments()-success");
//        return medicaments;
//    }

    @Override
    public boolean changeOrderStatus(Order order) throws DaoException {
        logger.debug("OrderDao.changeOrderStatus()");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(CHANGE_ORDER_STATUS);
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getIdOrderStatus());
            if (statement.executeUpdate() != 0) {
                logger.debug("OrderDao.changeOrderStatus()-success");
                return true;
            } else {
                logger.debug("OrderDao.changeOrderStatus()-failed");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(changeOrderStatus)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
    }
}
