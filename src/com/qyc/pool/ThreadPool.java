package com.qyc.pool;

import java.util.concurrent.*;

/**
 * @author qyc
 * @time 2020/5/26 - 14:07
 */
public class ThreadPool {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        try{
//            for(int i = 1;i<=100;i++){
//                executorService.execute(() -> {
//                    System.out.println(Thread.currentThread().getName());
//                });
//            }
//        }finally {
//            executorService.shutdown();
//        }

//        AbortPolicy(默认):直接抛出RejectedExecutionException异常阻止 系统正常运行
//        CallerRunsPolicy:“调用者运行”一-种调节机制，该策略既不会抛弃任务，也不
//        会抛出异常，而是将某些任务回退到调用者,从而降低新任务的流量。
//        DiscardOldestPolicy:抛弃队列中等待最久的任务,然后把当前任务加人队列中
//        尝试再次提交当前任务。
//        DiscardPolicy:该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。
//        如果允许任务丢失，这是最好的一种策略。

        ExecutorService pool = new ThreadPoolExecutor(
                2,
                5,
                2L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

                try{
            for(int i = 1;i<=20;i++){
//                TimeUnit.SECONDS.sleep(1);
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
        }
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                finally {
                    pool.shutdown();
        }
    }
}
