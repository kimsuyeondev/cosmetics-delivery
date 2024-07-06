package com.cosmetics.thread;

import com.cosmetics.domain.sample.service.AsyncService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AsyncTest {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void testAsyncMethod() throws InterruptedException {

        for(int i=0; i<1000; i++) {
            System.out.println("11");
        }

        System.out.println("1111111111111");
        asyncService.customThread("aaaaaaaaaaa",2);
        for(int i=0; i<1000; i++) {
            System.out.println("2222");
        }
// org.springframework.core.task.TaskRejectedException:
    }
}
//상품유효성체크
//상품임시등록
//상품등록
