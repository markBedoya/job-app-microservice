package org.example.job.job.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.example.job.job.dao.JobDao;
import org.example.job.job.externalpojo.Company;
import org.example.job.job.dto.JobDTO;
import org.example.job.job.externalpojo.Review;
import org.example.job.job.mapper.JobMapper;
import org.example.job.job.pojo.Job;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

  private JobDao jobDao;
  private RestTemplate restTemplate;

  @Override
  public List<JobDTO> getAllJobs() {
    List<Job> jobs = jobDao.findAll();
    return jobs.stream().map(this::convertToDTO).toList();
  }

  private JobDTO convertToDTO (Job job) {
    Company company = restTemplate.getForObject(
        "http://company:8081/companies/" + job.getCompanyId(), Company.class);

    ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
        "http://review:8083/reviews?companyId=" + job.getCompanyId(),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Review>>() {});

    return JobMapper.mapToJobDto(job, company, reviewResponse.getBody());
  }

  @Override
  public JobDTO getJobById(Long jobId) {
    return convertToDTO(jobDao.findById(jobId).orElse(null));
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
