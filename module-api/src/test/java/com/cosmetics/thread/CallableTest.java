package com.cosmetics.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableTest {
    @Test
    public void callableTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //제네릭을 사용해 결과값을 받을 수 있는 Runnable의 발전 형태 Callable
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000L);
                System.out.println("1. Thread name :" + Thread.currentThread().getName());
                return "1. Thread name :" + Thread.currentThread().getName();
            }
        };
        executorService.submit(callable);
        executorService.shutdown();
    }

    @Test
    public void callableExceptionTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = new Callable<>() {
            @Override
            public String call() throws Exception {
                System.out.println("2. Thread name :" + Thread.currentThread().getName());
                return "2. Thread name :" + Thread.currentThread().getName();
            }
        };
        executorService.submit(callable);
        executorService.shutdown();
    }
}
