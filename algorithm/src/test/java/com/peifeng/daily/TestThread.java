package com.peifeng.daily;

import org.junit.Test;

public class TestThread {

    @Test
    public void testWaitAndNotify() throws InterruptedException {
        Object lock = new Object();

        Thread threadA = new Thread(() -> {
            synchronized (lock){
                for (int i = 0; i < 5; i++) {
//                    System.out.println("ThreadA:" + i);
                    try{
                        System.out.println("ThreadA:" + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (lock){
                for (int i = 0; i < 5; i++) {
//                    System.out.println("ThreadB:" + i);
                    try{
                        System.out.println("ThreadB:" + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });
        threadA.start();
//        Thread.sleep(1000L);
        threadB.start();
    }

    private static volatile int signal = 0;

    @Test
    public void testSignal() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            while(signal < 101){
                if(signal % 2 == 0){
                    System.out.println("threadA===:" + signal);
                    synchronized (this){
                        signal++;
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while(signal < 101){
                if(signal % 2 == 1){
                    System.out.println("threadB:" + signal);
                    synchronized (this){
                        signal=signal+1;
                    }
                }
            }
        });

        threadA.start();
        Thread.sleep(1000L);
        threadB.start();
    }
}
