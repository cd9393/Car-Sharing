package com.company;

import com.company.Company.Company;
import com.company.Company.CompanyDAOImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    com.company.DatabaseRepository db = com.company.DatabaseRepository.getInstance(args[1]);
        db.createDatabase();
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        companyDAO.addCompany("Asda");
        companyDAO.addCompany("Tesco");
        companyDAO.addCompany("Sainsburys");
        List<Company> results = companyDAO.getAllCompanies();
        for (Company company : results) {
            System.out.println("ID = " + company.getID() + " Name = " + company.getName());
        }
    }
}
