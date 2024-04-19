package org.example.job.job;

import java.util.List;
import java.util.Optional;
import org.example.job.job.dto.JobWithCompanyDTO;

public interface JobService {
  List<JobWithCompanyDTO> getAllJobs();
  Optional<Job> getJobById(Long jobId);
  void createJob(Job job);
  boolean updateJobById(Long jobId, Job updatedJob);
  boolean deleteJobById(Long jobId);
}
