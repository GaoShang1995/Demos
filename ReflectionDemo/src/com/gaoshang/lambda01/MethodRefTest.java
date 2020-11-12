package com.gaoshang.lambda01;

import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
*   方法引用
*
*   1.使用情景：
*       当要传递给lambda体的操作，已经有实现的方法了，就可以使用方法引用
*
*
* */
public class MethodRefTest {

//    情况一：对象：：实例方法

    /*
    * Consumer中的void accept(T t)
    * PrintStream中的void println(T t)
    * */
    @Test
    public void test1(){
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("北京");

        System.out.println("**********");

        PrintStream printStream = System.out;
        Consumer<String> con2 = printStream::println;
        con2.accept("晚安");
    }

    /*
    *
    * Supplier中的T get()
    * Employee中的String getName()
    * */
    @Test
    public void test2(){
//        Supplier<String> sup1 = ()->"123";
        Employee employee = new Employee(1,"zhang3",15,6000);
        Supplier<String> sup2 = () -> employee.getName();
        System.out.println(sup2.get());
        System.out.println("*****************");
        Supplier<String> sup3 = employee::getName;
        System.out.println(sup3.get());
    }
    /*
    * 情况2：类::静态方法
    * Comparator中的int compare(T t1,T t2)
    * Integer中的int compare(T t1,T t2)
    * */
    @Test
    public void test3(){
        Comparator<Integer> comparator2 = (a,b)->Integer.compare(a,b);
        System.out.println(comparator2.compare(3, 2));
        System.out.println("**************");
        Comparator<Integer> comparator = Integer :: compare;
        System.out.println(comparator.compare(2, 3));
    }

    /*
    * Function 中的R apply(T t)
    * Math中的Long round(Double d)
    * */
    @Test
    public void test4(){

        Function<Double,Long> function = d ->Math.round(d);
        System.out.println(function.apply(10.34));
        System.out.println("**************");

        Function<Double,Long>function1 = Math::round;
    }

    /*
    *
    * 情况3：类::实例方法
    * Comparator 中的int compare(T t1,T t2)
    * String中的int t1.compareTo(t2)
    * */
    @Test
    public void test5(){
        Comparator<String> comparator = (s1,s2)->s1.compareTo(s2);
        System.out.println(comparator.compare("abc", "abc"));
        System.out.println("****************");

        Comparator<String> comparator1 = String ::compareTo;
        comparator1.compare("abc","def");
    }

    /*
    *   Function中的R apply(T t)
    *   Employee中的String getName()
    * */
    @Test
    public void test6(){
        Employee employee = new Employee(1,"dd",15,1000);
        Function<Employee,String> function = e -> e.getName();
        System.out.println(function.apply(employee));
        System.out.println("*******************");
        Function<Employee,String> function1 = Employee::getName;
        System.out.println(function1.apply(employee));

    }

    /*构造器引用*/
    @Test
    public void test7(){
        Supplier<Employee> sup = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };

        System.out.println("*************");

        Supplier<Employee> sup2 = () -> new Employee();

        System.out.println("******************");
        Supplier<Employee> sup3 = Employee::new;

        System.out.println(sup3.get());
        System.out.println(sup2.get());
        System.out.println(sup.get());

    }

    @Test
    public void test8(){
        Function<Integer,Employee> function1 = id -> new Employee(id);
        Employee employee = function1.apply(1);
        System.out.println(employee);

        System.out.println("*************");
        Function<Integer,Employee> function2 = Employee::new;
        Employee employee1 = function2.apply(22);
        System.out.println(employee1);

    }

    @Test
    public void test9(){
        BiFunction<Integer,String,Employee> function = (id,name) -> new Employee(id,name);
        Employee employee = function.apply(1,"zhang3");
        System.out.println(employee);

        System.out.println("**************");
        BiFunction<Integer,String,Employee> function1 = Employee::new;
        Employee employee1 = function1.apply(2,"li4");
        System.out.println(employee1);
    }

    /*
    * 数组引用
    * */
    @Test
    public void test10(){
        Function<Integer,String[]> function = length -> new String[length];
        String[] arr1 = function.apply(5);
        System.out.println(Arrays.toString(arr1));

        System.out.println("*****************");
        Function<Integer,String[]> function1 = String[]::new;
        String[] arr2 = function1.apply(5);
        System.out.println(Arrays.toString(arr2));
    }
}
