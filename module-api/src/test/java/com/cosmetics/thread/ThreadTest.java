package com.cosmetics.thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    public void threadStart() {
        Thread thread = new MyThread();
        thread.run();
        thread.start();
    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread started : " + Thread.currentThread().getName());
    }
}
