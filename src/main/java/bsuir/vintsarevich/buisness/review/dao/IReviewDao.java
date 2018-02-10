package bsuir.vintsarevich.buisness.review.dao;

import bsuir.vintsarevich.entity.Review;
import bsuir.vintsarevich.exception.dao.DaoException;

import java.util.List;

public interface IReviewDao {
    boolean addReview(Review review) throws DaoException;
    boolean deleteReview(Integer id) throws DaoException;
    List<Review> getAllReviews() throws DaoException;
}
