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
/*  1个对象里面如果有多个synchronized方法， 某个时刻内，只要一个线程去调用其中的一个synchronized方法了，
        其它的线程都只能等待，换句话说，菜-个时刻内，只能有唯一一个线程去访间这些synchronized方法
        锁的是 当前对象this,被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
        加个普通方法后发现和同步锁无关
        换成两个对象后，不是同把锁了，情况立刻变化。
        都换成静态同步方法后，情况又变化
        所有的非静态同步方法用的都是同一把锁一实例对象本身，
synchronized.实现同步的基动: Java 中的拇一一个对象都可以作为锁。
        具体表现为以下3种形式。
        对于普通同步方法，锁是当前实例对象。
        对于同步方法块，锁是Synchonized括 号里配置的对象。
        对于静态同步方法，锁是当前类的CLass对象。
        当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。

        也就尼说如果- - 个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必领等待获取锁的方法释放锁后才能获取锁，
        可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
        所以毋频等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
        所有的静态同步方法用的也是同一把锁一 类对象本身，
        这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
        但是一旦-一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
        而不管是同一一个实例对象的静态同步方法之间，
        还是不同的实例对象的静态同步方法之间，只要它们同一一个类的实例对象!
*/