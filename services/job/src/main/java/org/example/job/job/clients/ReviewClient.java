package org.example.job.job.clients;

import java.util.List;
import org.example.job.job.externalpojo.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "review-service",
    url = "${review-service.url}")
public interface ReviewClient {

  @GetMapping("/reviews")
  List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
