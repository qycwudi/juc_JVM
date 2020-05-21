package com.qyc;

import java.util.concurrent.TimeUnit;

/**
 * @author qyc
 * @time 2020/5/21 - 20:19
 */
/*
1.标准访问       QQ 微信
2.QQ暂停4秒     QQ 微信  锁的是this
3.新增say方法   你好 QQ  一个有锁，一个没锁
4.2部手机，     微信QQ   不是一个this锁
5.2个静态同步方法，1部手机   QQ   微信  因为锁的是类，而不是this对象
6.2个静态同步方法，2部手机   QQ   微信  因为锁的是类，而不是this对象
7.1个静态同步方法 1部手机 微信QQ    因为QQ锁的是类，而微信锁this对象，不是一个锁，不发生竞争
8.1个静态同步方法 2部手机 微信QQ    因为QQ锁的是类，而微信锁this对象，不是一个锁，不发生竞争

 */
class Phone{
    public static synchronized void QQ() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("QQ");
    }
//    public static synchronized void WX(){
    public synchronized void WX(){
        System.out.print("微信");
    }

    public static void say(){
        System.out.println("你好");
    }

}
public class Synchionized_8lock {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(()->{
            try {
                phone.QQ();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{phone.WX();}).start();
//        new Thread(()->{phone.say();}).start();phone1
//        new Thread(()->{phone1.WX();}).start();
    }

}
