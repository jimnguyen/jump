package com.cognixia.jump.homework.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Homework1 {
    public static void main(String[] args) {
        String[] strs = {"one", "two", "three"};
        List<String> list = new ArrayList<>(Arrays.stream(strs).toList());
//        String valid = "";
//        while(!valid.equals("Valid phone number")) {
//            System.out.print("Enter a phone number: ");
//            valid = getNumber();
//            System.out.println(valid);
//        }
    }

    static String getNumber() {
        Scanner scan = new Scanner(System.in);
        String number = scan.nextLine();

        if (!checkNumber(number)) {
            scan.close();
            return "Invalid phone number";
        }
        scan.close();
        return "Valid phone number";
    }

    static boolean checkNumber(String number) {
        if (number.length() >= 10) {
            number = number.replaceAll(" ", "");
            for (int i  = 0; i < number.length(); i++) {
                char c = number.charAt(i);
                if (!Character.isDigit(c)) {
                    if (i == 0 && c != '(') return false;
                    if (i == 4 && c != ')') return false;
                    if (i == 3 && c != '-') return false;
                    if (i == 8 && c != '-') return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }
}
