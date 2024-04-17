package org.example.review.review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
  List<Review> getAllReviewsByCompanyId(Long companyId);
  Optional<Review> getReviewByCompanyIdReviewId(Long companyId, Long reviewId);
  boolean createCompanyReview(Long companyId, Review review);
  boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview);
  boolean deleteReviewById(Long companyId, Long reviewId);

}
