package org.example.job.job;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/jobs")
public class JobController {

  private JobService jobService;

  @GetMapping
  public ResponseEntity<List<Job>> getAllJobs() {
    return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
  }

  @GetMapping("/{jobId}")
  public ResponseEntity<Job> getJobById(@PathVariable Long jobId) {
    Optional<Job> job = jobService.getJobById(jobId);
    return job.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<String> createJob(@RequestBody Job job) {
    //As expected - Throws 500 internal server error if company doesn't exist.
    jobService.createJob(job);
    return new ResponseEntity<>("Job added successfully.", HttpStatus.CREATED);
  }

  @PutMapping("/{jobId}")
  public ResponseEntity<String> updateJobById(@PathVariable Long jobId, @RequestBody Job updatedJob) {
    return jobService.updateJobById(jobId, updatedJob) ?
        new ResponseEntity<>("Job updated successfully.", HttpStatus.OK) :
        new ResponseEntity<>("Job not found.", HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{jobId}")
  public ResponseEntity<String> deleteJobById(@PathVariable Long jobId) {
    return jobService.deleteJobById(jobId) ?
        new ResponseEntity<>("Job deleted successfully.", HttpStatus.OK) :
        new ResponseEntity<>("Job not found.",HttpStatus.NOT_FOUND);
  }

}
