package com.cognixia.jump.assignment;

import java.util.*;

public class EmployeeManagement {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        List<Department> departments = new ArrayList<>();

        departments.add(new Department("Technology", 25));
        departments.add(new Department("Accounting", 15));
        departments.add(new Department("Human Resources", 10));
        departments.add(new Department("Management", 50));

        try {
            start(employees, departments);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid option");
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }

    public static void start(List<Employee> employees, List<Department> departments) {
        System.out.println("Welcome to the Employee Management System!");
        int option = 0;
        while (option != 5) {
            option = menu();
            switch (option) {
                case 1 -> displayEmployeeInformation(employees);
                case 2 -> addEmployeePrompt(employees, departments);
                case 3 -> updateEmployeePrompt(employees, departments);
                case 4 -> removeEmployeePrompt(employees);
                case 5 -> manageDepartmentsPrompt(departments, employees);
                case 6 -> {
                    System.out.println("\nYou are now exiting the Employee Management System");
                    System.out.println("See you again!");
                }
                default -> System.out.println("Please select a valid option (1-6)\n");
            }
        }
    }

    public static int menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select an option below:");
        System.out.println("------------------------------");
        System.out.println("1. Display Employee Information");
        System.out.println("2. Add New Employee");
        System.out.println("3. Update Employee Information");
        System.out.println("4. Remove Employee");
        System.out.println("5. Manage Departments");
        System.out.println("6. Exit");
        System.out.println("------------------------------");
        System.out.print("Enter option here (1-6): ");

