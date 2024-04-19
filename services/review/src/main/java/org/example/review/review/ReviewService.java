package org.example.review.review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
  List<Review> getAllReviews();
  Optional<Review> getReviewById(Long reviewId);
  void createReview(Review review);
  boolean updateReviewById(Long reviewId, Review updatedReview);
  boolean deleteReviewById(Long reviewId);

}
