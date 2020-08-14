package com.peifeng.daily;

public class Signal {
    private static volatile int signal = 0;

    static class ThreadA implements Runnable{
        @Override
        public void run() {
            while(signal < 101){
                if(signal % 2 == 0){
                    System.out.println("threadA===:" + signal);
                    signal++;
//                    synchronized (this){
//                        signal++;
//                    }
                }
            }
        }
    }

    static class ThreadB implements Runnable{
        @Override
        public void run() {
            while(signal < 101){
                if(signal % 2 == 1){
                    System.out.println("threadB:" + signal);
                    signal++;
//                    synchronized (this){
//                        signal++;
//                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new ThreadA());
        System.out.println("1:"+threadA.getState());
        threadA.start();
        System.out.println("2:"+threadA.getState());
        threadA.sleep(1000L);
        System.out.println("3:"+threadA.getState());
        new Thread(new ThreadB()).start();
    }
}
