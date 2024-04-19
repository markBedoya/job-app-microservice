package org.example.job.job;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.example.job.external.Company;
import org.example.job.job.dto.JobWithCompanyDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

  private JobDao jobDao;

  @Override
  public List<JobWithCompanyDTO> getAllJobs() {
    List<Job> jobs = jobDao.findAll();
    return jobs.stream().map(this::convertToDTO).toList();
  }

  private JobWithCompanyDTO convertToDTO (Job job) {
    JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();

    RestTemplate restTemplate = new RestTemplate();
    Company company = restTemplate.getForObject(
        "http://localhost:8081/companies/" + job.getCompanyId(), Company.class);

    jobWithCompanyDTO.setJob(job);
    jobWithCompanyDTO.setCompany(company);
    return jobWithCompanyDTO;
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
