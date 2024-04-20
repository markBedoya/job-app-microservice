package org.example.job.job.service;

import java.util.List;
import org.example.job.job.dto.JobDTO;
import org.example.job.job.pojo.Job;

public interface JobService {
  List<JobDTO> getAllJobs();
  JobDTO getJobById(Long jobId);
  void createJob(Job job);
  boolean updateJobById(Long jobId, Job updatedJob);
  boolean deleteJobById(Long jobId);
}
