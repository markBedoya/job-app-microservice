package org.example.job.job.dao;

import org.example.job.job.pojo.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDao extends JpaRepository<Job, Long> {

}
