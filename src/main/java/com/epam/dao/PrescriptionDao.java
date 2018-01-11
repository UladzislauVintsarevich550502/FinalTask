package com.epam.dao;

import com.epam.dao.exception.ConnectionException;
import com.epam.dao.exception.DaoException;
import com.epam.dao.pool.ConnectionPool;
import com.epam.entity.Prescription;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PrescriptionDao implements IPrescriptionDao {
    public static String GET_PRESCRIPTION_BY_ID = "SELECT * FROM pharmacy.prescription WHERE idPrescription=?;";
    public static String ADD_PRESCRIPTION = "INSERT INTO prescription (idDoctor,idUser,idMedicament,dateOfIssue,dateOfCompletion) VALUES(?,?,?,?,?);";
    private static Logger logger = Logger.getLogger(PrescriptionDao.class);
    ConnectionPool connectionPool;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;
    Prescription prescription;
    List<Prescription> prescriptions;

    @Override
    public boolean addPrescription(Prescription prescription) throws DaoException {

        logger.debug("PrescriptionDao.addPrescription()");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(ADD_PRESCRIPTION);
            statement.setInt(1, prescription.getIdDoctor());
            statement.setInt(2, prescription.getIdUser());
            statement.setInt(3, prescription.getIdMedicament());
            statement.setDate(4, prescription.getDateOfIssue());
            statement.setDate(5, prescription.getDateOfCompletion());
            if (statement.executeUpdate() != 0) {
                logger.debug("PrescriptionDao.addPrescription()-success");
                return true;
            } else {
                logger.debug("PrescriptionDao.addPrescription()-failed");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(addPrescription)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
    }

    @Override
    public boolean deletePriscription() {
        return false;
    }

    @Override
    public boolean createPrescription() {
        return false;
    }

    @Override
    public Prescription getPrescriptionById(int id) throws DaoException {
        logger.debug("PrescriptionDao.getPrescriptionById()");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_PRESCRIPTION_BY_ID);
            statement.setInt(1, id);
            resultSet = null;
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                prescription = load(resultSet);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(getPrescriptionById)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        logger.debug("PrescriptionDao.getPrescriptionById() - success " + prescription);
        return prescription;
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctorLogin(String doctorLogin) {
        return null;
    }

    @Override
    public boolean changeStatus(Prescription prescription) {
        return false;
    }

    private Prescription load(ResultSet resultSet) throws DaoException {
        Prescription prescription = new Prescription();
        try {
            prescription.setId(resultSet.getInt("idPrescription"));
            prescription.setDoctorId(resultSet.getInt("idDoctor"));
            prescription.setUserId(resultSet.getInt("idUser"));
            prescription.setIdMedicament(resultSet.getInt("idMedicament"));
            prescription.setDateOfIssue(resultSet.getDate("dateOfIssue"));
            prescription.setDateOfCompletion(resultSet.getDate("dateOfCompletion"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
        return prescription;
    }
}
