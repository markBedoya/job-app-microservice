package org.example.company.company;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.company.company.clients.ReviewClient;
import org.example.company.company.dto.ReviewMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class CompanyServiceImpl implements CompanyService {

  private CompanyDao companyDao;
  private ReviewClient reviewClient;

  @Override
  public List<Company> getAllCompanies() {
    return companyDao.findAll();
  }

  @Override
  public Optional<Company> getCompanyById(Long companyId) {
    return companyDao.findById(companyId);
  }

  @Override
  public void createCompany(Company company) {
    companyDao.save(company);
  }

  @Override
  public boolean updateCompanyById(Long companyId, Company updatedCompany) {
    Optional<Company> companyOptional = companyDao.findById(companyId);
    if (companyOptional.isEmpty()) {
      return false;
    }
    Company companyToUpdate = companyOptional.get();
    companyToUpdate.setName(updatedCompany.getName());
    companyToUpdate.setDescription(updatedCompany.getDescription());
    companyDao.save(companyToUpdate);
    return true;
  }

  @Override
  public boolean deleteCompanyById(Long companyId) {
    if (!companyDao.existsById(companyId)) {
      return false;
    }
    companyDao.deleteById(companyId);
    return true;
  }

  @Override
  public void updateCompanyRating(ReviewMessage reviewMessage) {
    Company company = companyDao.findById(reviewMessage.getCompanyId()).orElseThrow(() ->
        new NotFoundException("company not found with companyId=" +reviewMessage.getCompanyId()));
    company.setAverageRating(reviewClient
            .getAvgCompanyRating(reviewMessage.getCompanyId()));
    companyDao.save(company);
  }

}
