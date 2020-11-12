package com.gaoshang.streamApi;

import com.gaoshang.lambda01.Employee;
import com.gaoshang.lambda01.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApi01 {

    /*
    *
    * 创建Stream的方式有四种：
    *   一、通过集合创建
    * */
    @Test
    public void test(){
        List<Employee> employeeList = EmployeeData.getEmployeeList();

        //default Stream<E> steam():返回一个顺序流
        Stream<Employee> stream =employeeList.stream();

        //default Stream<E> parallelStream():返回一个并行流
        Stream<Employee> stream1 = employeeList.parallelStream();


    }

    /*
    * 二、通过数组获取Stream
    * */
    @Test
    public void test2(){
        int[] arr = {1,2,3,4,5,6};
        //调用Arrays类的static <T> Steam<T> stream(T[] array):返回一个流
        IntStream stream = Arrays.stream(arr);

        Employee employee = new Employee(1,"zhang3",15,1000);
        Employee employee1 = new Employee(2,"li4",16,2000);
        Employee[] employees = new Employee[]{employee,employee1};

        Stream<Employee> stream1 = Arrays.stream(employees);


    }

    /*
    * 方法三、通过Stream的of()
    * */
    @Test
    public void test3(){
        Stream<Integer> stream  = Stream.of(1,2,3,4,5,6,7,8);

    }

    /*
    * 方法四：创建无限流
    * */
    @Test
    public void test4(){
        //迭代
        //public static <T> Stream<T> iterate(final T seed,final UnaryOperator<T> f)

        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);

        //生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
