package org.example.job.job.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.job.job.clients.CompanyClient;
import org.example.job.job.clients.ReviewClient;
import org.example.job.job.dao.JobDao;
import org.example.job.job.externalpojo.Company;
import org.example.job.job.dto.JobDTO;
import org.example.job.job.externalpojo.Review;
import org.example.job.job.mapper.JobMapper;
import org.example.job.job.pojo.Job;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class JobServiceImpl implements JobService {

  private JobDao jobDao;
  private CompanyClient companyClient;
  private ReviewClient reviewClient;
  private static int attempt = 0;

  @Override
  //@CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
  @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
  //@RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
  public List<JobDTO> getAllJobs() {
    log.info("Retry attempt: " + ++attempt);
    List<Job> jobs = jobDao.findAll();
    return jobs.stream()
        .map(Optional::ofNullable)
        .map(this::convertToDTO)
        .toList();
  }

  public List<String> companyBreakerFallback(Exception e) {
    List<String> list = new ArrayList<>();
    list.add("Dummy");
    return list;
  }

  private JobDTO convertToDTO (Optional<Job> job) {
    if (job.isEmpty()) {
      return new JobDTO();
    }

    //These rest calls can be called with Spring Rest Template
    //OpenFeign simplifies these calls
    Company company = companyClient.getCompany(job.get().getCompanyId());
    List<Review> reviewList = reviewClient.getReviews(job.get().getCompanyId());

    return JobMapper.mapToJobDto(job.get(), company, reviewList);
  }

  @Override
  public JobDTO getJobById(Long jobId) {
    return convertToDTO(jobDao.findById(jobId));
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
