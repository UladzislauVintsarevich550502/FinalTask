package bsuir.vintsarevich.buisness.review.service;

import bsuir.vintsarevich.entity.Review;

import java.util.List;

public interface IReviewService {
    boolean addReview(String text, Integer mark, Integer clientId);
    boolean editReview(Integer reviewId, String text, Integer mark);
    boolean deleteReview(Integer reviewId);
    List<Review> getAllReviews();
}
