package com.zongrui.companyservice.company;

import com.zongrui.companyservice.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
    void updateCompanyRating(ReviewMessage reviewMessage);
}
