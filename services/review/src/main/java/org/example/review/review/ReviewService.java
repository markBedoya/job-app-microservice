package org.example.review.review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
  List<Review> getAllReviews(Long companyId);
  Optional<Review> getReviewById(Long reviewId);
  boolean createReview(Long companyId, Review review);
  boolean updateReview(Long reviewId, Review updatedReview);
  boolean deleteReviewById(Long reviewId);

}
