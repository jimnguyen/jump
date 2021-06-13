package com.cognixia.jump.exercises;

import java.util.HashMap;
import java.util.Map;

public class Exercise {
    public static void main(String[] args) {
        System.out.println("binaryToInt: " + binaryToInt("10101"));
        System.out.println("binaryToInt: " + binaryToInt("10111"));
        System.out.println("binaryToInt: " + binaryToInt("11111"));
        System.out.println("binaryToInt: " + binaryToInt("00001"));
        System.out.println();
        System.out.println("Built in parseInt: " + Integer.parseInt("10101", 2));
        System.out.println("Built in parseInt: " + Integer.parseInt("10111", 2));
        System.out.println("Built in parseInt: " + Integer.parseInt("11111", 2));
        System.out.println("Built in parseInt: " + Integer.parseInt("00001", 2));
        System.out.println();
        System.out.println("binaryToNumber: " + binaryToNumber("10101"));
        System.out.println("binaryToNumber: " + binaryToNumber("10111"));
        System.out.println("binaryToNumber: " + binaryToNumber("11111"));
        System.out.println("binaryToNumber: " + binaryToNumber("00001"));
    }

    // Only works with specific input
    public static int binaryToInt(String number) {
        if (number.length() != 5) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 16);
        map.put(1, 8);
        map.put(2, 4);
        map.put(3, 2);
        map.put(4, 1);

        int num = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '1')
                num += map.get(i);
        }

        return num;
    }

    // For all types of input
    public static int binaryToNumber(String number) {
        int num = 0;

        for (int i = number.length()-1; i >= 0; i--) {
            if (number.charAt(i) == '1')
                num += Math.pow(2, number.length()-1 - i);
        }

        return num;
    }
}
