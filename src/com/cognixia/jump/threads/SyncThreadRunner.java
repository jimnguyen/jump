package com.cognixia.jump.threads;

public class SyncThreadRunner {
    public static void main(String[] args) {
//        SyncThread syncThread = new SyncThread();
//        syncThread.start();
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new SyncThread();

            switch (i) {
                case 0 -> threads[i].setPriority(10);
                case 1 -> threads[i].setPriority(6);
                case 2 -> threads[i].setPriority(2);
                default -> throw new IllegalArgumentException("Unexpected value: " + i);
            }
        }

        for (Thread st : threads) {
            st.start();
        }
    }
}
