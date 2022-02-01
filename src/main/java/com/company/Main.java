package com.company;

import com.company.Company.Company;
import com.company.Company.CompanyDAOImpl;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    com.company.DatabaseRepository db = com.company.DatabaseRepository.getInstance(args[1]);
        db.createDatabase();
        Scanner scanner = new Scanner(System.in);
        Boolean isRunning = true;
    while (isRunning) {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
        String input = scanner.nextLine();
        System.out.println();
        if ("1".equals(input)) {
        String choice = "";
            while(!"0".equals(choice)) {
                System.out.println("1.Company List");
                System.out.println("2.Create a company");
                System.out.println("0.Back");
                choice = scanner.nextLine();
                if ("1".equals(choice)) {
                    CompanyDAOImpl companyDAO = new CompanyDAOImpl();
                    List<Company> companies = companyDAO.getAllCompanies();
                    if (companies.isEmpty()) {
                        System.out.println("The company list is empty!");
                    } else {
                        for(int i = 0; i < companies.size(); i++) {
                            System.out.println((i + 1) + ". " + companies.get(i).getName());
                        }
                    }
                } else if ("2".equals(choice)) {
                    System.out.println("Enter the company name:");
                    String name = scanner.nextLine();
                    CompanyDAOImpl companyDAO = new CompanyDAOImpl();
                    companyDAO.addCompany(name);
                }
                System.out.println();
            }
        }
        if ("0".equals(input)) {
            isRunning = false;
        }
        }
    }
}
