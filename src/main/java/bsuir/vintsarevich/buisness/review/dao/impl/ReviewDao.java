package bsuir.vintsarevich.buisness.review.dao.impl;

import bsuir.vintsarevich.buisness.review.dao.IReviewDao;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.entity.Review;
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

public class ReviewDao implements IReviewDao {
    private static final Logger LOGGER = Logger.getLogger(ReviewDao.class);
    private static final String ADD_REVIEW = "INSERT INTO epamcafe.review (reviewText, reviewMark, clientId) VALUES (?, ?, ?)";
    private static final String DELETE_REVIEW = "DELETE FROM epamcafe.review WHERE reviewId=?";
    private static final String EDIT_REVIEW = "UPDATE epamcafe.review SET reviewText = ?, reviewMark = ? WHERE reviewId = ?";
    private static final String GET_ALL_REVIEWS = "SELECT * FROM epamcafe.review";
    private ConnectionPool connectionPool;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public boolean addReview(Review review) throws DaoException {
        LOGGER.log(Level.DEBUG, "ReviewDao: start addReview");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_REVIEW);
            statement.setString(1, review.getText());
            statement.setDouble(2, review.getMark());
            statement.setInt(3, review.getClientId());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "ReviewDao: success addReview");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "ReviewDao: finish addReview");
                return false;
            }
        } catch (ConnectionException e) {
            throw new DaoException(e);
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DaoException(exception);
            }
            throw new DaoException(exception);
        } finally {
            if (connectionPool != null)
                connectionPool.putBackConnection(connection, statement, resultSet);
        }
    }

    @Override
    public boolean deleteReview(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "ReviewDao: start deleteReview");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_REVIEW);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "ReviewDao: success deleteReview");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "ReviewDao: finish deleteReview");
                return false;
            }
        } catch (ConnectionException e) {
            throw new DaoException(e);
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DaoException(exception);
            }
            throw new DaoException(exception);
        } finally {
            if (connectionPool != null)
                connectionPool.putBackConnection(connection, statement, resultSet);
        }
    }

    @Override
    public boolean editReview(Integer id, String text, Integer mark) throws DaoException {
        LOGGER.log(Level.DEBUG, "ReviewDao: start deleteReview");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(EDIT_REVIEW);
            statement.setString(1, text);
            statement.setInt(2, mark);
            statement.setInt(3, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "ReviewDao: success editReview");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "ReviewDao: finish editReview");
                return false;
            }
        } catch (ConnectionException e) {
            throw new DaoException(e);
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DaoException(exception);
            }
            throw new DaoException(exception);
        } finally {
            if (connectionPool != null)
                connectionPool.putBackConnection(connection, statement, resultSet);
        }
    }

    @Override
    public List<Review> getAllReviews() throws DaoException {
        LOGGER.log(Level.DEBUG, "ReviewDao: start getAllReviews");
        List<Review> reviews = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ALL_REVIEWS);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                do {
                    reviews.add(createReviewByResultSet(resultSet));
                } while (resultSet.next());
            }
        } catch (ConnectionException e) {
            throw new DaoException(e);
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DaoException(exception);
            }
            throw new DaoException(exception);
        } finally {
            if (connectionPool != null)
                connectionPool.putBackConnection(connection, statement, resultSet);
        }
        LOGGER.log(Level.DEBUG, "ReviewDao: success getAllReviews");
        return reviews;
    }

    private Review createReviewByResultSet(ResultSet resultSet) throws DaoException {
        Review review = new Review();
        try {
            review.setReviewId(resultSet.getInt("reviewId"));
            review.setClientId(resultSet.getInt("clientId"));
            review.setMark(resultSet.getInt("reviewMark"));
            review.setText(resultSet.getString("reviewText"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return review;
    }
}
