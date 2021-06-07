package com.cognixia.jump.threads;

public class NumbersThreadRunner {
    public static void main(String[] args) {
        System.out.println("Main Thread ID: " + Thread.currentThread().getId());

        NumThread t1 = new NumThread();
        // This creates the thread, gives a lifecycle and then references .run() and executes accordingly
        // It isn't .run() that starts the thread
        t1.start();

        for (int i = 0; i < 3; i++) {
            new NumThread().start();
        }
    }
}
