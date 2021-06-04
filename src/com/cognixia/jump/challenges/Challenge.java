package com.cognixia.jump.challenges;

import java.util.*;
import java.util.stream.Collectors;

public class Challenge {

    public static void main(String[] args) {
        System.out.println("\nStrings Challenge:\n");

        List<String> strings = new ArrayList<>();
        strings.add("Red");
        strings.add("Yellow");
        strings.add("Green");
        strings.add("Yellow");
        strings.add("Blue");
        printDups(strings);
        System.out.println("List with duplicates:\n" + strings);
        System.out.println("List without duplicates:\n" + printDups2(strings));

        System.out.println("\nMap Challenge:\n");

        Map<String, Double> students = new HashMap<>();
        students.put("student1", 75.5);
        students.put("student2", 80.5);
        students.put("student3", 85.5);
        students.put("student6", 15.5);
        students.put("student7", 25.5);
        students.put("student4", 90.5);
        students.put("student5", 95.5);

        System.out.println("Highest scoring grade: [" + getStudent(students, highestScoring(students)) + ", "
                + highestScoring(students) + "]");
        System.out.println("Lowest scoring grade: [" + getStudent(students, lowestScoring(students)) + ", "
                + lowestScoring(students) + "]");
        System.out.format("Median grade: %.2f%n", medianGrade(students));
        System.out.format("Average grade: %.2f%n%n", averageGrade(students));
    }

    static Set<String> printDups(List<String> strings) {
        Set<String> uniqueStrings = new HashSet<>();
        var hasDups = false;

        for (String s : strings) {
            if (uniqueStrings.contains(s)) {
                hasDups = true;
                System.out.println(s);
            } else {
                uniqueStrings.add(s);
            }
        }

        if (hasDups) {
            System.out.println("This list of strings has duplicates");
        }

        return uniqueStrings;
    }

    static List<String> printDups2(List<String> strings) {
        return strings.stream().distinct().collect(Collectors.toList());
    }

    static String getStudent(Map<String, Double> students, double grade) {
        var student = "";

        for (Map.Entry<String, Double> stud : students.entrySet()) {
            if (stud.getValue() == grade) {
                student = stud.getKey();
            }
        }

        return student;
    }

    static double highestScoring(Map<String, Double> students) {
        double highest = 0;

        for (Map.Entry<String, Double> student : students.entrySet()) {
            highest = Math.max(student.getValue(), highest);
        }

        return highest;
    }

    static double lowestScoring(Map<String, Double> students) {
        double lowest = 101;

        for (Map.Entry<String, Double> student : students.entrySet()) {
            lowest = Math.min(student.getValue(), lowest);
        }

        return lowest;
    }

    static double medianGrade(Map<String, Double> students) {
        List<Double> grades = new ArrayList<>();

        for (Map.Entry<String, Double> student : students.entrySet()) {
            grades.add(student.getValue());
        }

        Collections.sort(grades);

        return grades.get(grades.size() / 2);
    }

    static double averageGrade(Map<String, Double> students) {
        double total = 0;

        for (Map.Entry<String, Double> student : students.entrySet()) {
            total += student.getValue();
        }

        return total / students.size();
    }
}