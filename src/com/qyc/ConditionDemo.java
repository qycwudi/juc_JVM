package com.qyc;

import javax.swing.plaf.IconUIResource;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qyc
 * @time 2020/5/22 - 15:58
 */
class Methods{

    //先改标志位
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public void A(){
        lock.lock();
        try{
            //判断
            while (num!=1){
                condition1.await();
            }
            //干活
            for(int i = 1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"  "+i+"  "+num);
            }
            //通知
                num = 2;
                condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void B(){
        lock.lock();
        try{
            //判断
            while (num!=2){
                condition2.await();
            }
            //干活
            for(int i = 1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"  "+i+"  "+num);
            }
            //通知
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void C(){
        lock.lock();
        try{
            //判断
            while (num!=3){

                condition3.await();
            }
            //干活
            for(int i = 1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"  "+i+"  "+num);
            }
            //通知
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ConditionDemo {

    public static void main(String[] args) {
        //资源类
        Methods methods = new Methods();
        //操作
            //线程
            new Thread(() -> {
                for(int i = 0;i<2;i++) {
                    methods.A();
                }
            },"A").start();

            new Thread(() -> {
                for(int j = 0;j<2;j++) {
                    methods.B();
                }
            },"B").start();

            new Thread(() -> {
                for(int k = 0;k<2;k++) {
                    methods.C();
                }
            },"C").start();
        }
}
