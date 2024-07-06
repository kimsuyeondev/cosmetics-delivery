package com.cosmetics.thread;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceTest {

    @Test
    public void invokeAll() throws InterruptedException, ExecutionException {
        //ExecutorService를 만들어 작업을 실행하면, shutdown이 호출되기 전까지 계속해서 다음 작업을 대기하게 된다. 그러므로 작업이 완료되었다면 반드시 shutdown을 명시적으로 호출해주어야 한다. 
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Instant start = Instant.now();

        Callable<String> callable2 = new Callable<>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000L);
                return "3Seconds " + Thread.currentThread().getName();
            }
        };

        Callable<String> callable1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "1Seconds " + Thread.currentThread().getName();
            }
        };
        //ScheduledExecutorService 도 있는데 이번엔 생략하자

        Callable<String> callable3 = new Callable<>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000L);
                return "5Seconds"  + Thread.currentThread().getName();
            }
        };

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(callable1, callable2, callable3));

        for(Future<String> future : futures) {
            System.out.println(future.get());
        }

        System.out.println("time = " + Duration.between(start, Instant.now()).getSeconds());
        executorService.shutdown();
    }

    @Test
    public void invokeAny() throws InterruptedException, ExecutionException {
        //ExecutorService를 만들어 작업을 실행하면, shutdown이 호출되기 전까지 계속해서 다음 작업을 대기하게 된다. 그러므로 작업이 완료되었다면 반드시 shutdown을 명시적으로 호출해주어야 한다. 
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Instant start = Instant.now();

        Callable<String> callable1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "1Seconds " + Thread.currentThread().getName();
            }
        };

        Callable<String> callable2 = new Callable<>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000L);
                return "3Seconds " + Thread.currentThread().getName();
            }
        };

        Callable<String> callable3 = new Callable<>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000L);
                return "5Seconds"  + Thread.currentThread().getName();
            }
        };

        String result = executorService.invokeAny(Arrays.asList(callable1, callable2, callable3));
        System.out.println(result);
        System.out.println("time = " + Duration.between(start, Instant.now()).getSeconds());
        executorService.shutdown();
    }



}
