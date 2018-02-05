package bsuir.vintsarevich.buisness.review.service.impl;

import bsuir.vintsarevich.buisness.review.dao.IReviewDao;
import bsuir.vintsarevich.buisness.review.service.IReviewService;
import bsuir.vintsarevich.entity.Review;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


public class ReviewService implements IReviewService {
    private static final Logger LOGGER = Logger.getLogger(ReviewService.class);
    private static final IReviewDao reviewDao = DaoFactory.getInstance().getReviewDao();
    @Override
    public boolean addReview(String text, Integer mark, Integer clientId) {
        LOGGER.log(Level.DEBUG, "Review Service: start addReview");
        Review review = new Review(text, mark, clientId);
        try {
            if (reviewDao.addReview(review)) {
                LOGGER.log(Level.DEBUG, "Review Service: success addReview");
                return true;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Review Service: finish addReview");
        return false;
    }

    @Override
    public boolean deleteReview(Integer reviewId) {
        LOGGER.log(Level.DEBUG, "Review Service: start deleteReview");
        try {
            if (reviewDao.deleteReview(reviewId)) {
                LOGGER.log(Level.DEBUG, "Review Service: success addReview");
                return true;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Review Service: finish deleteReview");
        return false;
    }

    @Override
    public boolean editReview(Integer reviewId, String text, Integer mark) {
        LOGGER.log(Level.DEBUG, "Review Service: start editReview");
        try {
            if (reviewDao.editReview(reviewId, text, mark)) {
                LOGGER.log(Level.DEBUG, "Review Service: success editReview");
                return true;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Review Service: finish editReview");
        return false;
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> reviews = null;
        LOGGER.log(Level.DEBUG, "Review Service: start getAllReviews");
        try {
            reviews = reviewDao.getAllReviews();
            if (reviews != null) {
                LOGGER.log(Level.DEBUG, "Review Service: success getAllReviews");
                return reviews;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Review Service: finish getAllReviews");
        return reviews;
    }
}
