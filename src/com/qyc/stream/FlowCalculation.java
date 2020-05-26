package com.qyc.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qyc
 * @time 2020/5/26 - 16:29
 */
public class FlowCalculation {
    public static void main(String[] args) {
        Student student1 = new Student(1, "a");
        Student student2 = new Student(4, "b");
        Student student3 = new Student(3, "c");
        Student student4 = new Student(2, "d");
        List<Student> list = Arrays.asList(student1, student2, student3, student4);

        //流式计算
        //collect(Collectors.toList())  流转list
//        list = (List<Student>) list.stream().filter(u ->{
//            return u.getId()%2==0;
//        }).filter(u -> {
//            return u.getId()>3;
//        }).collect(Collectors.toList());

//        list.stream().filter(u ->{
//            return u.getId()%2==0;
//        }).forEach(System.out::print);
//
//        list = (List<Student>) list.stream().filter(u ->{
//            return u.getId()%2==0;
//        }).sorted((o1,o2) -> {
//            return o1.getId().compareTo(o2.getId());
//        }).collect(Collectors.toList());

        //limit 显示1条数据
        //sorted 排序
        list = (List<Student>) list.stream().filter(u ->{
            return u.getId()%2==0;
        }).sorted(Comparator.comparing(Student::getId)).limit(1).collect(Collectors.toList());

        list = (List<Student>)list.stream().map(s ->{
            s.setName(s.getName().toUpperCase());
            return s;
        }).collect(Collectors.toList());
        for (Student s:list
             ) {
            System.out.println(s.toString());
        }
    }
}
