package org.example.review.review;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private ReviewDao reviewRepository;

  @Override
  public List<Review> getAllReviews() {
    return reviewRepository.findAll();
  }

  @Override
  public Optional<Review> getReviewById(Long reviewId) {
    return reviewRepository.findById(reviewId);
  }

  @Override
  public void createReview(Review review) {
    reviewRepository.save(review);
  }

  @Override
  public boolean updateReviewById(Long reviewId, Review updatedReview) {
    Optional<Review> reviewOptional= reviewRepository.findById(reviewId);
    if (reviewOptional.isEmpty()) {
      return false;
    }
    Review reviewToUpdate = reviewOptional.get();
    reviewToUpdate = updatedReview;
    reviewRepository.save(reviewToUpdate);
    return true;
  }

  @Override
  public boolean deleteReviewById(Long reviewId) {
    if (!reviewRepository.existsById(reviewId)) {
      return false;
    }
    reviewRepository.deleteById(reviewId);
    return true;
  }

}
