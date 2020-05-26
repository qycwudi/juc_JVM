package com.qyc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author qyc
 * @time 2020/5/22 - 21:25
 */

//读写锁，保持数据一致性
class MapDemo{
    private volatile Map<String,String> map = new HashMap();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void write(String key,String value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"---开始写入 "+key);
            map.put(key,value);

        }finally {
            readWriteLock.writeLock().unlock();
        }
        System.out.println("写入完成");
    }

    public void read(String key){
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"开始读取"+key);
        }finally {
            readWriteLock.readLock().unlock();
        }
        System.out.println("读取完成"+key);
    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        //资源类
        MapDemo mapDemo = new MapDemo();
        for(int i = 1;i<=5;i++){
            final String num = i+"";
            new Thread(() -> {
                mapDemo.write(num,num);
            },String.valueOf(i)).start();
        }

        for(int i = 6;i<=10;i++){
            final String num = i+"";
            new Thread(() -> {
                mapDemo.read(num);
            },num).start();
        }
    }
}
