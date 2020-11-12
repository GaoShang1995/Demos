package com.gaoshang.lambda01;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/*
 * @description: (o1,o2)->Integer.compare(o1,o2);
 * @author Gaoshang
 * @date 2020/10/15
 * @email gs_nuaa@163.com
 */
public class LambdaDemo02 {

    //无参数，无返回值的情况
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello,world");
            }
        };

        Runnable r2 = ()-> System.out.println("hello,world!");
        r2.run();
    }

    //有一个参数
    @Test
    public void test2(){
        //语法格式1
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("你好");
        System.out.println("*************");

        //语法格式2
        Consumer<String> consumer1 = (String s) -> {System.out.println(s);};
        consumer1.accept("再见");
        System.out.println("*****************");
        //语法格式3
        //根据泛型可以进行类型推断，无需设置参数类型
        Consumer<String> consumer2 = (s) -> {System.out.println(s);};
        consumer2.accept("晚安");
        System.out.println("********************");
        //语法格式4
        //只有一个参数的时候，可以省略小括号
        Consumer<String> consumer3 = s -> {
            System.out.println(s);
        };
        consumer3.accept("早上好");
        System.out.println("*******************");
    }

    //有多个参数，多条执行语句，可以有返回值
    @Test
    public void test3(){
        Comparator<Integer> comparator = (o1,o2) ->{
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };

        int result = comparator.compare(2,3);
        System.out.println(result);

        //当lambda体只有一条语句的时候，return与大括号若有，都可以省略
    }


    @Test
    public void test4(){
        happyTime(400,money -> System.out.println("happytime"+money));
    }

    public void happyTime(double money,Consumer<Double> consumer){
        consumer.accept(money);
    }

    @Test
    public void test5(){
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("a");
        list1.add("b");
        list1.add("c");

       List<String> result =  filterString(list1,s->s.contains("a"));
        System.out.println(result);
    }

    public ArrayList<String> filterString(List<String> list, Predicate<String> predicate){
        ArrayList<String> arrayList= new ArrayList<>();
        for(String s: list){
            if (predicate.test(s)) {
                arrayList.add(s);
            }
        }
        return arrayList;
    }
}
