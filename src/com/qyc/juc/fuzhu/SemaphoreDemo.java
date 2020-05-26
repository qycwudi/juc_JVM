package com.qyc.juc.fuzhu;

import javax.swing.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author qyc
 * @time 2020/5/22 - 21:00
 */

//秒杀 acquire  release
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4);

        for(int i = 1;i<= 6;i++){
            new Thread(() -> {
                try {
                    semaphore.acquire(); //抢占
                    System.out.println(Thread.currentThread().getName()+" 抢到");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+" 离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    semaphore.release(); //释放
                }
            },String.valueOf(i)).start();
        }
    }
}
