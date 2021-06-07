package com.cognixia.jump.threads;

public class NumbersRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Start Thread: " + Thread.currentThread().getId());
        Numbers.print(25);
    }
}
