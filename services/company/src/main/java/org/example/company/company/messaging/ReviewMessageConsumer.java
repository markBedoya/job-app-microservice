package org.example.company.company.messaging;

import lombok.AllArgsConstructor;
import org.example.company.company.CompanyService;
import org.example.company.company.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewMessageConsumer {
  private final CompanyService companyService;

  @RabbitListener(queues = "companyRatingQueue")
  public void consumeMessage(ReviewMessage reviewMessage) {
    companyService.updateCompanyRating(reviewMessage);
  }

}
