package com.cognixia.jump.assignment;

public class Department {
    private static int idCounter = 1;
    private String name;
    private double budget;
    private final int departmentId;

    public Department(String name, int budget) {
        this.name = name;
        this.budget = (budget * Math.random()) * 1000000;
        this.departmentId = idCounter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String budgetStr = String.format("%.2f", this.getBudget());
        return "id: " + this.getDepartmentId() + " | Name: " + this.getName() + " | Budget: " + budgetStr;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
