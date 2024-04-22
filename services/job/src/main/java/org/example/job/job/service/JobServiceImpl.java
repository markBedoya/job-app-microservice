package org.example.job.job.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
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
public class JobServiceImpl implements JobService {

  private JobDao jobDao;
  private CompanyClient companyClient;
  private ReviewClient reviewClient;

  @Override
  public List<JobDTO> getAllJobs() {
    List<Job> jobs = jobDao.findAll();
    return jobs.stream()
        .map(Optional::ofNullable)
        .map(this::convertToDTO)
        .toList();
  }

  private JobDTO convertToDTO (Optional<Job> job) {
    if (job.isEmpty()) {
      return new JobDTO();
    }

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
