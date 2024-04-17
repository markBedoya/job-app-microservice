package org.example.job.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDao extends JpaRepository<Job, Long> {

}
