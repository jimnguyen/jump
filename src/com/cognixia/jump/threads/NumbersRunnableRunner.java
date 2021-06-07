package com.cognixia.jump.threads;

public class NumbersRunnableRunner {
    public static void main(String[] args) {
        System.out.println("Main Thread ID: " + Thread.currentThread().getId());

        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new NumbersRunnable());
            threads[i].start();
        }
    }
}
