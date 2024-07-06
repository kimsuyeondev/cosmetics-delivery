package com.cosmetics.domain.sample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncService {

    @Async
    public void customThread(String str, int i) throws InterruptedException {
            //for (int i = 0; i < 100; ++i) { 자가호출은안됨
                log.error(str + "스레드명 : " + Thread.currentThread().getName() + " " + i);
                System.out.println(str + "[" + Thread.currentThread().getName() + "] CustomRunnable " + i);
    }

    public void tempCreate(String str,int i) {
        log.error(str + i + "tempCreate 스레드명 :" + Thread.currentThread().getName() );
    }

    public void tempUpdate(String str,int i) {
        log.error(str + i + "tempUpdate 스레드명 : " + Thread.currentThread().getName() );
    }

    public void tempDelete(String str,int i) {
        log.error(str + i + "tempDelete 스레드명 : " + Thread.currentThread().getName() );
    }


}
