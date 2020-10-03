package com.geek.commonjava.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/pool")
public class ThreadPoolTest {

    private final Logger log = LoggerFactory.getLogger(ThreadPoolTest.class);

    @GetMapping("/ooml")
    public void ooml() throws InterruptedException {
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

        //printStats(threadPool);
        log.info("threadPool {}", threadPool.getActiveCount());
        for (int i = 0; i < 100000000; i++) {
            threadPool.execute(() -> {
                String playload = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();

                try{
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                }
                log.info(playload);
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
