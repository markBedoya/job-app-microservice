package org.example.job.job.externalpojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
  private Long id;
  private String title;
  private String message;
  private double rating;
}
