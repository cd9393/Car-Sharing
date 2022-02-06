package com.company.Company;

import java.util.List;

public interface CompanyDAO {
    public List<Company> getAllCompanies();
    public void addCompany(String name);
    public Company getCompanyByID(int id);
}
