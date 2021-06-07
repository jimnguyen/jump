package com.cognixia.jump.assignment;

import java.util.*;

public class EmployeeManagement {

    public static void main(String[] args) {
        try {
            start();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    public static void start() {
        System.out.println("Welcome to the Employee Management System!");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Jim Nguyen", 100000, "SWE"));
        employees.add(new Employee("Jimmy Two Tones", 999900, "ART"));
        int option = 0;
        while (option != 5) {
            option = menu();
            switch (option) {
                case 1 -> displayEmployeeInformation(employees);
                case 2 -> addEmployeePrompt(employees);
                case 3 -> updateEmployee(employees);
                case 4 -> removeEmployeePrompt(employees);
                case 5 -> {
                    System.out.println("\nYou are now exiting the Employee Management System");
                    System.out.println("See you again!");
                }
                default -> System.out.println("Please select a valid option (1-5)\n");
            }
        }
    }

    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select an option below:");
        System.out.println("1. Display Employee Information");
        System.out.println("2. Add New Employee");
        System.out.println("3. Update Employee Information");
        System.out.println("4. Remove Employee");
        System.out.println("5. Exit");
        System.out.println("Enter your option here (1-5): ");
        return scanner.nextInt();
    }

    public static void addEmployeePrompt(List<Employee> employees) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nYou will now be entering in information for a new employee...\n");
            System.out.println("Please enter a name (FirstName LastName) and hit enter: ");
            String name = scan.nextLine();
            if (name.split(" ").length < 2) {
                throw new InvalidEmployeeInfoException();
            }
            System.out.println("Great! Nice to meet you " + name + "!\n");
            System.out.println("Now please enter a salary and hit enter: ");
            int salary = scan.nextInt();
            scan.nextLine();
            System.out.println("Wonderful! You will be making [" + salary + "] dollars!\n");
            System.out.println("Lastly, which department will [" + name + "] be joining?");
            System.out.println("(Hit enter when finished)");
            String department = scan.nextLine();
            System.out.println("The [" + department + "] department! How exciting!\n");
            System.out.println(name + " has been entered into the system!\n");
            Employee employee = new Employee(name, salary, department);
            addEmployee(employees, employee);
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid information\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeEmployeePrompt(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("There are no employees to be removed\n");
            return;
        }
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nYou will now be removing an employee from the system...\n");
            System.out.println("Please select an employee by id to remove below:");
            for (Employee e : employees) {
                System.out.println("ID : [" + e.getEmployeeId() + "] - " + e.getName());
            }
            int employeeId = scanner.nextInt();
            String name = employees.stream().filter(e -> e.getEmployeeId() == employeeId).findFirst().orElseThrow().getName();
            removeEmployee(employees, employeeId);
            System.out.println("\n" + name + " has been successfully removed from the system...\n");
        } catch (InputMismatchException e) {
            System.out.println("Please provide a valid id number\n");
        } catch (NoSuchElementException e) {
            System.out.println("Please provide an existing id number\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addEmployee(List<Employee> employees, Employee employee) {
        employees.add(employee);
    }

    public static List<Employee> updateEmployee(List<Employee> employees) {
        List<Employee> updatedEmployeeList = new ArrayList<>();
        return updatedEmployeeList;
    }

    public static void removeEmployee(List<Employee> employees, int employeeId) {
        employees.removeIf(e -> e.getEmployeeId() == employeeId);
    }

    public static void displayEmployeeInformation(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("There are no employees to display\n");
            return;
        }
        System.out.println(employees + "\n");
    }
}
