package com.cognixia.jump.streams;

import java.util.*;
import java.util.stream.Stream;

public class StreamsRunner {
    public static void main(String[] args) {
        int[] integer = {2,4,6,2,4, 12, 3, 11};
        Integer[] integers = Arrays.stream(integer)
                .boxed()
                .toArray(Integer[]::new);

        List<Integer> numbers = Arrays.asList(integers);
        System.out.println(numbers);
        numbers.forEach(System.out::println);

        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Numbers sum is: " + sum);

        int biggest = numbers.stream()
                .reduce((num1, num2) -> num1 > num2 ? num1 : num2).orElseThrow();

        System.out.println("The largest number in numbers is: " + biggest);

        double avgInt = Stream.of(integers).parallel()
                .mapToDouble(Integer::new)
                .average().orElseThrow();

        System.out.println("The average of numbers list was: " + avgInt);

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Matthew", 10, "JUMP"));
        employees.add(new Employee("Orquidia", 10, "JUMP"));
        employees.add(new Employee("Sumi", 10000000, "NASA"));
        employees.add(new Employee("Jai", 500000, "Tesla"));
        employees.add(new Employee("Nikita", 600000, "Apple"));
        employees.add(new Employee("Daven", 10_000_000, "New York City Dept."));
        employees.add(new Employee("Sabeet", 5, "The Zoo"));

        // Employee with lowest salary
        Employee lowestSal = employees.stream()
                .reduce((e1,e2) -> e1.getSalary() < e2.getSalary() ? e1 : e2)
                .orElseThrow();

        //Optional
        Optional<Employee> tom = employees.stream()
                .filter(e -> e.getName().equals("Jai"))
                .findFirst();

        if(tom.isPresent()) {
            System.out.println(tom.get());
        }else {
            System.out.println("Tom not found.");
        }

        // Coding Challenges
        System.out.println("The highest paid employee is: " + getHighestPaid(employees));
        System.out.println("These are all employees that have 6 figure salaries: " + sixFigureSalaries(employees));
        System.out.println("This is the average salary: " + getAverageSalary(employees));
        System.out.println("These are all employees that have less than 100,000 salaries: " + under100k(employees));
        System.out.println("-------------------");
        System.out.println(employees);
        System.out.println("-------------------");
        removeEmployee(employees, "Daven");
        System.out.println(employees);
    }

    public static Employee getHighestPaid(List<Employee> employees) {
        return employees.stream().reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2).orElseThrow();
    }

    static List<Employee> sixFigureSalaries(List<Employee> employees) {
        return employees.stream().filter(e -> e.getSalary() >= 100000).toList();
    }

    static Double getAverageSalary(List<Employee> employees) {
        return employees.stream().parallel().mapToDouble(Employee::getSalary).average().orElseThrow();
    }

    static List<Employee> under100k(List<Employee> employees) {
        return employees.stream().filter(e -> e.getSalary() < 100000).toList();
    }

    public static boolean removeEmployee(List<Employee> employees, String name) {
        return employees.removeIf(e -> e.getName().equals("Daven"));
    }
}
