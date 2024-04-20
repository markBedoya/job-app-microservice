package org.example.job.job.mapper;

import java.util.List;
import lombok.experimental.UtilityClass;
import org.example.job.job.externalpojo.Company;
import org.example.job.job.externalpojo.Review;
import org.example.job.job.pojo.Job;
import org.example.job.job.dto.JobDTO;

@UtilityClass
public class JobMapper {

  public static JobDTO mapToJobDto(Job job, Company company, List<Review> reviewList) {
    return JobDTO.builder()
        .id(job.getId())
        .title(job.getTitle())
        .description(job.getDescription())
        .location(job.getLocation())
        .minSalary(job.getMinSalary())
        .maxSalary(job.getMaxSalary())
        .company(company)
        .reviewList(reviewList)
        .build();
  }
}
