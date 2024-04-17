package org.example.company.company;

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
@RequestMapping("/companies")
public class CompanyController {

  private CompanyService companyService;

  @GetMapping
  public ResponseEntity<List<Company>> getAllCompanies() {
    return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
  }

  @GetMapping("/{companyId}")
  public ResponseEntity<Company> getCompanyById(@PathVariable Long companyId) {
    Optional<Company> company = companyService.getCompanyById(companyId);
    return company.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<String> createCompany(@RequestBody Company company) {
    companyService.createCompany(company);
    return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
  }

  @PutMapping("/{companyId}")
  public ResponseEntity<String> updateCompanyById(@PathVariable Long companyId,
      @RequestBody Company updatedCompany) {
    return companyService.updateCompanyById(companyId, updatedCompany) ?
        new ResponseEntity<>("Company updated successfully.", HttpStatus.OK) :
        new ResponseEntity<>("Company not found.", HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{companyId}")
  public ResponseEntity<String> deleteCompanyById(@PathVariable Long companyId) {
    return companyService.deleteCompanyById(companyId) ?
        new ResponseEntity<>("Company deleted successfully.", HttpStatus.OK) :
        new ResponseEntity<>("Company not found.", HttpStatus.NOT_FOUND);
  }
}