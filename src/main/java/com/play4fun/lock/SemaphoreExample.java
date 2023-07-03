package com.play4fun.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static Semaphore semaphore = new Semaphore(20);
    private static final ExecutorService Executor = Executors.newFixedThreadPool(200);
    private static Integer threadCount = 500;

    public static void main(String[] args) {
        for (Integer i = 0; i < threadCount; i++) {
            Integer threadNum = i;
            Executor.execute(() -> {
                try {
                    semaphore.acquire();
                    String name = Thread.currentThread().getName();
                    test(threadNum, name);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        Executor.shutdown();
        System.out.println("finish");

    }

    private static void test(Integer threadNum, String threadName) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(String.format("threadNum:%d, thread:%s", threadNum, threadName));
        Thread.sleep(1000);
    }
}
