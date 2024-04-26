package org.example.job.job.clients;

import org.example.job.job.externalpojo.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company")
public interface CompanyClient {

  @GetMapping("/companies/{companyId}")
  Company getCompany(@PathVariable("companyId") Long companyId);
}
