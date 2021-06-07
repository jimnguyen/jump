package com.cognixia.jump.threads;

public class NumThread extends Thread {
    // Need to override run() to say what this thread does
    @Override
    public void run() {
        System.out.println("Start thread " + Thread.currentThread().getId());
        Numbers.print(5);
        super.run();
    }
}
