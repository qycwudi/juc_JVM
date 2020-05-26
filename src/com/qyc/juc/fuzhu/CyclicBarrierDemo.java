package com.qyc.juc.fuzhu;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author qyc
 * @time 2020/5/22 - 20:45
 */

//循环栅栏  收集7颗龙珠召唤神龙   加法
//    CountDownLatch 是一次性的
//    CyclicBarrier 是可循环利用的
//    CountDownLatch 参与的线程的职责是不一样的，有的在倒计时，有的在等待倒计时结束。
//    CyclicBarrier 参与的线程职责是一样的。

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("召唤神龙");
        });

        for(int i = 1;i<=14;i++){
            final int num = i;
            new Thread(()->{
                System.out.println("抢到了"+num);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
