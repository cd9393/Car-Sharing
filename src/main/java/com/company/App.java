package com.company;

import com.company.Company.Company;
import com.company.Company.CompanyDAOImpl;

import java.util.List;
import java.util.Scanner;

public class App {
    private static CompanyDAOImpl companyDAO;
    private static Scanner scanner;

    public App () {
        this.companyDAO = new CompanyDAOImpl();
        this.scanner = new Scanner(System.in);
    }

    public static void start(String dbName) {
        createDB(dbName);

        while (true) {
            displayLoginMenu();
        }
    }

    public static void createDB(String dbName){
        com.company.DatabaseRepository db = com.company.DatabaseRepository.getInstance(dbName);
        db.createDatabase();
    }

    private static void displayLoginMenu() {
            System.out.println(                "1. Log in as a manager\n" +
                    "0. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    managerMenu();
                    break;
                case 0:
                    System.exit(8);
                    break;
                default:
                    System.out.println("Invalid selection");
            }
    }

    private static void managerMenu() {
        boolean isRunning = true;
            while (isRunning) {
                System.out.println("" +
                        "1. Company list\n" +
                        "2. Create a company\n" +
                        "0. Back");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayCompanies();
                        break;
                    case 2:
                        createCompany();
                        break;
                    case 0:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid selection!");
                }
                System.out.println();
            }
    }

    private static void createCompany() {
            System.out.println("Enter the company name:");
            String name = scanner.nextLine();
            companyDAO.addCompany(name);
    }

    private static void displayCompanies() {
        List<Company> companies = companyDAO.getAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            for(int i = 0; i < companies.size(); i++) {
                System.out.println((i + 1) + ". " + companies.get(i).getName());
            }
        }
    }
}
