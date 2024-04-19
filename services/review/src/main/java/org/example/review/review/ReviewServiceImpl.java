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
  public List<Review> getAllReviews(Long companyId) {
    //use companyId
    return reviewRepository.findByCompanyId(companyId);
  }

  @Override
  public Optional<Review> getReviewById(Long reviewId) {
    return reviewRepository.findById(reviewId);
  }

  @Override
  public boolean createReview(Long companyId, Review review) {
    if (companyId == null) {
      return false;
    }
    review.setCompanyId(companyId);
    reviewRepository.save(review);
    return true;
  }

  @Override
  public boolean updateReview(Long reviewId, Review updatedReview) {
    Optional<Review> reviewOptional= reviewRepository.findById(reviewId);
    if (reviewOptional.isEmpty()) {
      return false;
    }
    Review reviewToUpdate = reviewOptional.get();
    reviewToUpdate.setTitle(updatedReview.getTitle());
    reviewToUpdate.setMessage(updatedReview.getMessage());
    reviewToUpdate.setRating(updatedReview.getRating());
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
