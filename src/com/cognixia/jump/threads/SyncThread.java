package com.cognixia.jump.threads;

public class SyncThread extends Thread {

    private static int counter = 0;

    // sync the entire method
    public static synchronized void increment() {
        counter++;
    }

    public static synchronized void newIncrement() {
        counter++;
        System.out.println("Thread " + Thread.currentThread().getId() + ": " + counter);
    }

    public void incrementBlock() {
        System.out.println("----------------");
        synchronized (this) {
            counter++;
            System.out.println("Thread " + Thread.currentThread().getId() + ": " + counter);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {

            // Option 1
            increment();
            System.out.println("Thread " + Thread.currentThread().getId() + ": " + counter);

            // Option 2
//            newIncrement();

            // Option 3
//            incrementBlock();

        }
    }
}
