package com.qyc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author qyc
 * @time 2020/5/22 - 18:17
 */

//另一种创建多线程的方法，有返回值

    //有缓存
class Test implements Callable<Integer> {
    public Integer call() throws Exception {
        System.out.println("Callable");

        return 1314;
    }
}
 public class MyCallable{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new Test());

        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());
    }

}
