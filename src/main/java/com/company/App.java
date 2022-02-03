package com.company;

import com.company.Car.Car;
import com.company.Car.CarDAOImpl;
import com.company.Company.Company;
import com.company.Company.CompanyDAOImpl;

import java.util.List;
import java.util.Scanner;

public class App {
    private static CompanyDAOImpl companyDAO;
    private static CarDAOImpl carDAO;
    private static Scanner scanner;

    public App () {
        this.companyDAO = new CompanyDAOImpl();
        this.carDAO = new CarDAOImpl();
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
        db.createCarDatabase();
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
                        companyMenu();
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

    private static void companyMenu() {
        boolean isRunning = true;
        List<Company> companies = companyDAO.getAllCompanies();
        while(isRunning) {
            displayCompanies(companies);
            System.out.println("0. Back");
            if(companies.isEmpty()) {
                isRunning = false;
                break;
            }
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                isRunning = false;
                break;
            }
            runCompanyChoices(companies.get(choice - 1));
            break;
        }
    }

    private static void runCompanyChoices(Company company) {
        boolean isRunning = true;
        while(isRunning) {
            displayCompanyChoices(company);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    displayCarList(company.getID());
                    break;
                case "2":
                    createACar(company.getID());
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Selection");
            }
        }
    }

    private static void createACar(int company_id) {
        System.out.println("Enter the car name:");
        String carName = scanner.nextLine();
        carDAO.addCar(carName, company_id);
    }

    private static void displayCarList(int company_id) {
        List<Car> cars = carDAO.getAllCars(company_id);
        if(cars.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            System.out.println("Car list:");
            for(int i = 0; i < cars.size(); i++) {
                System.out.println((i + 1) + ". " + cars.get(i).getName());
            }
        }
    }

    private static void displayCompanyChoices(Company company) {
        System.out.println("'" + company.getName() + "' company");
        System.out.println("1. Car list\n" +
                "2. Create a car\n" +
                "0. Back");
    }

    private static void displayCompanies(List<Company> companies) {
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            System.out.println("Choose the company:");
            for(int i = 0; i < companies.size(); i++) {
                System.out.println((i + 1) + ". " + companies.get(i).getName());
            }
        }
    }
}
