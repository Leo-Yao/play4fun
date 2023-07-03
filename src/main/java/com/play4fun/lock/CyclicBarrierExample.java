package com.play4fun.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    private static final Integer threadCount = 500;
    private static ExecutorService ThreadPool = Executors.newFixedThreadPool(200);
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("-----welcome to next challenge-----"));

    public static void main(String[] args) throws InterruptedException {
        for (Integer i = 0; i < threadCount; i++) {
            Thread.sleep(1000);
            final Integer threadNum = i;
            ThreadPool.execute(() -> {
                try {
                    test(threadNum);
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        ThreadPool.shutdown();
    }

    private static void test(Integer threadNum) throws BrokenBarrierException, InterruptedException {
        System.out.println(threadNum + " start");
        cyclicBarrier.await();
        System.out.println(threadNum + " finish");
    }
}
