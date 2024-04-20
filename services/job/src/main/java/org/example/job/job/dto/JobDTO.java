package org.example.job.job.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.job.job.externalpojo.Company;
import org.example.job.job.externalpojo.Review;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
  private Long id;
  private String title;
  private String description;
  private String minSalary;
  private String maxSalary;
  private String location;
  private Company company;
  private List<Review> reviewList;

}
