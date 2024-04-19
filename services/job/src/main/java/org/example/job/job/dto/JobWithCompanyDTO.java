package org.example.job.job.dto;

import lombok.Data;
import org.example.job.external.Company;
import org.example.job.job.Job;

@Data
public class JobWithCompanyDTO {
  private Job job;
  private Company company;

}
