package org.example.review.review.messaging;

import lombok.AllArgsConstructor;
import org.example.review.review.Review;
import org.example.review.review.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewMessageProducer {

  private final RabbitTemplate rabbitTemplate;

  public void sendMessage(Review review) {
    ReviewMessage reviewMessage = ReviewMessage.builder()
        .id(review.getId())
        .title(review.getTitle())
        .message(review.getMessage())
        .rating(review.getRating())
        .companyId(review.getCompanyId())
        .build();
    rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
  }

}
