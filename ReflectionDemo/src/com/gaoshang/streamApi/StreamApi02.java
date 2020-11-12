package com.gaoshang.streamApi;

import com.gaoshang.lambda01.Employee;
import com.gaoshang.lambda01.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
*
*
* 中间操作链
* */
public class StreamApi02 {

    /*
    * 1.筛选与切片
    * */
    @Test
    public void test(){
        //filter过滤

        List<Employee> employeeList = EmployeeData.getEmployeeList();
        Stream<Employee> stream = employeeList.stream();

        //查询员工表中薪资大于7000的
        stream.filter(e -> e.getSalary()>7000).forEach(System.out::println);

        System.out.println("*******************8");
        //截断流
         employeeList.stream().limit(3).forEach(System.out::println);
        System.out.println("****************");

        //跳过
        employeeList.stream().skip(3).forEach(System.out::println);
        System.out.println("*****************");
        //筛选
        employeeList.add(new Employee(1006,"zhang8",20,11000.00));
        employeeList.add(new Employee(1006,"zhang8",20,11000.00));
        employeeList.add(new Employee(1006,"zhang8",20,11000.00));
        employeeList.add(new Employee(1006,"zhang8",20,11000.00));
        employeeList.stream().distinct().forEach(e -> System.out.println(e));

    }

    /*
    * 映射
    * */
    @Test
    public void test2(){
        //map(Function f),映射操作
        List<String> strings = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        strings.stream().map(str -> str.toUpperCase()).forEach(str ->System.out.println(str));


        //获取姓名长度大于3的员工姓名

        List<Employee> employeeList = EmployeeData.getEmployeeList();
        Stream<Double> stringStream = employeeList.stream().map(Employee::getSalary);
        stringStream.filter(salary -> salary > 8000.00).forEach(salary ->System.out.println(salary));

        //flatMap 接受一个function作为参数，将流中的每一个值都换成另一个流，然后把所有流连接成一个流

        System.out.println("***************");
//        List<Employee> employeeList1 = EmployeeData.getEmployeeList();
        Stream<Stream<Character>> streamStream = strings.stream().map(s -> fromStringToStream(s));
        Stream<Character> characterStream = strings.stream().flatMap(StreamApi02::fromStringToStream);
        characterStream.forEach(s->System.out.println(s));

    }

    public static Stream<Character> fromStringToStream(String s){
        ArrayList<Character> arrayList = new ArrayList<>();
        for(Character c : s.toCharArray()){
            arrayList.add(c);
        }

        return  arrayList.stream();
    }

    /*
    * 排序
    * */
    @Test
    public void test3(){
        //sorted()自然排序

        Arrays.asList(1,4,12,344,21,3,16);
    }
}
