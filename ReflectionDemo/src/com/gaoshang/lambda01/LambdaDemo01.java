package com.gaoshang.lambda01;


import org.junit.jupiter.api.Test;

import java.util.Comparator;

/*
 * @description:lambda表达式练习
 * @author Gaoshang
 * @date 2020/10/15
 * @email gs_nuaa@163.com
 */
public class LambdaDemo01 {

    @Test
    public void lambdaTest(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();

        System.out.println("****************");
        Runnable r2 = () -> System.out.println("我爱故宫");
        r2.run();
    }

    @Test
    public  void lambdaTest2(){
        Comparator comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        int result = comparator.compare(1,2);
        System.out.println(result);

        System.out.println("******************");
        //lambda表达式
        Comparator<Integer> comparator1 = (o1,o2) ->Integer.compare(o1,o2);
        int result2 = comparator1.compare(2,3);
        System.out.println(result2);
        System.out.println("******************");

        //方法引用
        Comparator<Integer> comparator2 = Integer::compare;
        int result3 = comparator2.compare(4,5);
        System.out.println(result3);
    }
}
