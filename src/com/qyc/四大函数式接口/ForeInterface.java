package com.qyc.四大函数式接口;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author qyc
 * @time 2020/5/26 - 14:59
 */

public class ForeInterface {
    public static  void main(String[] args) {

        //消费型接口  有参数，无返回值
        Consumer<String> consumer = t -> {
            System.out.println(t);
        };
        consumer.accept("consumer");

        //供给型接口  无参数，有返回值
        Supplier<String> supplier = () -> {
            return  "supplier";
        };
        System.out.println(supplier.get());

        //函数型接口 有参数，有返回值
        Function<String,Integer> function = s -> {
            if(s.equals("qyc"))
            return 2;
            return null;
        };
        System.out.println(function.apply("qyc"));

        //断定型接口 有参数，返回值为boolean
        Predicate<String> predicate = s ->{
            return true;
        };
        System.out.println(predicate.test("qyc"));
    }
}
