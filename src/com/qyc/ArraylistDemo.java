package com.qyc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author qyc
 * @time 2020/5/21 - 18:29
 */
// java.util.concurrent 实用程序类
//集合类不安全  默认10      hashmap16  扩容一倍    vector线程安全
    //扩容扩一半，copyof

    //volatile

    //故障现象
//java.util.ConcurrentModificationException
    //导致原因
//线程争抢资源发生线程修改异常
    //解决方法
//    list
//    set
//    map
//hashset底层为hashmap，第二个为Object
public class ArraylistDemo {
    public static void main(String[] args) {

        //出错
//        List list  = new ArrayList<>();
//        for(int i = 0;i<5;i++){
//            new Thread(() ->{
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                for (Object l :
//                        list) {
//                    System.out.print(l.toString()+"   ");
//                }
//                System.out.println();
//
//            },String.valueOf(i)).start();
//        }

//        //ArrayList
                List list  = new CopyOnWriteArrayList();
        for(int i = 0;i<5;i++){
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,8));
//                for (Object l :
//                        list) {
//                    System.out.print(l.toString()+"   ");
//                }
//                System.out.println();
                list.forEach(System.out::println);
            },String.valueOf(i)).start();
        }
        //set
//        Set set  = new CopyOnWriteArraySet();
//        for(int i = 0;i<5;i++){
//            new Thread(() ->{
//                set.add(UUID.randomUUID().toString().substring(0,8));
//                for (Object l :
//                        set) {
//                    System.out.print(l.toString()+"   ");
//                }
//                System.out.println();
//
//            },String.valueOf(i)).start();
//        }

        //map
//        Map map  = new ConcurrentHashMap<String,String>();
//        for(int i = 0;i<5;i++){
//            new Thread(() ->{
//                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
//                Set set = map.keySet();
//
//                for (Object l :
//                       set) {
//                    System.out.print(map.get(l).toString()+"   ");
//                }
//                System.out.println();
//
//            },String.valueOf(i)).start();
//        }
    }
}
