package org.example.company.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jobapplicationdemo.job.Job;
import org.example.jobapplicationdemo.review.Review;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
  @JsonIgnore
  private List<Job> jobs;
  @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
  private List<Review> reviews;
}
