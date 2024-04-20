package org.example.job.job.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {


  @LoadBalanced
  @Bean
  //This is now replaced with OpenFeign, but I'm leaving here for reference
  //OpenFeign also has built in load balancing.
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
