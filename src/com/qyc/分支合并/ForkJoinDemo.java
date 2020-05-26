package com.qyc.分支合并;

import javax.xml.bind.annotation.XmlInlineBinaryData;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author qyc
 * @time 2020/5/26 - 17:36
 */
class MyTask extends RecursiveTask<Integer> {

    private int start;
    private int end;
    private int resouce;

    public MyTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(end-start<=10){
           for(int i = start;i<=end;i++){
               resouce +=i;
           }
        }else {

            int middle = (start+end)/2;
            System.out.println(middle);
            MyTask myTask1 = new MyTask(start,middle);
            MyTask myTask2 = new MyTask(middle+1,end);
            myTask1.fork();
            myTask2.fork();
            resouce = myTask1.join()+myTask2.join();
        }
        return resouce;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(1,100);

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask forkJoinTask = pool.submit(myTask);

        System.out.println(forkJoinTask.get());
        //关闭
        pool.shutdown();
    }
}
