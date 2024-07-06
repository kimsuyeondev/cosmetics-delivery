package com.cosmetics.thread;

import org.junit.jupiter.api.Test;

public class RunnableTest {

    @Test
    public void threadStart() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread started : " + Thread.currentThread().getName());
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}


