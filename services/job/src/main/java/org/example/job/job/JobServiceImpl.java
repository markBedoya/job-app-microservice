package org.example.job.job;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

  private JobDao jobDao;

  @Override
  public List<Job> getAllJobs() {
    return jobDao.findAll();
  }

  @Override
  public Optional<Job> getJobById(Long jobId) {
    return jobDao.findById(jobId);
  }

  @Override
  public void createJob(Job job) {
    jobDao.save(job);
  }

  @Override
  public boolean updateJobById(Long jobId, Job updatedJob) {
    Optional<Job> jobOptional = jobDao.findById(jobId);
    if (jobOptional.isEmpty()) {
      return false;
    }
    Job jobToUpdate = jobOptional.get();
    jobToUpdate.setTitle(updatedJob.getTitle());
    jobToUpdate.setDescription(updatedJob.getDescription());
    jobToUpdate.setMinSalary(updatedJob.getMinSalary());
    jobToUpdate.setMaxSalary(updatedJob.getMaxSalary());
    jobToUpdate.setLocation(updatedJob.getLocation());
    jobToUpdate.setCompanyId(updatedJob.getCompanyId());
    jobDao.save(jobToUpdate);
    return true;
  }

  @Override
  public boolean deleteJobById(Long jobId) {
    if (!jobDao.existsById(jobId)) {
      return false;
    }
    jobDao.deleteById(jobId);
    return true;
  }

}
