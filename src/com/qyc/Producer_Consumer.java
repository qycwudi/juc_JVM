package com.qyc;

import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qyc
 * @time 2020/5/21 - 23:06
 */

//判断  干活  通知
    //防止虚假唤醒   while
class Number{
    private int num = 0;

    public synchronized void add() throws InterruptedException {
        while (num!=0){
            this.wait();
        }
        num++;
        System.out.println("生产  "+num);
        this.notifyAll();
    }
    public synchronized void del() throws InterruptedException {
        while(num==0){
            this.wait();
        }
        num--;
        System.out.println("消费 "+num);
        this.notifyAll();
    }

    //Lock
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void addLock() throws InterruptedException {
        lock.lock();
        try{
            while (num!=0){
                condition.await();
            }
            num++;
            System.out.println("生产  "+num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public void delLock() throws InterruptedException {
        lock.lock();
        try{
            while(num==0){
                condition.await();
            }
            num--;
            System.out.println("消费 "+num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

public class Producer_Consumer {


    public static void main(String[] args) {
        Number number = new Number();

//            new Thread(() -> {
//                try {
//                    for(int j = 0;j <10;j++) number.add();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            },String.valueOf(1)).start();
//
//
//            new Thread(() -> {
//                try {
//                    for(int j = 0;j <10;j++) number.del();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            },String.valueOf(2)).start();
//
//        new Thread(() -> {
//            try {
//                for(int j = 0;j <10;j++) number.add();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },String.valueOf(3)).start();
//
//        new Thread(() -> {
//            try {
//                for(int j = 0;j <10;j++) number.del();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },String.valueOf(4)).start();

        new Thread(() -> {
            try {
                for(int j = 0;j <10;j++) number.addLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },String.valueOf(1)).start();


        new Thread(() -> {
            try {
                for(int j = 0;j <10;j++) number.delLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },String.valueOf(2)).start();

        new Thread(() -> {
            try {
                for(int j = 0;j <10;j++) number.addLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },String.valueOf(3)).start();

        new Thread(() -> {
            try {
                for(int j = 0;j <10;j++) number.delLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },String.valueOf(4)).start();
    }
}
