package com.cognixia.jump.threads;

public class Numbers {
    static public void print(int max) {
        for (int i = 0; i < max; i++) {
            System.out.println("Thread " + Thread.currentThread().getId()
                    + ": " + i);
        }
    }
}
