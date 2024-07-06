package com.cosmetics.thread;

import org.junit.jupiter.api.Test;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class FutureTest {
    @Test
    public void isDone_False() throws ExecutionException, InterruptedException, TimeoutException {
        //Thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<String> callable = getStringCallable();
        //미래에 완료된 callable의 반환값을 가져옴
        Future<String> future = executorService.submit(callable);

        executorService.shutdown();
        assertThat(future.isDone()).isFalse();

        //blocking방식, timeout가능
        System.out.println(future.get(6, TimeUnit.SECONDS));
        System.out.println(future.get());
    }

    @Test
    public void isDone_True() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<String> callable = getStringCallable();
        Future<String> future = executorService.submit(callable);

        System.out.println(future.isDone());
        System.out.println(future.isDone());
        System.out.println(future.isDone());
        System.out.println(future.get());//get이 블로킹이니까 기다리니까 바로 밑에 오류가 안남
        assertThat(future.isDone()).isTrue();

        if (future.isDone()) {
            System.out.println("is done");
            assertThat(future.isDone()).isTrue();
            executorService.shutdownNow();
        }
    }

    @Test
    public void isCancelled_True() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<String> callable = getStringCallable();
        Future<String> future = executorService.submit(callable);
        future.cancel(true);
        assertThat(future.isCancelled()).isTrue();
        assertThat(future.isDone()).isTrue();
        executorService.shutdown();
    }

    @Test
    public void isCancelled_False() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<String> callable = getStringCallable();
        Future<String> future = executorService.submit(callable);
        assertThat(future.isCancelled()).isFalse();

        executorService.shutdown();
    }

    private static Callable<String> getStringCallable() {
        //제네릭을 사용해 결과값을 받을 수 있는 Runnable의 발전 형태 Callable
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000L);
                return "Thread name :" + Thread.currentThread().getName();
            }
        };
        return callable;
    }


}