        return scanner.nextInt();
    }

    public static int departmentMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nDepartment Management Menu");
        System.out.println("Please select an action");
        System.out.println("------------------------------");
        System.out.println("1. Display Department Information");
        System.out.println("2. Add New Department");
        System.out.println("3. Update Department Information");
        System.out.println("4. Remove Department");
        System.out.println("5. Exit Department Menu");
        System.out.println("------------------------------");
        System.out.print("Enter option here (1-5): ");

        return scanner.nextInt();
    }

    public static void addEmployeePrompt(List<Employee> employees, List<Department> departments) {

        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nYou will now be entering in information for a new employee...\n");
            System.out.print("Please enter a name (FirstName LastName) and hit enter: ");

            // Name information
            String name = scan.nextLine();

            // Handle the case where first and last name are not entered
            if (name.split(" ").length < 2) {
                throw new InvalidEmployeeNameException();
            }

            System.out.println("Great! You've entered in [" + name + "]!\n");
            System.out.print("Now please enter a salary and hit enter: ");

            // Salary information
            int salary = scan.nextInt();
            scan.nextLine();
            System.out.println("Wonderful! You will be making [" + salary + "] dollars!\n");
            System.out.println("Lastly, which department will [" + name + "] be joining?");
            displayDepartments(departments);
            System.out.print("Choose here: ");

            // Department information
            int departmentId = scan.nextInt();
            scan.nextLine();
            Department department = selectDepartment(departments, departmentId);

            System.out.println("The [" + department.getName() + "] department! How exciting!\n");
            System.out.println(name + " has been entered into the system!\n");

            // Add employee
            Employee employee = new Employee(name, salary, department.getName());
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

            displayEmployeeInformation(employees);

            System.out.print("Enter id here: ");
            int employeeId = scanner.nextInt();
            String name = employees.stream().filter(e -> e.getEmployeeId() == employeeId).findFirst().orElseThrow().getName();
            removeEmployee(employees, employeeId);
            System.out.println("\n[" + name + "] has been successfully removed from the system...\n");
        } catch (InputMismatchException e) {
            System.out.println("Please provide a valid id number\n");
        } catch (NoSuchElementException e) {
            System.out.println("Please provide an existing id number\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateEmployeePrompt(List<Employee> employees, List<Department> departments) {
        if (employees.isEmpty()) {
            System.out.println("There are no employees to update\n");
            return;
        }

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Please select an employee to update by ID");

            displayEmployeeInformation(employees);

            System.out.print("Enter id here: ");
            int selection = scanner.nextInt();

            Employee employee = selectEmployee(employees, selection);
            updateEmployee(employee, departments);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid option\n");
        } catch (NoSuchElementException e) {
            System.out.println("Please enter a valid id\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addEmployee(List<Employee> employees, Employee employee) {
        employees.add(employee);
    }

    public static Employee selectEmployee(List<Employee> employees, int employeeId) {
        return employees.stream().filter(e -> e.getEmployeeId() == employeeId).findFirst().orElseThrow();
    }

    public static void updateEmployee(Employee employee, List<Department> departments) throws InvalidEmployeeNameException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease select an action to perform");
        System.out.println("1. Update name");
        System.out.println("2. Update salary");
        System.out.println("3. Update department\n");
        System.out.print("Enter option here: ");

        int selection = scanner.nextInt();

        switch (selection) {
            case 1 -> updateName(employee);
            case 2 -> updateSalary(employee);
            case 3 -> updateEmployeeDepartment(employee, departments);
            default -> System.out.println("Select a valid option please");
        }
    }

    public static void updateName(Employee employee) throws InvalidEmployeeNameException {
        Scanner scanner = new Scanner(System.in);
        String oldName = employee.getName();
        System.out.print("Enter new name for [" + oldName + "] (FirstName LastName): ");
        String updatedName = scanner.nextLine();

        if (updatedName.split(" ").length < 2) {
            throw new InvalidEmployeeNameException();
        }

        employee.setName(updatedName);
        System.out.println("You've successfully updated [" + oldName + "] to [" + updatedName + "]!\n");
    }

    public static void updateSalary(Employee employee) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new salary for [" + employee.getName() + "]: ");
        int updatedSalary = scanner.nextInt();
        employee.setSalary(updatedSalary);
        System.out.println("You've successfully updated [" + employee.getName() + "]'s salary to [" + updatedSalary + "]!\n");
    }

    public static void removeEmployee(List<Employee> employees, int employeeId) {
        employees.removeIf(e -> e.getEmployeeId() == employeeId);
    }

    public static void displayEmployeeInformation(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("There are no employees to display\n");
            return;
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("List of Employees:");
        employees.forEach(System.out::println);
        System.out.println("-----------------------------------------------------\n");
    }

    public static void manageDepartmentsPrompt(List<Department> departments, List<Employee> employees) {
        try {
            manageDepartments(departments, employees);
        } catch (InputMismatchException e) {
            System.out.println("Enter a valid number please");
            manageDepartmentsPrompt(departments, employees);
        }
    }

    public static void manageDepartments(List<Department> departments, List<Employee> employees) {
        int selection = 0;
        while (selection != 5) {
            selection = departmentMenu();
            switch (selection) {
                case 1 -> displayDepartments(departments);
                case 2 -> addNewDepartmentPrompt(departments);
                case 3 -> updateDepartmentPrompt(departments);
                case 4 -> removeDepartmentPrompt(departments, employees);
                case 5 -> {
                    System.out.println();
                    start(employees, departments);
                }
                default -> System.out.println("Please choose a valid option");
            }
        }
    }

    private static boolean checkIfContainsEmployees(List<Employee> employees, String departmentName) {
        Set<String> employeeSet = new HashSet<>();
        for (Employee e : employees) {
            employeeSet.add(e.getDepartment());
        }
        return employeeSet.contains(departmentName);
    }

    private static void removeDepartmentPrompt(List<Department> departments, List<Employee> employees) {
        if (departments.isEmpty()) {
            System.out.println("There are no departments to be removed\n");
            return;
        }

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nYou will now be removing a department from the system...\n");
            System.out.println("Please select a department by id to remove below:");

            displayDepartments(departments);

            System.out.print("Enter id here: ");
            int departmentId = scanner.nextInt();
            String name = departments.stream().filter(d -> d.getDepartmentId() == departmentId).findFirst().orElseThrow().getName();

            if (checkIfContainsEmployees(employees, name)) {
                System.out.println("Cannot remove department with active employees!");
            } else {
                removeDepartment(departments, departmentId);
                System.out.println("\n[" + name + "] has been successfully removed from the system...\n");
            }

        } catch (InputMismatchException e) {
            System.out.println("Please provide a valid id number\n");
        } catch (NoSuchElementException e) {
            System.out.println("Please provide an existing id number\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removeDepartment(List<Department> departments, int departmentId) {
        departments.removeIf(e -> e.getDepartmentId() == departmentId);
    }

    public static void displayDepartments(List<Department> departments) {
        System.out.println("-----------------------------------------------------");
        System.out.println("List of Departments:");
        departments.forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
    }

    public static void addNewDepartmentPrompt(List<Department> departments) {

        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nYou will now be entering in information for a new department...\n");
            System.out.print("Please enter a name and hit enter: ");

            // Name information
            String name = scan.nextLine();
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

            System.out.println("Great! You've entered in [" + name + "]!\n");
            System.out.print("Now please enter a budget multiplier between [1-50] and hit enter: ");

            // Budget information
            int budget = scan.nextInt();
            scan.nextLine();
            System.out.println("Wonderful! The department has a budget multiplier of [" + budget + "] dollars!\n");
            System.out.println("The [" + name + "] department has been entered into the system!\n");

            // Add department
            Department department = new Department(name, budget);
            addDepartment(departments, department);

        } catch (InputMismatchException e) {
            System.out.println("Please enter valid information\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDepartment(List<Department> departments, Department department) {
        departments.add(department);
    }

    public static void updateDepartmentPrompt(List<Department> departments) {
        if (departments.isEmpty()) {
            System.out.println("There are no departments to update\n");
            return;
        }

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease select a department to update");
            displayDepartments(departments);

            System.out.print("Enter id here: ");
            int selection = scanner.nextInt();

            Department department = selectDepartment(departments, selection);
            updateDepartment(department);

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid option");
        }
    }

    private static void updateDepartment(Department department) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease select an action to perform");
        System.out.println("1. Update department name");
        System.out.println("2. Update budget");
        System.out.print("Enter option here: ");

        int selection = scanner.nextInt();

        switch (selection) {
            case 1 -> updateDepartmentName(department);
            case 2 -> updateDepartmentBudget(department);
            default -> System.out.println("Select a valid option please");
        }
    }

    private static void updateDepartmentBudget(Department department) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a new budget modifier [1-50] for [" + department.getName() + "]: ");
        int updatedBudget = scanner.nextInt();
        if (updatedBudget > 50 || updatedBudget < 1) {
            System.out.println("Enter a valid number");
            return;
        }
        department.setBudget(updatedBudget);
        System.out.println("You've successfully updated [" + department.getName() + "]'s budget to [" + department.getBudget() + "]!\n");
    }

    private static void updateDepartmentName(Department department) {
        Scanner scanner = new Scanner(System.in);
        String oldName = department.getName();
        System.out.print("Enter new department name for [" + oldName + "] : ");
        String updatedName = scanner.nextLine();
        updatedName = updatedName.substring(0, 1).toUpperCase() + updatedName.substring(1).toLowerCase();
        department.setName(updatedName);
        System.out.println("You've successfully updated [" + oldName + "] to [" + updatedName + "]!\n");
    }

    public static Department selectDepartment(List<Department> departments, int departmentId) {
        return departments.stream().filter(d -> d.getDepartmentId() == departmentId).findFirst().orElseThrow();
    }

    private static void updateEmployeeDepartment(Employee employee, List<Department> departments) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a new department for [" + employee.getName() + "]: ");
        displayDepartments(departments);
        System.out.print("Enter id here: ");
        int departmentId = scanner.nextInt();
        Department department = selectDepartment(departments, departmentId);
        employee.setDepartment(department.getName());
        System.out.println("You've successfully updated [" + employee.getName() + "]'s department to [" + department.getName() + "]!\n");
    }
}
