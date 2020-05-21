package com.qyc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qyc
 * @time 2020/5/21 - 17:01
 */

//线程  操作 资源类
    //new runnable  blocked wating time_wating terminated(结束)
    //三个售票员卖30张票
//ReentrantLock()  瑞安吹  可重复锁
    //lambda表达式 ：copy小括号，写死右箭头，落地大括号
//函数式编程只能有一个方法  但可以有多个default，static方法
//    @FunctionalInterface

class Ticket{
    public  int num = 30;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if(num>0){
                System.out.println(Thread.currentThread().getName()+" 卖出第  "+ num-- +"票  还有"+num+"张");
            }
        }finally {
            lock.unlock();
        }

    }
}
public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {for(int i = 0;i<40;i++) ticket.sale();},"A").start();
//        new Thread(() -> {for(int i = 0;i<40;i++) ticket.sale();},"B").start();
//        new Thread(() -> {for(int i = 0;i<40;i++) ticket.sale();},"C").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<40;i++) ticket.sale();
            }
        },"D").start();
    }


}
