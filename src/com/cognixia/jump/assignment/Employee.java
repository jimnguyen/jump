package com.cognixia.jump.assignment;

import java.time.LocalDate;

public class Employee {
    private static int idCounter = 1;
    private String name;
    private final int employeeId;
    private final LocalDate startDate;
    private int salary;
    private String department;

    public Employee(String name, int salary, String department) {
        super();
        this.name = name;
        this.employeeId = idCounter++;
        this.salary = salary;
        this.department = department;
        this.startDate = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "Employee [ id = " + employeeId + ", name = " + name + ", salary = " + salary + ", department = " + department + "]";
    }
}
