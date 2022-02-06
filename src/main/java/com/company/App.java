package com.company;

import com.company.Car.Car;
import com.company.Car.CarDAOImpl;
import com.company.Company.Company;
import com.company.Company.CompanyDAOImpl;
import com.company.Customer.Customer;
import com.company.Customer.CustomerDAOImpl;

import java.util.List;
import java.util.Scanner;

public class App {
    private static CompanyDAOImpl companyDAO;
    private static CarDAOImpl carDAO;
    private static CustomerDAOImpl customerDAO;
    private static Scanner scanner;

    public App () {
        this.companyDAO = new CompanyDAOImpl();
        this.carDAO = new CarDAOImpl();
        this.customerDAO = new CustomerDAOImpl();
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
        db.createCustomerDatabase();
    }

    private static void displayLoginMenu() {
            System.out.println("1. Log in as a manager\n" +
                    "2. Log in as a customer\n" +
                    "3. Create a customer\n" +
                    "0. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    managerMenu();
                    break;
                case 2:
                    customersMenu();
                    break;
                case 3:
                    createCustomer();
                    break;
                case 0:
                    System.exit(8);
                    break;
                default:
                    System.out.println("Invalid selection");
            }
    }

    private static void customersMenu() {
        List<Customer> customers = customerDAO.getAllCustomers();
        displayCustomerList(customers);
    }

    private static void displayCustomerList(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("The customer list is empty!");
        } else {
            System.out.println("Customer list:");
            for (int i = 0; i < customers.size(); i++) {
                System.out.println(i + 1 + ". " + customers.get(i).getName());
            }
            System.out.println("0. Back");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0) {
                customerChoices(customers.get(choice - 1));
            }
        }
    }

    private static void customerChoices(Customer customer) {
        boolean isRunning = true;
        while(isRunning) {
            Customer customerRefreshed = customerDAO.getCustomerByID(customer.getID());
            System.out.println("1. Rent a car\n" +
                    "2. Return a rented car\n" +
                    "3. My rented car\n" +
                    "0. Back");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    rentACar(customerRefreshed);
                    break;
                case 2:
                    returnRentedCar(customerRefreshed);
                    break;
                case 3:
                    displayRentedCar(customerRefreshed.getRentedCarId());
                    break;
                default:
                    isRunning = false;
                    break;
            }
        }
    }

    private static void rentACar(Customer customer) {
        if (customer.hasRentedCar()){
            System.out.println("You've already rented a car!");
        } else {
            List<Company> companies = companyDAO.getAllCompanies();
            boolean isRunning = true;
            while(isRunning) {
                displayCompanies(companies);
                if(companies.isEmpty()) {
                    isRunning = false;
                    break;
                }else {
                    int choice = Integer.parseInt(scanner.nextLine());
                    if(choice == 0) {
                        isRunning = false;
                        break;
                    }
                    List<Car> cars = carDAO.getAllCars(companies.get(choice - 1).getID());
                    displayCarList(companies.get(choice - 1).getID());
                    if (cars.isEmpty()) {
                        isRunning = false;
                        break;
                    }
                    int carChoice = Integer.parseInt(scanner.nextLine());
                    if(carChoice == 0) {
                        isRunning = false;
                        break;
                    }
                    customerDAO.rentACar(cars.get(carChoice - 1).getID(), customer.getID());
                    System.out.println("You rented " + cars.get(carChoice - 1).getName());
                    isRunning = false;
                    break;
                }
            }
        }
    }

    private static void returnRentedCar(Customer customer) {
        if (customer.hasRentedCar()) {
            customerDAO.returnRentedCar(customer.getID());
            System.out.println("You've returned a rented car!");
        } else {
            System.out.println("You didn't rent a car!");
        }
    }

    private static void displayRentedCar(int rentedCarId) {
        Car car = carDAO.getCarByID(rentedCarId);
        if (car == null) {
            System.out.println("You didn't rent a car!");
        } else {
            Company company = companyDAO.getCompanyByID(car.getCompany_id());
            System.out.println("Your rented car:");
            System.out.println(car.getName());
            System.out.println("Company:");
            System.out.println(company.getName());

        }

    }

    private static void createCustomer() {
        System.out.println("Enter the customer name:");
        String name = scanner.nextLine();
        customerDAO.addCustomer(name);
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
            System.out.println("0. Back");
        }
    }
}
