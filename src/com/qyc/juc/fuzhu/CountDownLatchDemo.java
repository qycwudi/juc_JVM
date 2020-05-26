package com.qyc.juc.fuzhu;

import java.util.concurrent.CountDownLatch;

/**
 * @author qyc
 * @time 2020/5/22 - 20:39
 */
//倒计时  减法
    //6个学生，班长最后锁门
    //当计数器变为0时，await被唤醒
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i = 1;i <= 6;i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+" 离开教室");
                countDownLatch.countDown();
                },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长锁门");
    }
}
