package com.play4fun.lock;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {
    private static final ExecutorService ThreadPool = Executors.newFixedThreadPool(200);
    private static Integer threadCount = 500;
    private static ConcurrentLinkedQueue<Integer> set = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (Integer i = 0; i < threadCount; i++) {
            final Integer threadNum = i;
            ThreadPool.execute(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    test(threadNum, name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });

        }
        countDownLatch.await();
        ThreadPool.shutdown();
        System.out.println(set.size());
    }

    private static void test(Integer threadNum, String threadName) throws InterruptedException {
        Thread.sleep(1000);
        set.add(threadNum);
        System.out.println(String.format("threadNum:%d, thread:%s", threadNum, threadName));
        Thread.sleep(1000);
    }
}
