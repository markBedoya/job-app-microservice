package org.example.company.company;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private CompanyDao companyDao;

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
    if (companyOptional.isPresent()) {
      Company companyToUpdate = companyOptional.get();
      companyToUpdate.setName(updatedCompany.getName());
      companyToUpdate.setDescription(updatedCompany.getDescription());
      companyToUpdate.setJobs(updatedCompany.getJobs());
      companyToUpdate.setReviews(updatedCompany.getReviews());
      companyDao.save(companyToUpdate);
      return true;
    }
    return false;
  }

  @Override
  public boolean deleteCompanyById(Long companyId) {
    if (!companyDao.existsById(companyId)) {
      return false;
    }
    companyDao.deleteById(companyId);
    return true;
  }

}
