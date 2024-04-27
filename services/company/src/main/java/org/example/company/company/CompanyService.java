package org.example.company.company;

import java.util.List;
import java.util.Optional;
import org.example.company.company.dto.ReviewMessage;

public interface CompanyService {
  List<Company> getAllCompanies();
  Optional<Company> getCompanyById(Long companyId);
  void createCompany(Company company);
  boolean updateCompanyById(Long companyId, Company updatedCompany);
  boolean deleteCompanyById(Long companyId);
  public void updateCompanyRating(ReviewMessage reviewMessage);

}
