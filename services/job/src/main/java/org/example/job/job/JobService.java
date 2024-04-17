package org.example.job.job;

import java.util.List;
import java.util.Optional;

public interface JobService {
  List<Job> getAllJobs();
  Optional<Job> getJobById(Long jobId);
  void createJob(Job job);
  boolean updateJobById(Long jobId, Job updatedJob);
  boolean deleteJobById(Long jobId);
}
