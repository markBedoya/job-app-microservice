package org.example.review.review;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<Review, Long> {
  List<Review> findByCompanyId(Long companyId);
}
